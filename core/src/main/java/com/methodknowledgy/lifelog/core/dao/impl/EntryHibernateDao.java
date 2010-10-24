package com.methodknowledgy.lifelog.core.dao.impl;

import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.methodknowledgy.lifelog.commons.dao.EntryDao;
import com.methodknowledgy.lifelog.commons.domain.Entry;
import com.methodknowledgy.lifelog.commons.domain.impl.EntryImpl;

public class EntryHibernateDao extends HibernateDaoSupport implements EntryDao {

	@SuppressWarnings("unchecked")
	public void delete(Entry entry) {
		getHibernateTemplate().delete(entry);
	}

	@SuppressWarnings("unchecked")
	public Collection<Entry> getAll() {
		return (Collection<Entry>) getHibernateTemplate().loadAll(
				EntryImpl.class);
	}

	public Entry getById(Long id) {
		return (Entry) getHibernateTemplate().get(EntryImpl.class, id);
	}

	public void save(Entry entry) {
		getHibernateTemplate().saveOrUpdate(entry);
	}

}
