package com.methodknowledgy.lifelog.commons.domain.impl;

import java.beans.PropertyChangeSupport;
import java.util.Date;

public abstract class AbstractLifeLogObject {

	private Long id;	
	private Date creationDate;
	private Date lastModifiedDate;
	protected PropertyChangeSupport changes = new PropertyChangeSupport(this);
	
	public AbstractLifeLogObject() {
		creationDate = new Date();
		lastModifiedDate = creationDate;
	}
	
	public Long getId() {
		return id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

}
