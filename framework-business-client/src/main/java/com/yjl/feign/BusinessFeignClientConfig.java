package com.yjl.feign;

import com.yjl.feign.config.decoder.CustomResponseDecoder;
import com.yjl.feign.config.interceptor.BizHeaderRequestInterceptor;
import com.yjl.properties.BusinessClientProperties;
import com.yjl.service.CurrentUserService;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.clientconfig.FeignClientConfigurer;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

/**
 * @author unbroken
 * @Description 业务feign客户端的组件配置
 * @Version 1.0
 * @date 2024/7/20 16:51
 */
public class BusinessFeignClientConfig {
    @Bean
    public FeignClientConfigurer feignClientConfigurer() {
        return new FeignClientConfigurer() {
            @Override
            public boolean inheritParentConfiguration() {
                //设值为false是为了从feign-client自身的容器中获取相应组件，避免组件配置冲突
                return false;
            }
        };
    }

    @Bean
    public Logger.Level feignLoggerLevel(BusinessClientProperties properties) {
        if ("full".equalsIgnoreCase(properties.getFeignLogLevel())) {
            return Logger.Level.FULL;
        }
        return Logger.Level.NONE;
    }

    @Bean
    public Encoder encoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    public Decoder decoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new CustomResponseDecoder(messageConverters);
    }

    @Bean
    public RequestInterceptor bizHeaderRequestInterceptor(BusinessClientProperties businessClientProperties, CurrentUserService currentUserService) {
        return new BizHeaderRequestInterceptor(businessClientProperties, currentUserService);
    }

}
