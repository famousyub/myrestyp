package com.example.crudmn.service;

import com.example.crudmn.payload.request.UserInfoRequest;
import com.example.crudmn.payload.response.UserInfoResponse;
import org.springframework.security.core.Authentication;

public interface UserInfoService {


    public void showinfo();

    public UserInfoResponse createUserInfo (Authentication auth , UserInfoRequest userinfo);


}
