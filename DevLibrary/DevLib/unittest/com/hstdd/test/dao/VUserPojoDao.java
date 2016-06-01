package com.hstdd.test.dao;

import com.hstdd.dao.BaseViewDao;
import com.hstdd.test.pojo.VUserPojo;

/**
 * Created by v-hesong on 5/20/2016.
 */
public class VUserPojoDao extends BaseViewDao<VUserPojo> {
	public VUserPojoDao() {
		mainTableName = "t_user";
		mainKeyName = "t_user.userId";
		joinString = "join t_userType on (t_user.userTypeId = t_userType.userTypeId)";
	}

}
