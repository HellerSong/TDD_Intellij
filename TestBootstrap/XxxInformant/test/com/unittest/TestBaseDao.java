package com.unittest;

import com.dao.JbkjxslyDao;
import com.dao.RoleinfoDao;
import com.pojo.JbkjxslyPojo;
import com.pojo.RoleinfoPojo;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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

    @Test
    public void test_read_clue_data() {
        JbkjxslyDao dao = new JbkjxslyDao();
        JbkjxslyPojo pojo = dao.getById(23);
        System.out.println(pojo.getJBKJXSLY_CLZT());
    }

    @Test
    public void test_current_datetime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        //随便怎么转都可以的
        //Date date = format.parse(time);
        Timestamp t = new Timestamp(System.currentTimeMillis());
        String dateString = formatter.format(t);

        System.out.println(dateString);
    }
}
