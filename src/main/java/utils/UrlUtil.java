package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jixiaonan on 16/2/28.
 */
public class UrlUtil {
    public static String getDTUrl(){
        String baseUrl = "http://data.eastmoney.com/stock/lhb";
        String dtUrl = baseUrl + "/%s.html";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time=sdf.format(new Date());
        Calendar cd = Calendar.getInstance();
        try {
            cd.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cd.add(Calendar.DATE, -1);
        Date date = cd.getTime();
        System.out.print(sdf.format(date));
        dtUrl = String.format(dtUrl, sdf.format(date));

        return dtUrl;
    }
}
