package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by jixiaonan on 16/3/9.
 */
public class EmailQuartz {
    public void start() throws SchedulerException
    {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = new JobDetail("sendEmail", "emailGroup", EmailJob.class);
        CronTrigger cronTrigger = new CronTrigger("emailTrigger", "emailTriggerGroup");
        try {
            CronExpression cexp = new CronExpression("0 48 20 * * ?");
            cronTrigger.setCronExpression(cexp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
    }
}
