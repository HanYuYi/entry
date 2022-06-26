package com.HanYuYi.config;

import com.HanYuYi.pojo.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * springboot 配置类
 * WebMvcConfigurer: 用于定制化springMVC的功能
 */
@Configuration
public class UrlPathHelperConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            // 用于配置url参数的矩阵变量，跟 MatrixVariableController 的 1、2个接口相呼应
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper helper = new UrlPathHelper();
                helper.setRemoveSemicolonContent(false);

                configurer.setUrlPathHelper(helper);
            }

            // 自定义Converter，用于解析自定的request请求的参数格式
            // 这里定义Pet以"name,color"的格式传，跟 MatrixVariableController 的 3、4个接口相呼应
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {
                    @Override
                    public Pet convert(String source) {
                        if (!StringUtils.isEmpty(source)) {
                            String[] split = source.split(",");
                            Pet pet = new Pet();
                            pet.setName(split[0]);
                            pet.setColor(split[1]);
                            return pet;
                        }
                        return null;
                    }
                });
            }
        };
    }

}
