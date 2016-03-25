package utils;

import domain.Stock;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.select.Elements;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jixiaonan on 16/3/4.
 */
public class MybatisUtil {
    public static Stock getStock() {
        SqlSession session = loadConf();
        /**
         * 映射sql的标识字符串，
         * mapper.StockMapper是StockMapper.xml文件中mapper标签的namespace属性的值，
         * selectStock是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "mapper.StockMapper.selectStock";//映射sql的标识字符串
        //执行查询返回一个唯一stock对象的sql
        Stock stock = session.selectOne(statement, "1353");
        System.out.println(stock.getCode());
        return stock;
    }

    /**
     * 查询列表
     * @return
     */
    public static List<Stock> getStockList() {
        SqlSession session = loadConf();
        /**
         * 映射sql的标识字符串，
         * mapper.StockMapper是StockMapper.xml文件中mapper标签的namespace属性的值，
         * selectStock是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "mapper.StockMapper.selectStock";//映射sql的标识字符串
        //执行查询返回一个唯一stock对象的sql
        List<Stock> stockList = session.selectList(statement);
        System.out.println(stockList.size());
        return stockList;
    }

    /**
     * 批量插入
     * @param list
     * @return
     */
    public static int insertStocks(List<Elements> list){
        SqlSession session = loadConf();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time=sdf.format(new Date());
        String statement = "mapper.StockMapper.insertStocks";
        List<Map<String,Object>> maps = new ArrayList<Map<String, Object>>();
        for (Elements elements:list){
            Map<String ,Object> map = new HashMap<String, Object>();
            map.put("sortNumber", elements.get(0).html());
            map.put("code", elements.get(1).html());
            map.put("name", elements.get(2).html());
            map.put("link", elements.get(3).html());
            map.put("turnover", elements.get(4).html());
            map.put("rankingFreq", elements.get(5).html());
            map.put("buyAmount", elements.get(6).html());
            map.put("sellAmount", elements.get(7).html());
            map.put("netAmount", elements.get(8).html());
            map.put("statPeriod", elements.get(9).text());
            map.put("addDate", time);
            maps.add(map);
        }
        return session.insert(statement, maps);
    }
    /**
     * 加载配置文件
     */
    private static SqlSession loadConf(){
        String resource = "mybatis-config.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        return sessionFactory.openSession(true);
    }
}
