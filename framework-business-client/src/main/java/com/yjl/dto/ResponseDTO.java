package com.yjl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/7/21 0:56
 */
@Data
@Accessors(chain = true)
public class ResponseDTO<T> implements Serializable {

    public static final String SUCCESS = "1";
    public static final String FAIL = "0";
    @ApiModelProperty(value = "返回结果(0失败，1成功)", allowableValues = "0,1")
    private String code = SUCCESS;
    @ApiModelProperty(value = "状态码(标准)")
    private String status;
    @ApiModelProperty(value = "状态描述(标准)")
    private String detail;
    @ApiModelProperty(value = "自定义说明")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private T data;
}
