package com.methodknowledgy.lifelog.commons.domain;

import java.util.Collection;
import java.util.Date;

public interface Entry {
	
	Long getId();

	EntryType getEntryType();
	
	void setEntryType(EntryType entryType);
	
	Collection<Attribute> getAttributes();
	
	void addAttribute(Attribute attribute);
	
	void removeAttribute(Attribute attribute);
	
	boolean isComplete();
	
	Collection<AttributeType> getMissing();
	
	Date getCreationDate();
	
	Date getLastModifiedDate();
	
}
