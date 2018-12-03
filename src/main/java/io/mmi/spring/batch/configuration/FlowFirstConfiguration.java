package io.mmi.spring.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowFirstConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step myStep() {
		Tasklet tasklet = (contribution,chunkContext) -> {
			System.out.println("myStep was executed ...");
			return RepeatStatus.FINISHED;
		};
		return stepBuilderFactory.get("myStep").tasklet(tasklet).build();
	}
	
	@Bean
	public Job flowFirstJob(Flow flow) {
		return jobBuilderFactory.get("flowFirstJob-1m")
				.start(flow)
				.next(myStep())
				.end()
				.build();
	}
	
	@Bean
	public Job flowEndJob(Flow flow) {
		return jobBuilderFactory.get("flowENdJob-2l")
				.start(myStep())
				.on("COMPLETED").to(flow)
				.end()
				.build();
	}
}
