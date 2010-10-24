package com.methodknowledgy.lifelog.commons.domain.impl;

import com.methodknowledgy.lifelog.commons.domain.Entry;

public class EntryAttribute extends AbstractAttribute {

	private Entry entry;
	
	public String getValue() {
		return entry.toString();
	}
	
	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

}
