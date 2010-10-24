package com.methodknowledgy.lifelog.core.dao.impl;

import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.methodknowledgy.lifelog.commons.dao.AttributeTypeDao;
import com.methodknowledgy.lifelog.commons.domain.AttributeType;
import com.methodknowledgy.lifelog.commons.domain.impl.AttributeTypeImpl;

public class AttributeTypeHibernateDao extends HibernateDaoSupport implements
		AttributeTypeDao {

	public void delete(AttributeType attributeType) {
		getHibernateTemplate().delete(attributeType);
	}

	@SuppressWarnings("unchecked")
	public Collection<AttributeType> getAll() {
		return (Collection<AttributeType>) getHibernateTemplate().loadAll(
				AttributeTypeImpl.class);
	}

	public AttributeType getById(Long id) {
		return (AttributeType) getHibernateTemplate().get(AttributeTypeImpl.class,
				id);
	}

	public void save(AttributeType attributeType) {
		getHibernateTemplate().saveOrUpdate(attributeType);
	}
	
}
