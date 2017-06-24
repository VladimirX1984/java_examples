package database;/*
 * Created on 13-8-8
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright @2013 the original author or authors.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Description of this file.
 *
 * @author XiongNeng
 * @version 1.0
 * @since 13-8-8
 */
public class EveryDay {
    private static final String SELECT_BASE_SQL = "SELECT DISTINCT order_info.biz_transaction_id bizId,"
        + " insurance_application.effect_date effectDate"
        + " FROM "
        + " order_payment_info,"
        + " order_info,"
        + " insurance_application"
        + " WHERE "
        + " order_info.biz_transaction_id=insurance_application.biz_transaction_id"
        + " AND"
        + " order_info.id=order_payment_info.order_id"
        + " AND"
        + " order_info.payment_status<>'PAI'"
        + " AND"
        + " order_payment_info.payment_method='Mobile99bill'"
        + " AND order_payment_info.payee=order_payment_info.payment_target"
        + " AND order_payment_info.`status`='Avail'"
        + " AND insurance_application.effect_date < NOW()";

    private static final String SELECT_ONE_DAY_SQL = SELECT_BASE_SQL
        + " AND insurance_application.effect_date > adddate(now(),-1)"
        + " ORDER BY insurance_application.effect_date DESC";
    private static final String SELECT_THREE_DAY_SQL = SELECT_BASE_SQL
        + " AND insurance_application.effect_date > adddate(now(),-3)"
        + " ORDER BY insurance_application.effect_date DESC";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        Calendar now = Calendar.getInstance();
        String outFile = args[0];
        System.out.println("---" + outFile);
        String url = "jdbc:mysql://121.14.57.226:33060/b2b_biz1?useUnicode=true&amp;characterEncoding=UTF-8";
        String username = "zenglb01";
        String password = "zenglb123";
        Connection cn = null;
        Statement stm = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, username, password);
            stm = cn.createStatement();
            ResultSet rs;
            if (isMonday(now)) {
                rs = stm.executeQuery(SELECT_THREE_DAY_SQL);
            }
            else {
                rs = stm.executeQuery(SELECT_ONE_DAY_SQL);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
            writer.append("----------------------这是系统自动发送的邮件正文！-------------------");
            writer.newLine();
            writer.append("----------------------所有过期的单的询价号和有效日期为：--------------");
            writer.newLine();
            writer.newLine();
            while (rs.next()) {
                String bizId = rs.getString("bizId");
                String effectDate = rs.getString("effectDate");
                System.out.println(bizId + effectDate);
                writer.append(bizId).append(",").append(effectDate);
                writer.newLine();
            }
            writer.flush();
            writer.close();

        }
        finally {
            if (stm != null) {
                stm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    /**
     * 获取指定时间所在星期的第一天，即周一
     *
     * @param calendar
     * @return
     */
    public static boolean isMonday(Calendar calendar) {
        boolean result = false;
        if (calendar != null && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            result = true;
        }
        return result;
    }

}
