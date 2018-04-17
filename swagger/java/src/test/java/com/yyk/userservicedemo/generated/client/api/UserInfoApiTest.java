package com.yyk.userservicedemo.generated.client.api;

import com.yyk.userservicedemo.generated.client.ApiClient;
import com.yyk.userservicedemo.generated.client.model.UserInfoRes;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for UserInfoApi
 */
public class UserInfoApiTest {

    private UserInfoApi api;

    @Before
    public void setup() {
        api = new ApiClient().buildClient(UserInfoApi.class);
    }

    
    /**
     * get user information
     *
     * 
     */
    @Test
    public void getUserInfoTest() {
        Integer userId = null;
        // UserInfoRes response = api.getUserInfo(userId);

        // TODO: test validations
    }

    
}
