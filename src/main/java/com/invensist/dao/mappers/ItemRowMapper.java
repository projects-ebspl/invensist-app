package com.invensist.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.invensist.entities.Item;
import com.invensist.enums.ItemType;


public class ItemRowMapper implements RowMapper<Item> {

	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item st = new Item();
		st.setId(rs.getInt("id"));
		st.setCode(rs.getString("code"));
		st.setDescription(rs.getString("description"));
		st.setItemcost(rs.getDouble("itemcost"));
		st.setAssemblycost(rs.getDouble("assemblycost"));
		st.setType(ItemType.valueOf(rs.getString("type")));
		return st;
	}
}
