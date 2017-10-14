package com.invensist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.invensist.enums.ItemType;
import com.invensist.enums.StoreType;
import com.invensist.models.AssociateModel;
import com.invensist.models.ItemModel;
import com.invensist.models.MessageModel;
import com.invensist.models.StoreModel;
import com.invensist.models.UserModel;
import com.invensist.service.ConfigService;

@RestController
public class DataController {

	@Autowired
	private ConfigService configService;
	
	@GetMapping(value = "/users.json", produces = "application/json")
	public @ResponseBody List<UserModel> getUsers() {
		return configService.getUsers();
	}
	
	@PostMapping(value = "/delete-user.json", produces = "application/json")
	public MessageModel deleteUser(@RequestParam Integer userId) {
		// TODO Delete
		return new MessageModel().withMessage("Done...");
	}
	
	@GetMapping(value = "/stores.json", produces = "application/json")
	public @ResponseBody List<StoreModel> getStores() {
		ArrayList<StoreModel> stores = new ArrayList<>();
		
		StoreModel s = new StoreModel();
		s.setId(1);
		s.setName("REG-1");
		s.setStoreType(StoreType.regular);
		stores.add(s);
		
		s = new StoreModel();
		s.setId(2);
		s.setName("REJ-1");
		s.setStoreType(StoreType.rejection);
		stores.add(s);

		s = new StoreModel();
		s.setId(3);
		s.setName("ASM-1");
		s.setStoreType(StoreType.assembly);
		stores.add(s);
		
		s = new StoreModel();
		s.setId(4);
		s.setName("BRK-1");
		s.setStoreType(StoreType.breakup);
		stores.add(s);

		s = new StoreModel();
		s.setId(5);
		s.setName("SHT-1");
		s.setStoreType(StoreType.shortage);
		stores.add(s);

		s = new StoreModel();
		s.setId(6);
		s.setName("WST-1");
		s.setStoreType(StoreType.wastage);
		stores.add(s);

		return stores;
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
		ArrayList<ItemModel> items = new ArrayList<>();
		
		ItemModel item = new ItemModel();
		item.setId(1);
		item.setItemcost(10d);
		item.setCode("S0001");
		item.setDescription("D0001");
		item.setItemType(ItemType.single);
		items.add(item);
		
		item = new ItemModel();
		item.setId(2);
		item.setItemcost(32d);
		item.setCode("S0002");
		item.setDescription("D0002");
		item.setItemType(ItemType.single);
		items.add(item);
		
		item = new ItemModel();
		item.setId(3);
		item.setItemcost(43d);
		item.setCode("S0003");
		item.setDescription("D0003");
		item.setItemType(ItemType.single);
		items.add(item);
		
		item = new ItemModel();
		item.setId(4);
		item.setAssemblycost(3d);
		item.setCode("C0001");
		item.setDescription("D0004");
		item.setItemType(ItemType.combo);
		items.add(item);
		
		item = new ItemModel();
		item.setId(5);
		item.setAssemblycost(7d);
		item.setCode("C0002");
		item.setDescription("D0005");
		item.setItemType(ItemType.combo);
		items.add(item);
		
		item = new ItemModel();
		item.setId(6);
		item.setAssemblycost(11d);
		item.setCode("C0003");
		item.setDescription("D0006");
		item.setItemType(ItemType.combo);
		items.add(item);
		return items;
	}

	@PostMapping(value = "/delete-item.json", produces = "application/json")
	public MessageModel deleteItem(@RequestParam Integer itemId) {
		// TODO Delete
		return new MessageModel().withMessage("Item is deleted successfully");
	}
}
