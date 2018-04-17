package com.userservice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.demo.schema.generated.user_service_demo.tables.records.UserRecord;
import com.userservice.demo.service.UserService;
import com.yyk.userservicedemo.generated.client.api.UserInfoApi;
import com.yyk.userservicedemo.generated.client.model.UserInfo;
import com.yyk.userservicedemo.generated.client.model.UserInfoRes;

@RestController
public class UserInfoController implements UserInfoApi {

	@Autowired
	private UserService userService;

	@Override
	public UserInfoRes getUserInfo(@PathVariable("user_id") Integer userId) {

		UserInfoRes userInfoRes = new UserInfoRes();

		UserRecord userRecord = userService.getUserById(userId);
		UserInfo userInfo = new UserInfo();

		userInfo.setUserId(userRecord.getId());
		userInfo.setPhone(userRecord.getPhone());
		userInfo.setCompany(userRecord.getCompany());
		userInfo.setEmail(userRecord.getEmail());
		userInfo.setName(userRecord.getName());

		userInfoRes.setStatusCode(200);
		userInfoRes.setMsg("Success");
		userInfoRes.setData(userInfo);
		return userInfoRes;
	}

}
