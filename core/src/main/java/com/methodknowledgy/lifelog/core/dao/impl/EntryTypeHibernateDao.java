package com.methodknowledgy.lifelog.core.dao.impl;

import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.methodknowledgy.lifelog.commons.dao.EntryTypeDao;
import com.methodknowledgy.lifelog.commons.domain.EntryType;
import com.methodknowledgy.lifelog.commons.domain.impl.EntryTypeImpl;

public class EntryTypeHibernateDao extends HibernateDaoSupport implements
		EntryTypeDao {

	public void delete(EntryType entryType) {
		getHibernateTemplate().delete(entryType);
	}

	@SuppressWarnings("unchecked")
	public Collection<EntryType> getAll() {
		return (Collection<EntryType>) getHibernateTemplate().loadAll(
				EntryTypeImpl.class);
	}

	public EntryType getById(Long id) {
		return (EntryType) getHibernateTemplate().get(EntryTypeImpl.class, id);
	}

	public void save(EntryType entryType) {
		getHibernateTemplate().saveOrUpdate(entryType);
	}

}
