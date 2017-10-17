package com.invensist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.invensist.dao.ConfigDao;
import com.invensist.entities.User;
import com.invensist.models.UserModel;

@Service
public class ConfigService {
	
	@Autowired
	ConfigDao configDao;
	/*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
*/
	
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
	/**
	 * @param email
	 * @return
	 */
	public UserModel getUserByEmail(String email) {

		User user = configDao.getUserByEmail(email);

		return this.toUserModel(user);
	}
	/**
	 * save new user along with assigned roles
	 * @param userModel
	 */
	public boolean saveUser(UserModel userModel) {		
		User user = this.toUser(userModel);
		/*if(userModel.getPassword() != null){
			user.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
		}*/
		configDao.saveUser(user);
		return true;
	}
	private UserModel toUserModel(User user){

		if(user == null){
			return null;
		}
		UserModel userModel = new UserModel();
		userModel.setAddress(user.getAddress());
		userModel.setId(user.getId());
		userModel.setEmail(user.getEmail());
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setPassword(user.getPassword());
		userModel.setPhone(user.getPhone());
		userModel.setAdmin(user.isAdmin());
		userModel.setPlanner(user.isPlanner());
		userModel.setUser(user.isUser());

		return userModel;
	}
	
	private User toUser(UserModel userModel){

		if(userModel == null){
			return null;
		}
		User user = new User();
		user.setId(userModel.getId());
		user.setAddress(userModel.getAddress());
		user.setEmail(userModel.getEmail());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setPassword(userModel.getPassword());
		user.setPhone(userModel.getPhone());
		user.setAdmin(userModel.isAdmin());
		user.setPlanner(userModel.isPlanner());
		user.setUser(userModel.isUser());

		return user;
	}

	
}
