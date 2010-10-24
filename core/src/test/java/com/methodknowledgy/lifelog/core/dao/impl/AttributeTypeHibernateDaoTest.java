package com.methodknowledgy.lifelog.core.dao.impl;

import com.methodknowledgy.lifelog.commons.dao.AttributeTypeDao;
import com.methodknowledgy.lifelog.commons.dao.EntryTypeDao;
import com.methodknowledgy.lifelog.commons.domain.AttributeType;
import com.methodknowledgy.lifelog.commons.domain.EntryType;
import com.methodknowledgy.lifelog.commons.domain.impl.AttributeTypeImpl;

public class AttributeTypeHibernateDaoTest extends AbstractHibernateDaoTest {

	private AttributeTypeDao dao;
	private EntryTypeDao etDao;

	public void setDao(AttributeTypeDao dao) {
		this.dao = dao;
	}

	public void setEtDao(EntryTypeDao etDao) {
		this.etDao = etDao;
	}

	@Override
	public void testDeleteOne() {
		Long attributeTypeId = 9L;

		AttributeType at = dao.getById(attributeTypeId);
		dao.delete(at);

		assertEquals(20, dao.getAll().size());

		// Also assert that any attributes of type AttributeType are gone
		int attributes = getJdbcTemplate().queryForInt(
				"select count(*) from attribs where attrib_type_id = ?",
				new Object[] { attributeTypeId });
		assertEquals(0, attributes);

	}

	@Override
	public void testGetAll() {
		int count = dao.getAll().size();
		assertEquals(21, count);

	}

	@Override
	public void testGetOne() {
		AttributeType at = dao.getById(3L);
		assertEquals("Full Name", at.getName());
		assertEquals(etDao.getById(2L), at.getEntryType());

	}

	@Override
	public void testSaveOne() {
		EntryType et = etDao.getById(11L);

		AttributeTypeImpl at = new AttributeTypeImpl();
		at.setCollection(false);
		at.setName("Calendar Date");
		at.setEntryType(et);
		dao.save(at);

		assertNotNull(at.getId());
		assertEquals(22, dao.getAll().size());

		int count = getJdbcTemplate().queryForInt(
				"select count(*) from attrib_types where id = ?",
				new Object[] { at.getId() });
		assertEquals(1, count);
	}

}
