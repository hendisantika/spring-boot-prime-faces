package com.example.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;


@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	private final static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(userName);
		Set<Role> roles = user != null ? user.getRoles() : new TreeSet<Role>();
		List<GrantedAuthority> authorities = getUserAuthority(roles);
		return buildUserForAuthentication(user, authorities, userName);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Role role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities, String userName) {
		if (user != null){
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, authorities);
		}else{
			logger.info("User not found: " + userName);
			throw new UsernameNotFoundException("User not found: " + userName);
		}
	}
}