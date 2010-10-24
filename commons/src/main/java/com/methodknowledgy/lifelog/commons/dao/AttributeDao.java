package com.methodknowledgy.lifelog.commons.dao;

import java.util.Collection;

import com.methodknowledgy.lifelog.commons.domain.Attribute;

public interface AttributeDao {

	Collection<Attribute> getAll();
	
	Attribute getById(Long id);
	
	void save(Attribute attribute);
	
	void delete(Attribute attribute);
	
}
