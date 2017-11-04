package com.invensist.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.invensist.entities.Associate;

public class AssociateRowMapper implements RowMapper<Associate> {

	@Override
	public Associate mapRow(ResultSet rs, int arg1) throws SQLException {
		Associate a = new Associate();
		a.setAddress(rs.getString("address"));
		a.setClient(rs.getBoolean("client"));
		a.setEmail(rs.getString("email"));
		a.setId(rs.getInt("id"));
		a.setName(rs.getString("name"));
		a.setNotes(rs.getString("notes"));
		a.setPhone(rs.getString("phone"));
		a.setVendour(rs.getBoolean("vendour"));
		return a;
	}

}
