package quartz;

import domain.Stock;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import service.DT_Crawler;
import utils.EmailUtil;
import utils.ExcelUtil;

import java.io.File;
import java.util.List;

/**
 * Created by jixiaonan on 16/3/9.
 */
public class EmailJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            List<Stock> list = DT_Crawler.getData();
            File file = ExcelUtil.exportStock(list);
            EmailUtil email = new EmailUtil(true);
            email.doSendHtmlEmail("个股龙虎榜股票数据", "数据在附件中,请注意查收", "947459175@qq.com", file);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("定时发送邮件出错" + e);
        }
    }
}
