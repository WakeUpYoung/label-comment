package cn.wakeupeidolon.label.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class LabelCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabelCommentApplication.class, args);
    }

}
