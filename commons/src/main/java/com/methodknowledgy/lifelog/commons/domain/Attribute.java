package com.methodknowledgy.lifelog.commons.domain;

import java.util.Date;

public interface Attribute {
	
	Long getId();
	
	Entry getEntry();
	
	void setEntry(Entry entry);
	
	AttributeType getAttributeType();
	
	void setAttributeType(AttributeType attributeType);
	
	Date getCreationDate();
	
	Date getLastModifiedDate();
	
}
