package com.invensist.enums;

public enum ItemType {
	single("Single"), combo("Combo");
	
	
	private final String value;

	private ItemType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static ItemType geTypeByValue(String value) {
		for (ItemType type : values()) {
			if(type.value.equals(value)) {
				return type;
			}
		}
		return null;
	}
	
	
}
