
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import quartz.CrawlQuartz;
import quartz.EmailQuartz;

import java.io.IOException;


/**
 * Created by jixiaonan on 16/2/23.
 */
public abstract class CrawlerBase {
    /**
     *
     * @param args
     */
    public static void main(String[] args) throws WriteException, IOException, BiffException {
        try{
            CrawlQuartz crawlQuartz = new CrawlQuartz();
            crawlQuartz.start();
            EmailQuartz emailQuartz = new EmailQuartz();
            emailQuartz.start();
        }
        catch(Exception e){
            System.out.println("定时任务出错："+e);
        }
    }
}
