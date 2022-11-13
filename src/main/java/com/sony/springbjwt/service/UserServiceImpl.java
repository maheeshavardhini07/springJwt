package com.sony.springbjwt.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sony.springbjwt.entity.Role;
import com.sony.springbjwt.entity.User;
import com.sony.springbjwt.entity.UserDto;
import com.sony.springbjwt.repository.UserRepository;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService{

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
        List<User> list = new ArrayList<User>();
         userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
	
	@Override
	public User findOne(String userName) {
		return userRepository.findByUserName(userName);
	}

    public User save(UserDto user) {

        User nUser = user.getUserFromEntity();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        if(nUser.getEmail().split("@")[1].equals("admin.edu")){
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }

        nUser.setRoles(roleSet);
        return userRepository.save(nUser);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user));
    }
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
	
}
