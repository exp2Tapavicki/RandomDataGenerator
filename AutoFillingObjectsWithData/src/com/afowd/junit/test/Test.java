package com.afowd.junit.test;

import static org.junit.Assert.assertNotNull;

import com.afowd.ormClasses.Worker;
import com.afowd.util.RandomDataUtil;

public class Test {
//	private TestDataUtil testDataUtil;
//
//	@Before
//	public void setup() {
//		testDataUtil = new TestDataUtil();
//	}
	@org.junit.Test
	public void testIsTrue() {
		assertNotNull(RandomDataUtil.getRandomRangeNumberFilter(null, null));
		assertNotNull(RandomDataUtil.getRandomRangeNumberFilter(null, null));
		RandomDataUtil.setsClassName(Worker.class.getName());
		assertNotNull(RandomDataUtil.getFilledData());
		assertNotNull(RandomDataUtil.getRandomRangeNumberFilter(null, null));
	}
}
