package com.hstdd.test.dao;

import com.hstdd.dao.BaseDao;
import com.hstdd.test.pojo.UserPojo;
import com.hstdd.test.pojo.UserTypePojo;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class TestBaseDao {

	@Test
	public void test_get_data() {
		//UserDao dao = new UserDao();
		BaseDao<UserPojo> dao = new BaseDao<>(UserPojo.class);

		UserPojo pojo = dao.getById(2);
		System.out.println("Total Record Count: " + dao.totalCount);
		System.out.print(pojo.getUserId() + "--");
		System.out.print(pojo.getUserName() + "--");
		System.out.print(pojo.getIsMarried() + "--");
		System.out.print(pojo.getBirthday());

	}

	@Test
	public void test_get_data_from_user_type_dao() {
		BaseDao<UserTypePojo> dao = new BaseDao<>(UserTypePojo.class);

		UserTypePojo pojo = dao.getById(2);
		System.out.println("Total Record Count: " + dao.totalCount);
		System.out.print(pojo.getUserTypeId() + "--");
		System.out.print(pojo.getUserTypeName());
	}

	@Test
	public void test_update_data_by_user_dao() {
		BaseDao<UserPojo> dao = new BaseDao<>(UserPojo.class);

		dao.update(4, "userTypeId", "3");
		int userTypeId = dao.getById(4).getUserTypeId();
		System.out.println(userTypeId);
		Assert.assertEquals(3, userTypeId);
	}

}
