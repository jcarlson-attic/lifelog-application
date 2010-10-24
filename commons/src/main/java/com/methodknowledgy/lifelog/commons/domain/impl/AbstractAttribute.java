package com.methodknowledgy.lifelog.commons.domain.impl;

import com.methodknowledgy.lifelog.commons.domain.Attribute;
import com.methodknowledgy.lifelog.commons.domain.AttributeType;
import com.methodknowledgy.lifelog.commons.domain.Entry;

public abstract class AbstractAttribute extends AbstractLifeLogObject implements Attribute {

	private AttributeType attributeType;
	private Entry entry;

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		return getValue();
	}
	
	public abstract String getValue();
}
