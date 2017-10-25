package com.invensist.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.invensist.dao.ConfigDao;
import com.invensist.entities.Associate;
import com.invensist.entities.Item;
import com.invensist.entities.Store;
import com.invensist.entities.User;
import com.invensist.models.AssociateModel;
import com.invensist.models.ItemModel;
import com.invensist.models.StoreModel;
import com.invensist.models.StoreSelectionModel;
import com.invensist.models.UserModel;
import com.invensist.models.UserSelectionModel;

@Service
public class ConfigService extends com.invensist.service.Service {
	
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
	/**
	 * delete user for given id
	 * @param id
	 */
	public void deleteUser(int id){
		configDao.deleteUserById(id);
	}

	
	public void deleteStores(String stringIds) {
		String [] ids = stringIds.split(",");
		for(String id:ids){			
			this.deleteStore(Integer.parseInt(id));			
		}		
	}

	public void deleteStore(int id) {
		configDao.deleteStoreById(id);		
	}
	public void saveOrUpdateStore(StoreModel storeModel) {
		Store store = this.toStore(storeModel);
		configDao.saveStore(store);
	}
	
	public StoreModel getStore(int id) {
		return toStoreModel(configDao.getStoreById(id));
	}
	public List<StoreModel> getStores() {		
		return configDao.getAllStores().stream().map(store -> toStoreModel(store)).collect(Collectors.toList());
	}
	public List<ItemModel> getItems() {		
		return configDao.getAllItems().stream().map(item -> toItemModel(item)).collect(Collectors.toList());
	}
	public List<StoreModel> getStoresForUser(Integer userId) {		
		return configDao.getStoresForUser(userId).stream().map(store -> toStoreModel(store)).collect(Collectors.toList());
	}

	public List<UserModel> getUsersForStore(Integer storeId) {
		return configDao.getUsersForStore(storeId).stream().map(user -> toUserModel(user)).collect(Collectors.toList());
	}

	public List<StoreSelectionModel> getStoreSelections(Integer userId) {
		List<StoreModel> userStores = getStoresForUser(userId);
		return getStores().stream().map(store -> {
			StoreSelectionModel model = new StoreSelectionModel();
			copyProperties(model, store);
			model.setStoreType(store.getStoreType());
			for (StoreModel userStore : userStores) {
				if(userStore.getId() == store.getId()) {
					model.setSelected(true);
					break;
				}
			}
			return model;
		}).collect(Collectors.toList());
	}
	
	public List<UserSelectionModel> getUserSelections(Integer storeId) {
		List<UserModel> storeUsers = getUsersForStore(storeId);
		return getUsers().stream().map(user -> {
			UserSelectionModel model = new UserSelectionModel();
			copyProperties(model, user);
			for (UserModel storeUser : storeUsers) {
				if(storeUser.getId() == user.getId()) {
					model.setSelected(true);
					break;
				}
			}
			return model;
		}).collect(Collectors.toList());
	}
	
	public void assignStores(Integer userId, String storeIdsCSV) {
		String [] storeIds = storeIdsCSV == null || storeIdsCSV.trim().length() == 0 ? new String [0] : storeIdsCSV.split(",");
		configDao.assignStores(userId, Arrays.asList(storeIds).stream().map(id -> Integer.parseInt(id)).collect(Collectors.toList()));
	}
	
	public List<AssociateModel> getAssociates() {
		return configDao.getAssociates().stream().map(associate -> {
			AssociateModel model = new AssociateModel();
			BeanUtils.copyProperties(associate, model);
			return model;
		}).collect(Collectors.toList());
	}

	public void saveAssociate(AssociateModel associateModel) {
		Associate associate = new Associate();
		copyProperties(associate, associateModel);
		configDao.saveAssociate(associate);
	}

	public void deleteAssociate(int id){
		configDao.deleteAssociateById(id);
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
	
	private StoreModel toStoreModel(Store store){
		if(store == null){
			return null;
		}
		StoreModel storeModel = new StoreModel();
		storeModel.setId(store.getId());
		storeModel.setName(store.getName());
		storeModel.setStoreType(store.getStoreType());		
		return storeModel;
	}
	
	private ItemModel toItemModel(Item item){
		if(item == null){
			return null;
		}
		ItemModel itemModel = new ItemModel();
		itemModel.setId(item.getId());
		itemModel.setCode(item.getCode());
		itemModel.setDescription(item.getDescription());
		itemModel.setItemcost(item.getItemcost());
		itemModel.setAssemblycost(item.getAssemblycost());
		itemModel.setItemType(item.getType());
		return itemModel;
	}
	private Store toStore(StoreModel storeModel) {
		if(storeModel == null){
			return null;
		}		
		Store store = new Store();
		store.setId(storeModel.getId());
		store.setName(storeModel.getName());
		store.setStoreType(storeModel.getStoreType());
		return store;
	}
	
}
