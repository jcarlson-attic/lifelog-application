package com.methodknowledgy.lifelog.core.dao.impl;

import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.methodknowledgy.lifelog.commons.domain.Attribute;
import com.methodknowledgy.lifelog.commons.domain.impl.AbstractAttribute;

public class AttributeHibernateDao extends HibernateDaoSupport implements
		com.methodknowledgy.lifelog.commons.dao.AttributeDao {

	public void delete(Attribute attribute) {
		getHibernateTemplate().delete(attribute);
	}

	@SuppressWarnings("unchecked")
	public Collection<Attribute> getAll() {
		return (Collection<Attribute>) getHibernateTemplate().loadAll(
				Attribute.class);
	}

	public Attribute getById(Long id) {
		return (Attribute) getHibernateTemplate().get(AbstractAttribute.class,
				id);
	}

	public void save(Attribute attribute) {
		getHibernateTemplate().saveOrUpdate(attribute);
	}

}
