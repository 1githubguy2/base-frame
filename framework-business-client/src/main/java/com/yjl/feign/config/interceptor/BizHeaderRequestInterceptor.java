package com.yjl.feign.config.interceptor;

import com.yjl.constant.CustomHeaderNameConstant;
import com.yjl.properties.BusinessClientProperties;
import com.yjl.service.CurrentUserService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author unbroken
 * @Description 用于添加自定义请求头的拦截器
 * @Version 1.0
 * @date 2024/7/21 1:12
 */
@RequiredArgsConstructor
public class BizHeaderRequestInterceptor implements RequestInterceptor {
    private final BusinessClientProperties businessClientProperties;
    private final CurrentUserService currentUserService;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        try {
            requestTemplate.header(CustomHeaderNameConstant.OPERATION_USER_ID, currentUserService.getId());
            requestTemplate.header(CustomHeaderNameConstant.OPERATION_USER_CODE, currentUserService.getCode());
            String name = currentUserService.getName();
            if (StringUtils.isNotBlank(name)) {
                requestTemplate.header(CustomHeaderNameConstant.OPERATION_USER_NAME, URLEncoder.encode(name, "utf-8"));
            }
            requestTemplate.header(CustomHeaderNameConstant.APP_SECRET, businessClientProperties.getAppSecret());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
