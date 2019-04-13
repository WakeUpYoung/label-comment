package cn.wakeupeidolon.label.comment.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Wang Yu
 */
public class HttpUtils {
    private HttpUtils(){
    
    }
    
    public static String get(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response execute = call.execute();
            ResponseBody body = execute.body();
            if (body == null){
                return null;
            }
            return body.string();
        } catch (IOException e) {
            return null;
        }
    }
}
