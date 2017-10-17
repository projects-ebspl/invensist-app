package com.invensist.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.invensist.dao.mappers.ItemRowMapper;
import com.invensist.dao.mappers.StoreRowMapper;
import com.invensist.entities.Item;
import com.invensist.entities.Store;
import com.invensist.enums.StoreType;

@Repository("inventoryDao")
public class InventoryDao extends BaseDao {
	
	
	public List<Store> getAllStores() {
		String sql = "select id, name, type from Stores";
		return getJdbcTemplate().query(sql, new StoreRowMapper());
	}
	
	public List<Store> getStoresForUser(int userId) {
		String sql = "select S.id, S.name, S.type from Stores S, Users U, UserStoreMapping US where U.id = US.user and S.id = US.store and U.id = ?";
		return getJdbcTemplate().query(sql, new Object[] {userId}, new StoreRowMapper());
	}
	
	public List<Store> getStoresForStoreType(StoreType storeType) {
		String sql = "select id, name, type from Stores where type = ?";
		return getJdbcTemplate().query(sql, new Object[] {storeType.name()}, new StoreRowMapper());
	}

	public Store getStoresForName(String name) {
		String sql = "select id, name, type from Stores where name = ?";
		return getJdbcTemplate().queryForObject(sql, new Object[] {name}, new StoreRowMapper());
	}

	public void saveStore(Store store) {
		if(store.getId() == null) {
			// Save as new 
			String sql = "insert into Stores set name = ?, type = ?";
			getJdbcTemplate().update(sql, new Object[] {store.getName(), store.getType().name()});
		} else {
			// Save as update
			String sql = "update Stores set name = ?, type = ? where id = ?";
			getJdbcTemplate().update(sql, new Object[] {store.getName(), store.getType().name(), store.getId()});
		}
	}
	
	public void deleteStoreById(int id) {
		String sql = "delete from Stores where id = ?";
		getJdbcTemplate().update(sql, new Object[] {id});
	}
	public Store getStoreById(int id) {
		String sql = "select id, name, type from Stores where id = ?";
		return getJdbcTemplate().queryForObject(sql, new Object[] {id}, new StoreRowMapper());
	}
	public void assignStoreToUser(int userId, int storeId) {
		String sql = "insert into UserStoreMapping values (?, ?)";
		getJdbcTemplate().update(sql, new Object[] {userId, storeId});
	}

	public void removeStoreFromUser(int userId, int storeId) {
		String sql = "delete from UserStoreMapping where user = ? and store = ?";
		getJdbcTemplate().update(sql, new Object[] {userId, storeId});
	}
	
	public List<Item> getAllItems() {
		String sql = "select id, code, description, itemcost, assemblycost, type from Item";
		return getJdbcTemplate().query(sql, new ItemRowMapper());				
	}
//	
//	public Item getItemByCode(String code) {
//		String sql = "select id, code, description, itemcost, assemblycost, type from Item where code = ?";
//		return getJdbcTemplate().queryForObject(sql, new Object[] {code}, new ItemRowMapper());
//	}
//
//	public void saveItem(Item item) {
//		if(item.getId() == null) {
//			// Save as new 
//			String sql = "insert into Item set code = ?, description = ?, itemcost = ?, assemblycost = ?, type = ?";
//			getJdbcTemplate().update(sql, 
//					new Object[] {item.getCode(), item.getDescription(), item.getItemcost(), item.getAssemblycost(), item.getType().name()});
//		} else {
//			// Save as update
//			String sql = "update Item set code = ?, description = ?, itemcost = ?, assemblycost = ?, type = ? where id = ?";
//			getJdbcTemplate().update(sql, 
//					new Object[] {item.getCode(), item.getDescription(), item.getItemcost(), item.getAssemblycost(), 
//							item.getType().name(), item.getId()});
//		}
//	}
	
	public void deleteStoreAssignments(int userId) {
		String sql = "delete from UserStoreMapping where user = ?";
		getJdbcTemplate().update(sql, new Object[] {userId});
	}
	
	public void assignStore(final int userId, final List<Integer> storeIds) {
		try {
			// dao.startTransaction();
			deleteStoreAssignments(userId);
			// Insert
			String sql = "insert into UserStoreMapping values (?,?)";
			getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, userId);
					ps.setInt(2, storeIds.get(i));
				}
				
				@Override
				public int getBatchSize() {
					return storeIds.size();
				}
			});
			// dao.commitTransaction();
		} catch (Exception e) {
			// dao.rollbackTransaction();
			throw e;
		} 
		
	}

	
	public void deleteItemById(int id) {
		deleteObjectFromTableById("Item", id);
	}
}
