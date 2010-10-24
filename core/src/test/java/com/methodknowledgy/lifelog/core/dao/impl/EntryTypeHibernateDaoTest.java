package com.methodknowledgy.lifelog.core.dao.impl;

import java.util.Collection;

import com.methodknowledgy.lifelog.commons.dao.EntryTypeDao;
import com.methodknowledgy.lifelog.commons.domain.EntryType;
import com.methodknowledgy.lifelog.commons.domain.impl.EntryTypeImpl;

public class EntryTypeHibernateDaoTest extends AbstractHibernateDaoTest {

	private EntryTypeDao dao;

	public void setEntryTypeDao(EntryTypeDao entryTypeDao) {
		this.dao = entryTypeDao;
	}

	@Override
	public void testGetOne() {
		// Test a basic entry type
		EntryType entryType = dao.getById(1L);

		assertNotNull(entryType);
		assertEquals(1L, entryType.getId().longValue());
		assertEquals("String", entryType.getName());
		assertFalse(entryType.isComplex());
		assertNull(entryType.getTemplate());
		assertNotNull(entryType.getCreationDate());
		assertEquals(entryType.getCreationDate(), entryType
				.getLastModifiedDate());
		assertEquals(0, entryType.getAttributeTypes().size());

		// Test a complex entry type
		entryType = dao.getById(2L);

		assertNotNull(entryType);
		assertEquals(2L, entryType.getId().longValue());
		assertEquals("Full Name", entryType.getName());
		assertTrue(entryType.isComplex());
		assertEquals("#{First Name} #{Last Name}", entryType.getTemplate());
		assertNotNull(entryType.getCreationDate());
		assertEquals(entryType.getCreationDate(), entryType
				.getLastModifiedDate());
		assertEquals(2, entryType.getAttributeTypes().size());
	}

	@Override
	public void testGetAll() {
		Collection<EntryType> collection = dao.getAll();
		assertEquals(9, collection.size());
	}

	@Override
	public void testSaveOne() {
		EntryTypeImpl et = new EntryTypeImpl();
		et.setName("Number");

		dao.save(et);
		Long id = et.getId();
		assertNotNull(id);

		assertEquals(10, dao.getAll().size());

		et = null;
		et = (EntryTypeImpl) dao.getById(id);
		assertEquals("Number", et.getName());
		assertFalse(et.isComplex());
		assertNull(et.getTemplate());
		assertNotNull(et.getCreationDate());
		assertEquals(et.getCreationDate(), et.getLastModifiedDate());
	}

	@Override
	public void testDeleteOne() {
		deleteEntryTypeWithNoEntriesAndNoAttributeTypesOfEntryType();
		deleteEntryTypeWithEntriesButNoAttributeTypesOfEntryType();
		deleteEntryTypeWithEntriesAndAttributeTypesOfEntryType();
	}

	private void deleteEntryTypeWithNoEntriesAndNoAttributeTypesOfEntryType() {
		Long entryTypeId = 11L;

		EntryType et = dao.getById(entryTypeId);
		dao.delete(et);
		assertEquals(8, dao.getAll().size());

		// Check that the entry_types_attrib_types associations are gone
		int associations = getJdbcTemplate()
				.queryForInt(
						"select count(*) from entry_types_attrib_types where entry_type_id = ?",
						new Object[] { entryTypeId });
		assertEquals(0, associations);

		// There should be no AttributeTypes that are of this EntryType either
		int attribTypes = getJdbcTemplate().queryForInt(
				"select count(*) from attrib_types where entry_type_id = ?",
				new Object[] { entryTypeId });
		assertEquals(0, attribTypes);
	}

	private void deleteEntryTypeWithEntriesButNoAttributeTypesOfEntryType() {
		Long entryTypeId = 10L;

		EntryType et = dao.getById(entryTypeId);
		dao.delete(et);
		assertEquals(7, dao.getAll().size());

		// Check that the Entries of this EntryType are gone
		int entries = getJdbcTemplate().queryForInt(
				"select count(*) from entries where entry_type_id = ?",
				new Object[] { entryTypeId });
		assertEquals(0, entries);
	}

	private void deleteEntryTypeWithEntriesAndAttributeTypesOfEntryType() {
		Long entryTypeId = 2L;

		EntryType et = dao.getById(entryTypeId);
		dao.delete(et);
		assertEquals(6, dao.getAll().size());

		// The following should all be true, now:

		// - EntryType (2) is deleted
		int entryType = getJdbcTemplate().queryForInt(
				"select count(*) from entry_types where id = ?",
				new Object[] { entryTypeId });
		assertEquals(0, entryType);

		// - Associations between EntryType (2) and any AttributeTypes it had
		// are deleted
		int associations = getJdbcTemplate()
				.queryForInt(
						"select count(*) from entry_types_attrib_types where entry_type_id = ?",
						new Object[] { entryTypeId });
		assertEquals(0, associations);

		// - AttributeTypes of type EntryType (2) are deleted
		int attributeTypes = getJdbcTemplate().queryForInt(
				"select count(*) from attrib_types where entry_type_id = ?",
				new Object[] { entryTypeId });
		assertEquals(0, attributeTypes);

		// - Entries of type EntryType (2) are deleted
		int entries = getJdbcTemplate().queryForInt(
				"select count(*) from entries where entry_type_id = ?",
				new Object[] { entryTypeId });
		assertEquals(0, entries);

	}

}
