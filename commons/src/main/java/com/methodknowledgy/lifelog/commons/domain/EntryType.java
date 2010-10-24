package com.methodknowledgy.lifelog.commons.domain;

import java.util.Collection;
import java.util.Date;

public interface EntryType {

	Long getId();

	String getName();
	
	void setName(String name);
	
	boolean isComplex();
	
	void setComplex(boolean complex);
	
	String getTemplate();
	
	void setTemplate(String template);
	
	Collection<AttributeType> getAttributeTypes();
	
	void addAttributeType(AttributeType attributeType);
	
	void removeAttributeType(AttributeType attributeType);
	
	Date getCreationDate();
	
	Date getLastModifiedDate();
	
}
