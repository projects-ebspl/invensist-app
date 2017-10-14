package com.invensist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invensist.dao.ConfigDao;
import com.invensist.entities.User;
import com.invensist.models.UserModel;

@Service
public class ConfigService {
	
	@Autowired
	ConfigDao configDao;

	
	/**
	 * get all users
	 * @return
	 */
	public List<UserModel> getUsers() {		
		List<User> users = configDao.getAllUsers();
		return users.stream().map(user -> {
			UserModel model = new UserModel();
			BeanUtils.copyProperties(user, model);
			return model;
		}).collect(Collectors.toList());
	}
	
}
