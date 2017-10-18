package com.invensist.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.invensist.entities.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
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

}
