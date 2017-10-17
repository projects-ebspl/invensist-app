package com.invensist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.invensist.models.AssociateModel;
import com.invensist.models.ItemModel;
import com.invensist.models.MessageModel;
import com.invensist.models.StoreModel;
import com.invensist.models.UserModel;
import com.invensist.service.ConfigService;
import com.invensist.service.InventoryService;

@RestController
public class DataController {

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping(value = "/users.json", produces = "application/json")
	public @ResponseBody List<UserModel> getUsers() {
		return configService.getUsers();
	}
	
	@PostMapping("/save-user.json")
	public List<UserModel> saveUser(@ModelAttribute("user")UserModel user, BindingResult bindingResult) {
		configService.saveUser(user);
		return getUsers();
	}
	
	@PostMapping(value = "/delete-user.json", produces = "application/json")
	public List<UserModel> deleteUser(@RequestParam Integer userId) {
		configService.deleteUser(userId);
		return getUsers();
	}
	
	@GetMapping(value = "/stores.json", produces = "application/json")
	public @ResponseBody List<StoreModel> getStores() {
		return inventoryService.getStores();
	}
	
	@GetMapping(value = "/stores-for-user.json", produces = "application/json")
	public @ResponseBody List<StoreModel> getStoresForUser(@RequestParam("userId") Integer userId) {
		return inventoryService.getStoresForUser(userId);
	}
	
	@GetMapping(value = "/check-valid-email.json", produces = "application/json")
	public void checkValidEmail(@RequestParam("email") String email) {
		System.out.println("YES");
	}

	@PostMapping(value = "/delete-store.json", produces = "application/json")
	public MessageModel deleteStore(@RequestParam Integer storeId) {
		// TODO Delete
		return new MessageModel().withMessage("Store is deleted successfully");
	}

	@GetMapping(value = "/associates.json", produces = "application/json")
	public @ResponseBody List<AssociateModel> getAssociates() {
		ArrayList<AssociateModel> associates = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			AssociateModel model = new AssociateModel();
			model.setId(i);
			model.setName("NAME" + i);
			model.setEmail("email" + i);
			model.setPhone("phone" + i);
			model.setAddress("address" + i);
			associates.add(model);
		}
		return associates;
	}
	
	@PostMapping(value = "/delete-associate.json", produces = "application/json")
	public MessageModel deleteAssociate(@RequestParam Integer associateId) {
		// TODO Delete
		return new MessageModel().withMessage("Associate is deleted successfully");
	}

	@GetMapping(value = "/items.json", produces = "application/json")
	public @ResponseBody List<ItemModel> getItems() {
		 return inventoryService.getItems();
	}

	@PostMapping(value = "/delete-item.json", produces = "application/json")
	public MessageModel deleteItem(@RequestParam Integer itemId) {
		// TODO Delete
		return new MessageModel().withMessage("Item is deleted successfully");
	}
}
