package com.yjl.service;

import com.yjl.dto.ResponseDTO;
import com.yjl.feign.client.TestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/7/21 1:44
 */
@RequiredArgsConstructor
@Service
public class BusinessService {
    private final TestClient testClient;

    public String getTest() {
        ResponseDTO<String> test = testClient.getTest();
        return test.getData();
    }
}
