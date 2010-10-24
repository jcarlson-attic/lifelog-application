package com.methodknowledgy.lifelog.commons.dao;

import java.util.Collection;

import com.methodknowledgy.lifelog.commons.domain.EntryType;

public interface EntryTypeDao {
	
	Collection<EntryType> getAll();

	EntryType getById(Long id);
	
	void save(EntryType entryType);
	
	void delete(EntryType entryType);
	
}
