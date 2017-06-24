package database;

import java.util.*;
import java.sql.*;

/**
 * 
 * @author Vladimir
 */
public class OracleDB {
    /**     
     * ORACLE_URL=10.10.112.171:49161
     * ORACLE_USER=system
     * ORACLE_PASSWORD=oracle
     *
     * @throws Exception IO Ex
     */
    public static final void countVisitor() throws Exception {
        Map<String, String> p = System.getenv();
        Connection connection = null;
        PreparedStatement ps = null;
        String url = "jdbc:oracle:thin:@" + p.get("ORACLE_URL") + ":XE";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String username = p.get("ORACLE_USER");
        String pwd = p.get("ORACLE_PASSWORD");
        int countPage = 0;
        System.out.println(url);
        System.out.println(username);
        System.out.println(pwd);
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, pwd);            
            try {
                ps = connection.prepareStatement("select count(*) from t_count");
                ps.executeQuery();
                ps.close();
            }
            catch (SQLException ee) {
                System.out.println("First create t_count table...");

                ps = connection.
                    prepareStatement("CREATE TABLE t_count (id number(10) NOT NULL, ccount number(10) DEFAULT 0)");
                ps.executeUpdate();
                ps.close();

                ps = connection.prepareStatement("insert into t_count values (1, 0)");
                ps.executeUpdate();
                ps.close();
            }

            ps = connection.prepareStatement("update t_count set ccount=ccount+1");
            ps.executeUpdate();
            ps.close();

            ps = connection.prepareStatement("select ccount from t_count WHERE ROWNUM = 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    countPage = rs.getInt(1);
                }
            }
            ps.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
//        out.println("您是第" + countPage + "位访问客户！");        //前台页面输出
//        out.println("IP地址：" + InetAddress.getLocalHost().getHostAddress());        //前台页面输出
    }
}
