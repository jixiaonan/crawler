package service;

import domain.Stock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import utils.DataUtil;
import utils.MybatisUtil;
import java.util.List;

/**
 * Created by jixiaonan on 16/2/23.
 */

public class DT_Crawler {
    private  static final String url = "http://data.eastmoney.com/stock/lhb.html";

    /**
     * 插入数据
     * @return
     * @throws Exception
     */
    public static int insertData() throws Exception{
        Document document = Jsoup.connect(url).get();
        List<Elements> elementsList = DataUtil.processData(document);

        return MybatisUtil.insertStocks(elementsList);
    }

    /**
     * 获取股票数据列表
     * @return
     */
    public static List<Stock> getData(){
        return MybatisUtil.getStockList();
    }
}
