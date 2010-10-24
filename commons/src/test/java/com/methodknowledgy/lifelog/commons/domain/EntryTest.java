/**
 * 
 */
package com.methodknowledgy.lifelog.commons.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.methodknowledgy.lifelog.commons.domain.impl.AttributeTypeImpl;
import com.methodknowledgy.lifelog.commons.domain.impl.EntryImpl;
import com.methodknowledgy.lifelog.commons.domain.impl.EntryTypeImpl;
import com.methodknowledgy.lifelog.commons.domain.impl.ValueAttribute;

public class EntryTest {

	private static EntryTypeImpl etString;
	private static AttributeTypeImpl atFirstName;
	private static AttributeTypeImpl atLastName;
	private static EntryTypeImpl etFullName;
	
	private EntryImpl entry;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		etString = new EntryTypeImpl();
		etString.setName("String");
		etString.setComplex(false);
		
		atFirstName = new AttributeTypeImpl();
		atFirstName.setName("First Name");
		atFirstName.setEntryType(etString);
		
		atLastName = new AttributeTypeImpl();
		atLastName.setName("Last Name");
		atLastName.setEntryType(etString);
		
		etFullName = new EntryTypeImpl();
		etFullName.setName("Full Name");
		etFullName.setComplex(true);
		etFullName.addAttributeType(atFirstName);
		etFullName.addAttributeType(atLastName);

	}
	
	@Before
	public void setUp() throws Exception {
		entry = new EntryImpl();
		entry.setEntryType(etFullName);

	}

	@After
	public void tearDown() throws Exception {
		entry = null;
	}

	@Ignore
	@Test
	public void testToString() {
		// TODO: Test templating
	}

	@Test
	public void testIsComplete() {
		ValueAttribute fName = new ValueAttribute();
		fName.setAttributeType(atFirstName);
		entry.addAttribute(fName);
		
		ValueAttribute lName = new ValueAttribute();
		lName.setAttributeType(atLastName);
		entry.addAttribute(lName);
		
		assertTrue(entry.isComplete());
	}

	@Test
	public void testIsNotComplete() {
		ValueAttribute lName = new ValueAttribute();
		lName.setAttributeType(atLastName);
		entry.addAttribute(lName);
		
		assertFalse(entry.isComplete());
	}

	@Test
	public void testMissingNone() {
		ValueAttribute fName = new ValueAttribute();
		fName.setAttributeType(atFirstName);
		entry.addAttribute(fName);
		
		ValueAttribute lName = new ValueAttribute();
		lName.setAttributeType(atLastName);
		entry.addAttribute(lName);
		
		assertEquals(0,entry.getMissing().size());
		
	}

	@Test
	public void testMissingOne() {
		ValueAttribute lName = new ValueAttribute();
		lName.setAttributeType(atLastName);
		entry.addAttribute(lName);
		
		assertEquals(1,entry.getMissing().size());
		
		assertTrue(entry.getMissing().contains(atFirstName));
	}
	
}
