package com.invensist.models;

public class StoreSelectionModel extends StoreModel {
	
	private boolean selected;
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getLabel() {
		return getName();
	}

	public void setLabel(String label) {
		setName(label);
	}
}
