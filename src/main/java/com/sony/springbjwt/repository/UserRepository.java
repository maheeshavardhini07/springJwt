package com.sony.springbjwt.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sony.springbjwt.entity.User;

@Qualifier("users")
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUserName(String userName);
}
