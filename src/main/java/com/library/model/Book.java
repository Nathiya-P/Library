package com.library.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

import com.library.util.Date;

@XmlRootElement(name = "BOOK")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

	@XmlElement(name = "NAME")
	private String bookName;

	@XmlElement(name = "AUTHOR")
	private String author;

	@XmlElement(name = "CATEGORY")
	private String category;

	@XmlElement(name = "ISSUEDATE")
	private String dateOfIssue;

	@XmlElement(name = "ISSUENUM")
	private Integer numberOfTimes;

	private LocalDate issueDate;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public Integer getNumberOfTimes() {
		return numberOfTimes;
	}

	public void setNumberOfTimes(Integer numberOfTimes) {
		this.numberOfTimes = numberOfTimes;
	}

	public LocalDate getIssueDate() {
		issueDate = Date.toDate(dateOfIssue);
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

}
