package cn.wakeupeidolon.label.comment.config;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;

/**
 * @author Wang Yu
 */
public class GenerateUserId implements Configurable, IdentifierGenerator {
    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
    
    }
    
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        long currentTime = System.currentTimeMillis();
        int random = new Random().nextInt(999);
        String format = String.format("%03d", random);
        String s = String.valueOf(currentTime);
        String substring = s.substring(s.length() - 8);
        return substring + format;
    }
}
