package utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixiaonan on 16/3/7.
 */
public class DataUtil {
    public static List<Elements> processData(Document document){
        List<Elements> elementsList = new ArrayList<Elements>();
        Element statPeriod = document.getElementById("StockStatistic_day");
        Elements periodList = statPeriod.children();
        List<String> ids = new ArrayList<String>();
        ids.add("StockStatistic-cont1");
        ids.add("StockStatistic-cont2");
        ids.add("StockStatistic-cont3");
        ids.add("StockStatistic-cont4");
        Integer period = 0;
        for (String id:ids){
            Element traderStatistic = document.getElementById(id);
            Elements stockELements = traderStatistic.select("tr");
            for (Element stock:stockELements.subList(1, stockELements.size())){
                Elements elements = new Elements();
                Elements tds = stock.select("td");

                elements.add(0, tds.get(0));
                Elements textTitles  =  tds.select("a[href]");
                elements.add(1, textTitles.get(0));
                elements.add(2, textTitles.get(1));
                elements.add(3, textTitles.get(2));
                Elements tradeNumber = traderStatistic.select("tr").select("td.tdnumber").select("span");
                elements.add(4, tradeNumber.get(0));
                elements.add(5, tds.select("span").get(1));
                elements.add(6, tradeNumber.get(1));
                elements.add(7, tradeNumber.get(2));
                elements.add(8, tradeNumber.get(3));
                elements.add(9, periodList.get(period));
                elementsList.add(elements);
            }
            period = period + 1;
        }

        return elementsList;
    }
}
