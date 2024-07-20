package com.yjl.feign.client;

import com.yjl.BusinessClientAutoConfig;
import com.yjl.dto.ResponseDTO;
import com.yjl.feign.BusinessFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.Response;

/**
 * @author unbroken
 * @Description feign客户端
 * @Version 1.0
 * @date 2024/7/21 1:27
 */
@FeignClient(value = "test-client", url = "business.url", path = "/rest/test", configuration = BusinessFeignClientConfig.class)
public interface TestClient {

    @RequestMapping(value = "/getTest")
    ResponseDTO<String> getTest();
}
