package com.invensist.enums;

public enum StoreType {

	assembly("Assembly"),
	breakup("Break Up"),
	regular("Regular"),
	rejection("Rejection"),
	shortage("Shortage"),
	wastage("Wastage")
	;
	
	private final String value;
	
	private StoreType(String type) {
		this.value = type;
	}

	public String getValue() {
		return value;
	}
	
	public static StoreType geTypeByValue(String value) {
		for (StoreType type : values()) {
			if(type.value.equals(value)) {
				return type;
			}
		}
		return null;
	}
}
