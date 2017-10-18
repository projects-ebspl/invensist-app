package com.invensist.models;

public class UserSelectionModel extends UserModel {

	private boolean selected;
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getLabel() {
		return getFirstName();
	}

	public void setLabel(String label) {
		setFirstName(label);
	}

}
