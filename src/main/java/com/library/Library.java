package com.library;

import java.util.List;

import org.apache.log4j.Logger;

import com.library.exception.FileReaderException;
import com.library.model.Book;
import com.library.service.BookService;

public class Library {

	private static Logger LOGGER =  Logger.getLogger(Library.class);
	
	public static void main(String[] args) {
		
		LOGGER.info("Starting library app");
		BookService bookService = new BookService();
		
		try {
			List<Book> books = bookService.getAlltheBooks();
			bookService.countAllTypesOfBooks(books);
			bookService.bookHasHighestReader(books);
			bookService.bookIssuedOn6thJan2020(books);
		} 
		catch (FileReaderException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
}
