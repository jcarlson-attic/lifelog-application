package com.methodknowledgy.lifelog.core.dao.impl;

import com.methodknowledgy.lifelog.commons.dao.AttributeDao;
import com.methodknowledgy.lifelog.commons.dao.AttributeTypeDao;
import com.methodknowledgy.lifelog.commons.dao.EntryDao;
import com.methodknowledgy.lifelog.commons.domain.Attribute;
import com.methodknowledgy.lifelog.commons.domain.AttributeType;
import com.methodknowledgy.lifelog.commons.domain.Entry;
import com.methodknowledgy.lifelog.commons.domain.impl.EntryAttribute;
import com.methodknowledgy.lifelog.commons.domain.impl.ValueAttribute;

public class AttributeHibernateDaoTest extends AbstractHibernateDaoTest {

	private AttributeDao dao;
	private AttributeTypeDao atDao;
	private EntryDao eDao;

	public void setAttributeDao(AttributeDao dao) {
		this.dao = dao;
	}

	public void setAttributeTypeDao(AttributeTypeDao atDao) {
		this.atDao = atDao;
	}

	public void setEntryDao(EntryDao eDao) {
		this.eDao = eDao;
	}

	@Override
	public void testGetOne() {
		getValueAttribute();
		getEntryAttribute();
	}

	private void getValueAttribute() {
		// ValueAttribute Test
		Attribute a = dao.getById(1L);
		assertNotNull(a);
		assertEquals("Jarrod", a.toString());
	}

	private void getEntryAttribute() {

		// EntryAttribute Test
		Attribute a = dao.getById(3L);
		assertNotNull(a);
		try {
			a.toString();
			fail();
		} catch (UnsupportedOperationException e) {
		}
	}

	@Override
	public void testGetAll() {
		int count = dao.getAll().size();
		assertEquals(25, count);
	}

	@Override
	public void testSaveOne() {
		Entry e = eDao.getById(2L);
		AttributeType at1 = atDao.getById(12L);

		ValueAttribute a1 = new ValueAttribute();
		a1.setAttributeType(at1);
		a1.setEntry(e);
		a1.setValue("Test");
		dao.save((Attribute) a1);

		assertEquals(26, dao.getAll().size());
		int attribValue = getJdbcTemplate().queryForInt(
				"select count(*) from attrib_values where attrib_id = ?",
				new Object[] { a1.getId() });
		assertEquals(1, attribValue);

		AttributeType at2 = atDao.getById(10L);
		Entry target = eDao.getById(8L);

		EntryAttribute a2 = new EntryAttribute();
		a2.setAttributeType(at2);
		a2.setEntry(e);
		a2.setEntry(target);
		dao.save((Attribute) a2);

		assertEquals(27, dao.getAll().size());
		int entryValue = getJdbcTemplate().queryForInt(
				"select count(*) from entry_values where attrib_id = ?",
				new Object[] { a2.getId() });
		assertEquals(1, entryValue);
	}

	@Override
	public void testDeleteOne() {
		Long attributeId = 31L;
		Attribute a1 = dao.getById(attributeId);
		dao.delete(a1);
		
		assertEquals(24, dao.getAll().size());
		int attribValue = getJdbcTemplate().queryForInt(
				"select count(*) from attrib_values where attrib_id = ?",
				new Object[] { attributeId });
		assertEquals(0, attribValue);
		
		attributeId = 3L;
		Attribute a2 = dao.getById(attributeId);
		dao.delete(a2);
		
		assertEquals(23, dao.getAll().size());
		int entryValue = getJdbcTemplate().queryForInt(
				"select count(*) from entry_values where attrib_id = ?",
				new Object[] { attributeId });
		assertEquals(0, entryValue);
	}

}
