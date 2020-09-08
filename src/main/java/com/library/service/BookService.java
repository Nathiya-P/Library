package com.library.service;

import static com.library.util.Constants.INPUT_FILE;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.library.exception.FileReaderException;
import com.library.model.Book;
import com.library.model.Books;
import com.library.util.Date;

public class BookService {

	private static Logger LOGGER =  Logger.getLogger(BookService.class);
	private static final String CONFIG_FILE = "library.properties";
	private String inputFileName;
	
	public BookService() {
		LOGGER.info("Book service initiated");
		loadConfigFile();
	}
	
	public List<Book> getAlltheBooks() throws FileReaderException {
		
		try {
			LOGGER.info("Start getting all the books");
			File file = new ClassPathResource(inputFileName).getFile();
			
		    JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
		    Books books = (Books) jaxbUnmarshaller.unmarshal(file);
		    LOGGER.info("Number of books - " + books.getBooks().size());
		    return books.getBooks();
		}
		catch(IOException e) {
			e.printStackTrace();
			throw new FileReaderException(e.getMessage());
		}
		catch (JAXBException e) {
			LOGGER.error("Error in reading input XML file - " + e.getMessage());
			e.printStackTrace();
			throw new FileReaderException(e.getMessage());
		}		
	}
	
	public Map<String, Integer> countAllTypesOfBooks(List<Book> books) {
		
		//TODO the solution is commented. Please remove the solution before test
		
		Map<String, Integer> bookTypes = new HashMap<String, Integer>();
		
		books.stream().forEach(b -> {
			Integer bookNum = bookTypes.get(b.getCategory());
			if(bookNum == null) {
				bookTypes.put(b.getCategory(), 1);
			}
			else {
				bookTypes.put(b.getCategory(), bookNum+1);
			}
		});
		
		bookTypes.forEach((k,v) -> LOGGER.info("Category - " + k + " has " + v + " books"));
		
		return bookTypes;
		
		//return null;
	}
	
	public List<String> bookIssuedOn6thJan2020(List<Book> books) {
		
		//TODO the solution is commented. Please remove the solution before test
		
		LocalDate date6th = Date.toDate("06/01/2020");
		List<String> bookNames = new ArrayList<String>();
				
		books.stream().forEach(b -> {
			if(b.getIssueDate() != null && b.getIssueDate().equals(date6th)) {
				bookNames.add(b.getBookName());
			}
		});
		
		return bookNames;
		
		
		//return null;
	}
	
	public Book bookHasHighestReader(List<Book> books) {
		
		//TODO the solution is commented. Please remove the solution before test
		
		books.sort(Comparator.comparing(Book::getNumberOfTimes).reversed());
		LOGGER.info("Book name - " + books.get(0).getBookName() + ", number - " + books.get(0).getNumberOfTimes());
		return books.get(0);
		
		
		
		//return null;
	}
			
	private void loadConfigFile() {
		
		try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            Properties prop = new Properties();
            prop.load(input);
           
            inputFileName = prop.getProperty(INPUT_FILE);
        } 
		catch (IOException ex) {
            ex.printStackTrace();
        }
	}		
}
