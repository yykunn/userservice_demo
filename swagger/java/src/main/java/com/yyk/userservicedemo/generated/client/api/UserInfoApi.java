package com.yyk.userservicedemo.generated.client.api;

import com.yyk.userservicedemo.generated.client.ApiClient;
import com.yyk.userservicedemo.generated.client.EncodingUtils;

import com.yyk.userservicedemo.generated.client.model.UserInfoRes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-18T00:07:43.493+08:00")
public interface UserInfoApi extends ApiClient.Api {


  /**
   * get user information
   * 
    * @param userId  (required)
   * @return UserInfoRes
   */
  @RequestLine("GET /user/{userId}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  UserInfoRes getUserInfo(@Param("userId") Integer userId);
}
