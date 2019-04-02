package cn.wakeupeidolon.label.comment.utils;

import ma.glasnost.orika.DefaultFieldMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @Author Wang Yu
 * @Description 对象转换器
 * @Date 11:40 2019/3/20
 */
public class BeanMapper {
    private static final MapperFactory MAPPER_FACTORY = (new Builder()).build();
    private static final MapperFacade DEFAULT_MAPPER;
    
    private BeanMapper() {
    }
    
    static {
        DEFAULT_MAPPER = MAPPER_FACTORY.getMapperFacade();
    }
    
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return map(source, destinationClass, () -> {
            return DEFAULT_MAPPER;
        });
    }
    
    public static <S, D> D map(S source, Class<D> destinationClass, Supplier<MapperFacade> mapper) {
        return ((MapperFacade)mapper.get()).map(source, destinationClass);
    }
    
    private static <S, D> MapperFacade buildClassMapperFacade(Class<S> sourceClass, Class<D> destinationClass, Map<String, String> fieldMap) {
        MapperFactory factory = (new Builder()).build();
        ClassMapBuilder<S, D> classMapBuilder = factory.classMap(sourceClass, destinationClass);
        Iterator var5 = fieldMap.keySet().iterator();
        
        while(var5.hasNext()) {
            String key = (String)var5.next();
            classMapBuilder.field(key, (String)fieldMap.get(key));
        }
        
        classMapBuilder.byDefault(new DefaultFieldMapper[0]).register();
        return factory.getMapperFacade();
    }
    
    public static <S, D> D map(S source, Class<D> destinationClass, Map<String, String> fieldMap) {
        return buildClassMapperFacade(source.getClass(), destinationClass, fieldMap).map(source, destinationClass);
    }
    
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<S> sourceClass, Class<D> destinationClass, Map<String, String> fieldMap) {
        return buildClassMapperFacade(sourceClass, destinationClass, fieldMap).mapAsList(sourceList, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
    }
    
    public static <S, D> D map(S source, Type<S> sourceType, Type<D> destinationType) {
        return DEFAULT_MAPPER.map(source, sourceType, destinationType);
    }
    
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<S> sourceClass, Class<D> destinationClass) {
        return DEFAULT_MAPPER.mapAsList(sourceList, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
    }
    
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Type<S> sourceType, Type<D> destinationType) {
        return DEFAULT_MAPPER.mapAsList(sourceList, sourceType, destinationType);
    }
    
    public static <S, D> D[] mapArray(final D[] destination, final S[] source, final Class<D> destinationClass) {
        return DEFAULT_MAPPER.mapAsArray(destination, source, destinationClass);
    }
    
    public static <S, D> D[] mapArray(D[] destination, S[] source, Type<S> sourceType, Type<D> destinationType) {
        return DEFAULT_MAPPER.mapAsArray(destination, source, sourceType, destinationType);
    }
    
    public static <E> Type<E> getType(final Class<E> rawType) {
        return TypeFactory.valueOf(rawType);
    }
    
    public static MapperFactory getFactory() {
        return MAPPER_FACTORY;
    }
    

}
