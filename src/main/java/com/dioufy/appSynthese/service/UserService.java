package com.dioufy.appSynthese.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dioufy.appSynthese.model.User;

public interface UserService {
	
	public List<User> getAllUsers();
	
	public User getUserById(long id);
	
	public void deleteUserById(long id);
	
	public void saveUser(User user);
	
	public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
