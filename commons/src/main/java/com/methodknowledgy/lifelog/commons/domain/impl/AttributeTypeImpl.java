package com.methodknowledgy.lifelog.commons.domain.impl;

import java.util.Collection;

import com.methodknowledgy.lifelog.commons.domain.Attribute;
import com.methodknowledgy.lifelog.commons.domain.AttributeType;
import com.methodknowledgy.lifelog.commons.domain.EntryType;

public class AttributeTypeImpl extends AbstractLifeLogObject implements
		AttributeType {
	
	private String name;
	private boolean collection;
	private EntryType entryType;
	protected Collection<EntryType> referringEntryTypes;
	protected Collection<Attribute> referringAttributes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCollection() {
		return collection;
	}

	public void setCollection(boolean collection) {
		this.collection = collection;
	}

	public EntryType getEntryType() {
		return entryType;
	}

	public void setEntryType(EntryType entryType) {
		this.entryType = entryType;
	}

}
