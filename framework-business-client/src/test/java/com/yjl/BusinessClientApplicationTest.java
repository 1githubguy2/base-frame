package com.yjl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * 业务SDK测试
 */
@SpringBootApplication
@SpringBootTest
@ContextConfiguration(classes = BusinessClientApplicationTest.class)
public class BusinessClientApplicationTest {

}
