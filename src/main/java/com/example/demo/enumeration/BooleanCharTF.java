package com.example.demo.enumeration;

public enum BooleanCharTF {

	T("Sim"),
	F("Não");
	
	private String string;
	
	private BooleanCharTF(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}
	
}
