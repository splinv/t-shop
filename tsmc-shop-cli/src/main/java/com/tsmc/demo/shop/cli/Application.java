package com.tsmc.demo.shop.cli;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;

/**
 * @author shihpeng
 * @date 2019/12/6
 */
@SpringBootApplication(scanBasePackages = "com.tsmc.demo.shop")
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("# ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
