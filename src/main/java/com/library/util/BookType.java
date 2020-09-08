package com.library.util;

import java.util.Arrays;
import java.util.Optional;

public enum BookType {

	FICTION("Fiction", 5),
	NONFICTION("Non-Fiction", 6),
	ADVENTURE("Adventure", 8),
	MYSTERY("Mystery", 6),
	HISTORY("History", 5);
	
	private String type;
	private Integer number;
	
	BookType(String type, Integer number) {
		this.type = type;
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public Integer getNumber() {
		return number;
	}
	
	public static BookType getBookType(String type) {
		
		Optional<BookType> book = Arrays.asList(values()).stream()
									.filter(bt -> bt.getType().equalsIgnoreCase(type))
									.findAny();
		
		if(book != null) {
			return book.get();
		}
		
		return null;
	}
}
