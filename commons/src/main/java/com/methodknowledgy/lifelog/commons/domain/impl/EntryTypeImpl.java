package com.methodknowledgy.lifelog.commons.domain.impl;

import java.util.Collection;
import java.util.HashSet;

import com.methodknowledgy.lifelog.commons.domain.AttributeType;
import com.methodknowledgy.lifelog.commons.domain.Entry;
import com.methodknowledgy.lifelog.commons.domain.EntryType;

public class EntryTypeImpl extends AbstractLifeLogObject implements EntryType {

	private String name;
	private String template;
	private boolean complex;
	private Collection<AttributeType> attributeTypes;
	protected Collection<AttributeType> referringAttributeTypes;
	protected Collection<Entry> referringEntries;
	
	public EntryTypeImpl() {
		attributeTypes = new HashSet<AttributeType>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public boolean isComplex() {
		return complex;
	}

	public void setComplex(boolean complex) {
		this.complex = complex;
	}

	public Collection<AttributeType> getAttributeTypes() {
		return attributeTypes;
	}

	public void addAttributeType(AttributeType attributeType) {
		attributeTypes.add(attributeType);
	}

	public void removeAttributeType(AttributeType attributeType) {
		attributeTypes.remove(attributeType);
	}

}
