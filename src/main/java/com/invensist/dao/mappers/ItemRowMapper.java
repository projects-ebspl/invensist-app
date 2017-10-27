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
		st.setItemCode(rs.getString("code"));
		st.setDescription(rs.getString("description"));
		st.setItemCost(rs.getDouble("itemcost"));
		st.setAssemblyCost(rs.getDouble("assemblycost"));
		st.setItemType(ItemType.valueOf(rs.getString("type")));
		return st;
	}
}
