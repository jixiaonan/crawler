package quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import service.DT_Crawler;

/**
 * Created by jixiaonan on 16/3/8.
 */
public class CrawlJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            DT_Crawler.insertData();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("定时爬取任务出错" + e);
        }
    }
}
