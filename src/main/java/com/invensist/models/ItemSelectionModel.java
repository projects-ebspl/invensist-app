package com.invensist.models;

public class ItemSelectionModel extends ItemModel{

	private boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getLabel() {
		return getItemCode();
	}

	public void setLabel(String label) {
		setItemCode(label);
	}

}
