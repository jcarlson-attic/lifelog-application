package com.methodknowledgy.lifelog.core.dao.impl;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public abstract class AbstractHibernateDaoTest extends
		AbstractTransactionalDataSourceSpringContextTests {

	@Override
	public String[] getConfigLocations() {
		return new String[] { "classpath:lifelog-data.xml",
				"classpath:lifelog-data-test.xml" };
	}
	
	@Test public abstract void testGetOne();
	@Test public abstract void testGetAll();
	@Test public abstract void testSaveOne();
	@Test public abstract void testDeleteOne();

}
