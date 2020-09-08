package com.library;

import static com.library.util.Constants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.library.exception.FileReaderException;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.util.BookType;

@RunWith(MockitoJUnitRunner.class)
public final class LibraryTest {

	private static Logger LOGGER =  Logger.getLogger(LibraryTest.class);
	private BookService bookService;
	private List<Book> books;
	private List<String> typeOfBooks;
	
	@Before
    public void init() throws FileReaderException {
		bookService = new BookService();
		books = bookService.getAlltheBooks();
		typeOfBooks = new ArrayList<String>();
		String[] types = {"Adventure", "Non-Fiction", "Mystery", "History", "Fiction"};
		typeOfBooks = Arrays.asList(types);
    }
	
	@Test
    public void countAllTypesOfBooksTest() {
        
		LOGGER.info("countTypeOfBooksTest starts");
		Map<String, Integer> booksCount = bookService.countAllTypesOfBooks(books);
		
		Assert.assertTrue(typeOfBooks.size() == booksCount.size());
		
		booksCount.forEach((k,v) -> {
			BookType bookType = BookType.getBookType(k);
			Integer count = booksCount.get(k);
			Assert.assertTrue(count == bookType.getNumber());
		});
				
		LOGGER.info("countTypeOfBooksTest ends");
    }
	
	@Test
	public void bookHasHighestReaderTest() {
		
		LOGGER.info("bookHasHighestReaderTest starts");
		Book book = bookService.bookHasHighestReader(books);
		
		Assert.assertEquals(book.getNumberOfTimes().intValue(), NUMBER);
		Assert.assertEquals(book.getAuthor(), AUTHOR);
		Assert.assertEquals(book.getBookName(), NAME);
	}
	
	@Test
	public void bookIssuedOn6thJan2020Test() {
		
		LOGGER.info("bookHasHighestReaderTest starts");
		List<String> bookNames = bookService.bookIssuedOn6thJan2020(books);
		
		Assert.assertEquals(bookNames.size(), BOOKS_ISSUED_ON_6TH);
	}
	
}
