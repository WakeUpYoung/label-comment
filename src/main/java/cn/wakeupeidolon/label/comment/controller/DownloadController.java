package cn.wakeupeidolon.label.comment.controller;

import cn.wakeupeidolon.label.comment.domain.Result;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author Wang Yu
 */
@Controller
@RequestMapping("/download")
@Api(tags = {"下载"})
public class DownloadController {

    @GetMapping("/{platform}")
    public void downloadAndroid(HttpServletRequest request, HttpServletResponse response, @PathVariable("platform") String platform){
        String fileName;
        String path;
        if (platform.equalsIgnoreCase("android")){
            fileName = "comment-label.apk";
            path = "/home/comment-label/android/";
        }else{
            fileName = "comment-label.ipa";
            path = "/home/comment-label/ios/";
        }
        File file = new File(path + fileName);
        if (!file.exists()){
            return;
        }
        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 实现文件下载
        byte[] buffer = new byte[1024*1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
        catch (IOException e) {
            return;
        }finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } // 关闭流
    }
}
