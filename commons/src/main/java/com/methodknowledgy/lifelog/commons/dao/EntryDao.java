package com.methodknowledgy.lifelog.commons.dao;

import java.util.Collection;

import com.methodknowledgy.lifelog.commons.domain.Entry;

public interface EntryDao {

	Collection<Entry> getAll();
	
	Entry getById(Long id);
	
	void save(Entry entry);
	
	void delete(Entry entry);
	
}
