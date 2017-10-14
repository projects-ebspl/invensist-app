package com.invensist.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invensist.entities.User;

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
	
}
