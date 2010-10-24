package com.methodknowledgy.lifelog.commons.domain;

import java.util.Date;

public interface AttributeType {

	Long getId();

	String getName();
	
	void setName(String name);
	
	boolean isCollection();
	
	void setCollection(boolean collection);
	
	EntryType getEntryType();
	
	void setEntryType(EntryType entryType);
	
	Date getCreationDate();
	
	Date getLastModifiedDate();
	
}
