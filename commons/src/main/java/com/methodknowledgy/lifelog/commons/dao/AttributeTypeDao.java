package com.methodknowledgy.lifelog.commons.dao;

import java.util.Collection;

import com.methodknowledgy.lifelog.commons.domain.AttributeType;

public interface AttributeTypeDao {

	Collection<AttributeType> getAll();
	
	AttributeType getById(Long id);
	
	void save(AttributeType attributeType);
	
	void delete(AttributeType attributeType);
	
}
