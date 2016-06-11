package com.unittest;

import com.dao.RoleinfoDao;
import com.pojo.RoleinfoPojo;
import org.junit.Test;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class TestBaseDao {
    @Test
    public void test_read_data() {
        RoleinfoDao dao = new RoleinfoDao();

        RoleinfoPojo pojo = dao.getById(5);

        System.out.println(pojo.getRoleName());
        System.out.println(pojo.getRoleId());
    }
}