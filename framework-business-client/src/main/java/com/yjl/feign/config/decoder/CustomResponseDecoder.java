package com.yjl.feign.config.decoder;

import com.yjl.dto.ResponseDTO;
import feign.Response;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/7/21 0:48
 */
@Slf4j
public class CustomResponseDecoder extends SpringDecoder {
    private static final String AUTH_FAILED_STATUS_CODE = "A0200";
    public CustomResponseDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        super(messageConverters);
    }

    @Override
    public Object decode(final Response response, Type type) throws IOException {
        Object result = super.decode(response, type);
        ResponseDTO<?> responseDTO = (ResponseDTO<?>) result;
        String statusCode = responseDTO.getStatus();
        if (AUTH_FAILED_STATUS_CODE.equalsIgnoreCase(statusCode)) {
            throw new RetryableException(
                    response.status(),
                    statusCode,
                    response.request().httpMethod(),
                    null,
                    response.request()
            );
        }

        if (ResponseDTO.FAIL.equalsIgnoreCase(statusCode)) {
            throw new RuntimeException(responseDTO.getMsg());
        }
        return result;
    }
}
