package com.methodknowledgy.lifelog.core.dao.impl;

import java.util.Collection;

import com.methodknowledgy.lifelog.commons.dao.AttributeDao;
import com.methodknowledgy.lifelog.commons.dao.EntryDao;
import com.methodknowledgy.lifelog.commons.dao.EntryTypeDao;
import com.methodknowledgy.lifelog.commons.domain.Entry;
import com.methodknowledgy.lifelog.commons.domain.EntryType;
import com.methodknowledgy.lifelog.commons.domain.impl.EntryImpl;

public class EntryHibernateDaoTest extends AbstractHibernateDaoTest {

	private EntryDao dao;
	private EntryTypeDao etDao;
	private AttributeDao aDao;

	public void setEntryDao(EntryDao entryDao) {
		this.dao = entryDao;
	}

	public void setEntryTypeDao(EntryTypeDao entryTypeDao) {
		this.etDao = entryTypeDao;
	}
	
	public void setAttributeDao(AttributeDao aDao) {
		this.aDao = aDao;
	}

	@Override
	public void testGetOne() {
		Entry e = dao.getById(1L);
		assertNotNull(e);
		assertEquals(1L, e.getId().longValue());
		assertNotNull(e.getEntryType());
		assertNotNull(e.getCreationDate());
		assertEquals(e.getCreationDate(), e.getLastModifiedDate());
		assertEquals(2, e.getAttributes().size());
		assertTrue(e.isComplete());
		assertEquals(0, e.getMissing().size());
		
		e = dao.getById(2L);
		assertNotNull(e);
		assertEquals(2L, e.getId().longValue());
		assertNotNull(e.getEntryType());
		assertNotNull(e.getCreationDate());
		assertEquals(e.getCreationDate(), e.getLastModifiedDate());
		assertEquals(5, e.getAttributes().size());
		assertTrue(e.isComplete());
		assertEquals(0, e.getMissing().size());
	}

	@Override
	public void testGetAll() {
		Collection<Entry> collection = dao.getAll();
		assertEquals(8, collection.size());
	}

	@Override
	public void testSaveOne() {
		EntryType et = etDao.getById(2L);

		EntryImpl e = new EntryImpl();
		e.setEntryType(et);

		dao.save(e);
		Long id = e.getId();
		assertNotNull(id);
		
		assertEquals(9, dao.getAll().size());
		
		e = (EntryImpl) dao.getById(id);
		assertNotNull(e.getEntryType());
		assertNotNull(e.getCreationDate());
		assertEquals(e.getCreationDate(), e.getLastModifiedDate());
		assertEquals(0, e.getAttributes().size());
		assertEquals(2, e.getMissing().size());
		
	}

	@Override
	public void testDeleteOne() {
		Entry e;
		
		// First, test an entry that does not have EntryAttributes
		e = dao.getById(1L);
		
		assertEquals(2, e.getAttributes().size());
		
		dao.delete(e);
		e = null;
		
		assertEquals(7, dao.getAll().size());
		// Another Entry has an EntryAttribute that references this Entry.
		// Cascading deletes will remove that attribute in addition to this entry's attributes.
		assertEquals(22, aDao.getAll().size());
		// In fact, it was attrib (3) that had a reference to entry (1). So attrib (3) should not exist any more.
		assertNull(aDao.getById(3L));
		
		// Now test an entry that does have EntryAttributes
		// Note that one of the 5 attributes for this entry has just been deleted!
		e = dao.getById(2L);
		assertEquals(4, e.getAttributes().size());
		
		/* A few additional things should be true at this point:
		 * * No row in attribs with id in (1,2,3)
		 * * No row in entry_values with id 3
		 * * No row in attrib_values with id in (1,2)
		 */
		int attribs = getJdbcTemplate().queryForInt("select count(*) from attribs where id in (?,?,?)", new Object[] {1,2,3});
		int entryValues = getJdbcTemplate().queryForInt("select count(*) from entry_values where attrib_id in (?)", new Object[] {3});
		int attribValues = getJdbcTemplate().queryForInt("select count(*) from attrib_values where attrib_id in (?,?)", new Object[] {1,2});		
		assertEquals(0,attribs);
		assertEquals(0,entryValues);
		assertEquals(0,attribValues);
	}

}
