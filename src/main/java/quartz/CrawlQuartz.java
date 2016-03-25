package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * Created by jixiaonan on 16/3/7.
 */
public class CrawlQuartz {
    public void start() throws SchedulerException
    {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //CrawlJob是实现了org.quartz.Job的类
        JobDetail jobDetail = new JobDetail("jobDetail", "jobDetailGroup", CrawlJob.class);
        CronTrigger cronTrigger = new CronTrigger("cronTrigger", "triggerGroup");
        try {
            CronExpression cexp = new CronExpression("0 30 16 * * ?");
            cronTrigger.setCronExpression(cexp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
    }
}
