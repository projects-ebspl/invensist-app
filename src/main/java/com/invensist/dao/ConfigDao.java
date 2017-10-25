package com.invensist.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invensist.dao.mappers.AssociateRowMapper;
import com.invensist.dao.mappers.ItemRowMapper;
import com.invensist.dao.mappers.StoreRowMapper;
import com.invensist.dao.mappers.UserRowMapper;
import com.invensist.entities.Associate;
import com.invensist.entities.Item;
import com.invensist.entities.Store;
import com.invensist.entities.User;
import com.invensist.enums.StoreType;

@Repository("configDao")
public class ConfigDao extends BaseDao {
	
	public List<User> getAllUsers() {
		String sql = "select id as userid, firstname, lastname, email, phone, address, password,"
				+ "roleAdmin, rolePlanner, roleUser from Users order by id";
		return getJdbcTemplate().query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				return populateUser(rs, new User());
			}});
	}
	
	@Transactional("transactionManager")
	public User getUserByEmail(String email) {
		try{
			String sql = "select id as userid, firstname, lastname, email, phone, address, password, "
					+ "roleAdmin, rolePlanner, roleUser from Users where email = '" + email + "'";
			return getJdbcTemplate().queryForObject(sql, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int arg1) throws SQLException {
					return populateUser(rs, new User());
				}});
		}catch(EmptyResultDataAccessException e){			
			return null;
		}		
	}
	
	@Transactional("transactionManager")
	public void saveUser(User user) {
		if(user.getId() == null) {
			// Save as new user 
			String sql = "insert into Users set firstName = ?, lastName = ?, email = ?, phone = ?, address = ?,"
					+ " password = ?, roleAdmin = ?, rolePlanner = ?, roleUser = ?";
			getJdbcTemplate().update(sql, 
					new Object[] {user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getAddress(),
							user.getPassword(), user.isAdmin(), user.isPlanner(), user.isUser()});						
			
		} else {
			// Save as update
			String sql = "update Users set  firstName = ?, lastName = ?, phone = ?, address = ?"
					+ ", roleAdmin = ?, rolePlanner = ?, roleUser = ? where id = ?";
			getJdbcTemplate().update(sql, 
					new Object[] {user.getFirstName(), user.getLastName(), user.getPhone(), user.getAddress(), user.isAdmin(), 
							user.isPlanner(), user.isUser(), user.getId()});
		}
	}
	
	public void deleteUserById(int id) {
		String sql = "delete from Users where id = ?";
		getJdbcTemplate().update(sql, new Object[] {id});
	}
	
	public User getUserById(int id) {
		try{
			String sql = "select id as userid, firstname, lastname, email, phone, address, password, "
					+ "roleAdmin, rolePlanner, roleUser from Users where id = " + id;
			return getJdbcTemplate().queryForObject(sql, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int arg1) throws SQLException {
					return populateUser(rs, new User());
				}});
		} catch(EmptyResultDataAccessException e){
			
			return null;
		}
		
	}
	
	private User populateUser(ResultSet rs, User user) throws SQLException {
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setFirstName(rs.getString("firstName"));
		user.setId(rs.getInt("userid"));
		user.setLastName(rs.getString("lastName"));
		user.setPhone(rs.getString("phone"));
		user.setPassword(rs.getString("password"));
		user.setAdmin(rs.getBoolean("roleAdmin"));
		user.setPlanner(rs.getBoolean("RolePlanner"));
		user.setUser(rs.getBoolean("roleUser"));
		return user;
	}
	
	public void resetPassword(int userId, String password) {
		String sql = "update Users set password = ? where id = ?";
		getJdbcTemplate().update(sql, new Object[] {password, userId});
	}

	public void resetPassword(String email, String password) {
		String sql = "update Users set password = ? where email = ?";
		getJdbcTemplate().update(sql, new Object[] {password, email});
		
	}
	
	public List<Store> getAllStores() {
		String sql = "select id, name, type from Stores";
		return getJdbcTemplate().query(sql, new StoreRowMapper());
	}
	
	public List<Store> getStoresForUser(int userId) {
		String sql = "select S.id, S.name, S.type from Stores S, Users U, UserStoreMapping US where U.id = US.user and S.id = US.store and U.id = ?";
		return getJdbcTemplate().query(sql, new Object[] {userId}, new StoreRowMapper());
	}
	
	public List<User> getUsersForStore(int storeId) {
		String sql = "select U.id as userid, firstname, lastname, email, phone, address, password, roleAdmin, rolePlanner, roleUser "
				+ "from Users U, Stores S, UserStoreMapping US where U.id = US.user and S.id = US.store and S.id = ?";
		return getJdbcTemplate().query(sql, new Object[] {storeId}, new UserRowMapper());
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
			getJdbcTemplate().update(sql, new Object[] {store.getName(), store.getStoreType().name()});
		} else {
			// Save as update
			String sql = "update Stores set name = ?, type = ? where id = ?";
			getJdbcTemplate().update(sql, new Object[] {store.getName(), store.getStoreType().name(), store.getId()});
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
	
	public void assignStores(final int userId, final List<Integer> storeIds) {
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

	public List<Associate> getAssociates() {
		String sql = "select id, name, email, phone, address, notes, client, vendour from Associates";
		return getJdbcTemplate().query(sql, new AssociateRowMapper());
	}

	@Transactional("transactionManager")
	public void saveAssociate(Associate associate) {
		if(associate.getId() == null) {
			// Save as new Associate 
			String sql = "insert into Associates set name = ?, email = ?, phone = ?, address = ?,"
					+ " client = ?, vendour = ?, notes = ?";
			getJdbcTemplate().update(sql, 
					new Object[] {associate.getName(), associate.getEmail(), associate.getPhone(), associate.getAddress(),
							associate.getClient() ? 1 : 0, associate.getVendour() ? 1 : 0, associate.getNotes()});						
			
		} else {
			// Save as update
			String sql = "update Associates set  name = ?, email = ?, phone = ?, address = ?"
					+ ", client = ?, vendour = ?, notes = ? where id = ?";
			getJdbcTemplate().update(sql, 
					new Object[] {associate.getName(), associate.getEmail(), associate.getPhone(), associate.getAddress(), 
							associate.getClient() ? 1 : 0, associate.getVendour() ? 1 : 0, associate.getNotes(), associate.getId()}); 
		}
	}
}
