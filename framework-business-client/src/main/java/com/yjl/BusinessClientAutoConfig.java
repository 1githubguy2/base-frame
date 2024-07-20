package com.yjl;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jdk.nashorn.internal.parser.DateParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author unbroken
 * @Description 业务引擎客户端自动配置类
 * @Version 1.0
 * @date 2024/7/20 13:52
 */
@RequiredArgsConstructor
@ComponentScan(
        basePackages = {"com.yjl"},
        nameGenerator = BusinessClientAutoConfig.CustomBeanNameGenerator.class
)
@EnableFeignClients(
        value = "com.yjl.feign.client"
)
@EnableConfigurationProperties
@Configuration(proxyBeanMethods = false)
public class BusinessClientAutoConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer);
            builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer);
            builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
        };
    }
    /**
     * 自定义的bean名称生成器
     * 目的是为了集成其它系统，减少可能存在的bean名称冲突
     */
    public static class CustomBeanNameGenerator implements BeanNameGenerator {

        private static final String PREFIX = "yjl-framework-client-";
        @Override
        public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
            return PREFIX + definition.getBeanClassName();
        }
    }
}
