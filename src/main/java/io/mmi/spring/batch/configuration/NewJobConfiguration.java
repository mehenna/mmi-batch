package io.mmi.spring.batch.configuration;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewJobConfiguration {
	private final Log logger = LogFactory.getLog(getClass());

//	@Autowired
//	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step1() {
		logger.info("Creating firt step ...");
		return stepBuilderFactory.get("step1").tasklet((contribution, chunkContext) -> {
			System.out.println("Step number One");
			return RepeatStatus.FINISHED;
		}).build();
	}
	
	@Bean
	public Step step2() {
		logger.info("Creating firt step ...");
		return stepBuilderFactory.get("step2").tasklet((contribution, chunkContext) -> {
			System.out.println("Step number Two");
			return RepeatStatus.FINISHED;
		}).build();
	}
	
	@Bean
	public Step step3() {
		logger.info("Creating firt step ..."); 
		Tasklet tasklet = (contribution, chunkContext)->{System.out.println("Step number Three");
														 return RepeatStatus.FINISHED;};
		return stepBuilderFactory.get("step3").tasklet(tasklet).build();
	}
	
	@Bean
	public Flow  foot() {
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("foot");
		flowBuilder.start(step1()).next(step3()).end();
		return flowBuilder.build();
		
	}
	
//	@Bean
//	public Job helloWordJob() {
//	
//		return jobBuilderFactory
//				.get("HelloworldJob"+ ZonedDateTime.now(ZoneId.of("Pacific/Auckland" )).truncatedTo(ChronoUnit.MILLIS))
//				.start(step3())
//				.on("COMPLETED").to(step2())
//				.from(step2()).on("COMPLETED").stop()
//				.from(step3()).end()
//				.build();
//	}

}
