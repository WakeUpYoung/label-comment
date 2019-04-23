package cn.wakeupeidolon.label.comment.utils;

import cn.wakeupeidolon.label.comment.domain.Version;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Wang Yu
 */
public class VersionUtils {
    
    private static final Logger LOG = LoggerFactory.getLogger(VersionUtils.class);
    
    private VersionUtils(){}
    
    public static Version get(){
        try {
            File file = new File("version.txt");
            InputStream in = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            reader.close();
            String json = builder.toString();
            return JSON.parseObject(json, Version.class);
        } catch (IOException e) {
            LOG.error("get version 异常");
        }
        return null;
    }
}
