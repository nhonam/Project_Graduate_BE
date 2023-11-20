package com.shop.sport.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ConfigCors implements WebMvcConfigurer {

//    @Bean
//    public Cloudinary cloudinaryConfig() {
//        Cloudinary cloudinary = null;
//        Map config = new HashMap();
//        config.put("cloud_name", "dzljztsyy");
//        config.put("api_key", "633463535256664");
//        config.put("api_secret", "EyWtZfg-x67rjQx438ImVwc82PY");
//        cloudinary = new Cloudinary(config);
//        return cloudinary;
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE","HEAD","OPTIONS")
                .allowCredentials(false).allowedOrigins("*");
    }



}
