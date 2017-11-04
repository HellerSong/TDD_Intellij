package com.hstdd.test.dao;

import com.hstdd.test.pojo.VUserPojo;
import org.junit.Test;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class TestBaseViewDao {

	@Test
	public void test_get_user_view_data() {
		VUserPojoDao dao = new VUserPojoDao();

		//dao.initializeTableJoinString();
		VUserPojo pojo = dao.getById(1);
		System.out.println(pojo.getUserName() + "--" + pojo.getUserTypeName());
	}
}
