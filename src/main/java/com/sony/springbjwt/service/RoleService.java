package com.sony.springbjwt.service;

import org.springframework.stereotype.Service;

import com.sony.springbjwt.entity.Role;

@Service
public interface RoleService {
	Role findByName(String name);
}
