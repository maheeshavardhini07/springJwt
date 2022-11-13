package com.sony.springbjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sony.springbjwt.entity.Role;
import com.sony.springbjwt.repository.RoleRepository;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findByName(String name) {
		Role role = roleRepository.findRoleByName(name);
		return role;
	}

}
