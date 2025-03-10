package org.daragon.quartz;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest1 {

	/**
	 * @param args
	 * @throws SchedulerException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws SchedulerException,
			InterruptedException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		JobDetail job = newJob(DumbJob.class).withIdentity("job1", "group1")
				.usingJobData("jobSays", "hello World")
				.usingJobData("myFloatValue", 3.22f).build();
		Trigger trigger = newTrigger()
				.withIdentity("myTrigger", "group1")
				.startAt( nextGivenSecondDate(new Date(), 10))
				.withSchedule(
						simpleSchedule().withIntervalInSeconds(20).repeatForever()).build();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();

		// scheduler.shutdown();
	}
}