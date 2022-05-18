package com.example.springboot.utitlity;

import com.example.springboot.entity.User;
import com.example.springboot.request.UserRegisterRequest;
import com.example.springboot.request.UserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User convertOfUserRegisterDTO(UserRegisterRequest dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);

        return user;
    }
}
