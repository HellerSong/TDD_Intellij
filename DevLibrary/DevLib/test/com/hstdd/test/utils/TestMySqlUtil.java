package com.hstdd.test.utils;

import com.hstdd.utils.DevLog;
import com.hstdd.utils.MySqlUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class TestMySqlUtil {
	@Test
	public void test_config_file_path() {
		System.out.println(MySqlUtil.connUrl);
	}

	@Test
	public void test_read_db_simple_data() {
		Connection conn = MySqlUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from t_user where userId=?";
			DevLog.write(sql);

			if (conn == null)
				conn = MySqlUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "2");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.print(rs.getInt("userId") + "--");
				System.out.print(rs.getString("name"));
			}
		} catch (Exception e) {
			DevLog.write(e);
		} finally {
			MySqlUtil.close(pstmt, rs, conn);
		}

	}
}
