package com.invensist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.invensist.dao.mappers.ItemRowMapper;
import com.invensist.entities.Item;

@Repository("inventoryDao")
public class InventoryDao extends BaseDao {

	public List<Item> getAllItems() {
		String sql = "select id, code, description, itemcost, assemblycost, type from Item";
		return getJdbcTemplate().query(sql, new ItemRowMapper());				
	}

	public void saveItem(Item item) {
		if(item.getId() == null) {
			// Save as new 
			String sql = "insert into Item set code = ?, description = ?, itemcost = ?, assemblycost = ?, type = ?";
			getJdbcTemplate().update(sql, 
					new Object[] {item.getItemCode(), item.getDescription(), item.getItemCost(), item.getAssemblyCost(), item.getItemType().name()});
		} else {
			// Save as update
			String sql = "update Item set code = ?, description = ?, itemcost = ?, assemblycost = ?, type = ? where id = ?";
			getJdbcTemplate().update(sql, 
					new Object[] {item.getItemCode(), item.getDescription(), item.getItemCost(), item.getAssemblyCost(), 
					item.getItemType().name(), item.getId()});
		}
	}

}
