package com.invensist.controller;

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
import com.invensist.models.StoreSelectionModel;
import com.invensist.models.UserModel;
import com.invensist.models.UserSelectionModel;
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
		return configService.getStores();
	}
	
	@GetMapping(value = "/stores-for-user.json", produces = "application/json")
	public @ResponseBody List<StoreModel> getStoresForUser(@RequestParam("userId") Integer userId) {
		return configService.getStoresForUser(userId);
	}
	
	@GetMapping(value = "/users-for-store.json", produces = "application/json")
	public @ResponseBody List<UserModel> getUsersForStore(@RequestParam("storeId") Integer storeId) {
		return configService.getUsersForStore(storeId);
	}
	
	@GetMapping(value = "/store-selections-for-user.json", produces = "application/json")
	public List<StoreSelectionModel> getStoreSelectionsForUser(@RequestParam("userId") Integer userId) {
		return configService.getStoreSelections(userId);
	}
	
	@GetMapping(value = "/user-selections-for-store.json", produces = "application/json")
	public List<UserSelectionModel> getUserSelectionsForStore(@RequestParam("userId") Integer storeId) {
		return configService.getUserSelections(storeId);
	}
	
	@PostMapping(value = "/save-store-assignments.json", produces = "application/json")
	public MessageModel saveStoreAssignments(@RequestParam("userId") Integer userId, 
				@RequestParam("storeIds") String storeIds) {
		configService.assignStores(userId, storeIds);
		return new MessageModel().withMessage("Under Construction");
		
	}
	
	@GetMapping(value = "/check-valid-email.json", produces = "application/json")
	public void checkValidEmail(@RequestParam("email") String email) {
		System.out.println("YES");
	}
	
	@PostMapping(value = "/delete-store.json", produces = "application/json")
	public List<StoreModel> deleteStore(@RequestParam Integer storeId) {		
		configService.deleteStore(storeId);
		return getStores();
	}
	
	@PostMapping(value="/add-store.json")
	public List<StoreModel> saveOrUpdateStore(@ModelAttribute("store")StoreModel store, BindingResult bindingResult){
		configService.saveOrUpdateStore(store);
		return getStores();
	}
	@GetMapping(value = "/associates.json", produces = "application/json")
	public @ResponseBody List<AssociateModel> getAssociates() {
		return configService.getAssociates();
	}
	@PostMapping(value = "/delete-associate.json", produces = "application/json")
	public MessageModel deleteAssociate(@RequestParam Integer associateId) {
		configService.deleteAssociate(associateId);
		return new MessageModel().withMessage("Associate is deleted successfully");
	}
	@GetMapping(value = "/items.json", produces = "application/json")
	public @ResponseBody List<ItemModel> getItems() {
		return inventoryService.getItems();
	}
		
	@PostMapping(value="/add-item.json")
	public List<ItemModel> saveOrUpdateItem(@ModelAttribute("item")ItemModel item, BindingResult bindingResult){
		inventoryService.saveOrUpdateItem(item);
		return getItems();
	}
	
	@PostMapping(value = "/delete-item.json", produces = "application/json")
	public List<ItemModel> deleteItem(@RequestParam Integer itemId) {
		inventoryService.deleteItem(itemId);
		return getItems();
	}
	
	@PostMapping("/save-associate.json")
	public void saveAssociate(@ModelAttribute("associate")AssociateModel associate, BindingResult bindingResult) {
		configService.saveAssociate(associate);
	}
}
