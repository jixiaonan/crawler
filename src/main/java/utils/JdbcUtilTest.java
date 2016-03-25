package utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by jixiaonan on 16/2/29.
 */
public class JdbcUtilTest {
    public static String dbName = "DragonTiger";
    public static String passwrod = "root";
    public static String userName = "root";
    public static String url = "jdbc:mysql://localhost:3306/" + dbName;


    public static void insert(List<Elements> elementsList) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time=sdf.format(new Date());
        Connection conn = DriverManager.getConnection(url, userName,
                passwrod);
        PreparedStatement ps = null;
        for (Elements elements:elementsList){
            String writeSql = "insert into stock_Statistic (sortNumber, code, name, link, turnover, rankingFreq, " +
                    "buyAmount, sellAmount, netAmount, statPeriod, addDate) values(%s,%s,'%s','%s',%s,%s,%s,%s,%s,'%s','%s')";

            List<String> stockList = new ArrayList<String>();
            for (Element stock:elements){
                String data = stock.html();
                stockList.add(data);
            }
            writeSql = String.format(writeSql,stockList.get(0), stockList.get(1),stockList.get(2),stockList.get(3),
                    stockList.get(4),stockList.get(5),stockList.get(6),stockList.get(7),stockList.get(8),
                    elements.get(9).text(), time);
            ps = conn.prepareStatement(writeSql);
            ps.executeUpdate();

        }
        closeAll(null, ps, conn);

    }

    public static void select(String tableName) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time=sdf.format(new Date());
        String readSql = "select * from %s";
        Connection conn = DriverManager.getConnection(url, userName,
                passwrod);
        PreparedStatement ps = null;
        List<String> stockList = new ArrayList<String>();

        readSql = String.format(readSql, tableName);
        ps = conn.prepareStatement(readSql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("id : " + rs.getInt(1) );
        }
        closeAll(rs, ps, conn);

    }
    public static void closeAll(ResultSet rs , PreparedStatement ps, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    }
