package com.sony.springbjwt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sony.springbjwt.entity.User;
import com.sony.springbjwt.entity.UserDto;

@Service
public interface UserService {
    List<User> findAll();
    User findOne(String username);
	User save(UserDto user);
}
