package com.sugar.leke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan(basePackages = "com.sugar.leke.mapper")
@ServletComponentScan(basePackages = {"com.sugar.leke.config.**"})
@SpringBootApplication
public class LekeStarter {

    public static void main(String[] args) {
        //new SpringApplicationBuilder(PurchaseApplication.class).initializers(new CloudConfigSupportConfiguration()).web(true).run(args);
        new SpringApplicationBuilder(LekeStarter.class).web(true).run(args);
    }
}
