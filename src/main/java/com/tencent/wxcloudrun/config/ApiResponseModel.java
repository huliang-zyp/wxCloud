package com.tencent.wxcloudrun.config;

import lombok.Data;

import java.util.HashMap;

@Data
public final class ApiResponseModel<T> {

  private Integer code;
  private String errorMsg;
  private Object data;

  private ApiResponseModel(int code, String errorMsg, T data) {
    this.code = code;
    this.errorMsg = errorMsg;
    this.data = data;
  }
  
  public static ApiResponseModel ok() {
    return new ApiResponseModel(0, "", new HashMap<>());
  }

  public static ApiResponseModel ok(Object data) {
    return new ApiResponseModel(0, "", data);
  }

  public static ApiResponseModel error(String errorMsg) {
    return new ApiResponseModel(0, errorMsg, new HashMap<>());
  }
}
