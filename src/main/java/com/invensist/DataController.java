package com.invensist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.invensist.models.MessageModel;
import com.invensist.models.UserModel;

@RestController
public class DataController {
	
	@GetMapping(value = "/users.json", produces = "application/json")
	public @ResponseBody List<UserModel> getUsers() {
		ArrayList<UserModel> users = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			UserModel model = new UserModel();
			model.setId(i);
			model.setFirstName("F" + i);
			model.setLastName("L" + i);
			model.setEmail("email" + i);
			model.setPhone("phone" + i);
			model.setAddress("address" + i);
			users.add(model);
		}
		return users;
	}
	
	@PostMapping(value = "/delete-user.json", produces = "application/json")
	public MessageModel deleteUser(@RequestParam Object userId) {
		// TODO Delete
		return new MessageModel().withMessage("Done...");
	}
	
	
	
}
