package com.invensist.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.invensist.entities.Store;
import com.invensist.enums.StoreType;


public class StoreRowMapper implements RowMapper<Store> {

	@Override
	public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
		Store st = new Store();
		st.setId(rs.getInt("id"));
		st.setName(rs.getString("name"));
		st.setStoreType(StoreType.valueOf(rs.getString("type")));
		return st;
	}
}
