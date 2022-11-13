package com.sony.springbjwt.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sony.springbjwt.entity.Role;

@Qualifier("role")
@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findRoleByName(String name);
}
