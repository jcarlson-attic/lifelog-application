package com.methodknowledgy.lifelog.commons.domain.impl;

import java.util.Collection;
import java.util.HashSet;

import com.methodknowledgy.lifelog.commons.domain.Attribute;
import com.methodknowledgy.lifelog.commons.domain.AttributeType;
import com.methodknowledgy.lifelog.commons.domain.Entry;
import com.methodknowledgy.lifelog.commons.domain.EntryType;

public class EntryImpl extends AbstractLifeLogObject implements Entry {

	private Collection<Attribute> attributes;
	private EntryType entryType;
	private Collection<EntryAttribute> referringAttributes;
	
	public EntryImpl() {
		attributes = new HashSet<Attribute>();
	}
	
	public Collection<Attribute> getAttributes() {
		return attributes;
	}
	
	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	
	public void removeAttribute(Attribute attribute) {
		attributes.remove(attribute);
	}

	public EntryType getEntryType() {
		return entryType;
	}
	
	public void setEntryType(EntryType entryType) {
		this.entryType = entryType;
	}

	public Collection<AttributeType> getMissing() {
		Collection<AttributeType> missing = new HashSet<AttributeType>();
		
		Collection<AttributeType> expectedTypes = getEntryType().getAttributeTypes();
		
		// For each attribute type...
		for (AttributeType expected : expectedTypes) {
			// Try to find a matching attribute
			for (Attribute check : attributes) {
				// Does the current attribute match the current type?
				if (expected == check.getAttributeType()) {
					// An attribute was found for this type.
					// Break and go to the next type
					expected = null;
					break;
				}
			}
			if (null != expected) {
				missing.add(expected);
			}

		}
		
		return missing;
	}

	public boolean isComplete() {
		Collection<AttributeType> expectedTypes = getEntryType().getAttributeTypes();
		
		if (expectedTypes.size() > attributes.size()) {
			return false;
		}
		
		// Let's assume completeness to start
		boolean complete = true;
		
		// For each attribute type...
		for (AttributeType expected : expectedTypes) {
			// Try to find a matching attribute
			for (Attribute check : attributes) {
				// Does the current attribute match the current type?
				if (expected == check.getAttributeType()) {
					// An attribute was found for this type.
					// Break and go to the next type
					complete = true;
					break;
				} else {
					// A matching attribute was not found
					// We'll keep looking...
					complete = false;
				}
			}
			// We've looked through all the attributes for this type.
			// Did we find a matching attribute for the type?
			if (!complete) {
				// No? Then we're not complete. We can stop now.
				return false;
			}
		}
		// If we got here, we're complete.
		return true;
	}
	
	public Collection<EntryAttribute> getReferringAttributes() {
		return referringAttributes;
	}

	public void setReferringAttributes(
			Collection<EntryAttribute> referringAttributes) {
		this.referringAttributes = referringAttributes;
	}

	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
