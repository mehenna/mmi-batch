package io.mmi.spring.batch.configuration;
import org.springframework.batch.core.Job;
/**
 * 
 */
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	/**
	 * Step number One
	 * @return
	 */
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").tasklet((contribution, chunkContext) -> {
			System.out.println("hello word- on step 1 ");
			return RepeatStatus.FINISHED;
		}).build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step1").tasklet((contribution, chunkContext) -> {
			System.out.println("hello word- on step 2  ");
			return RepeatStatus.FINISHED;
		}).build();
	}
	
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step1").tasklet((contribution, chunkContext) -> {
			System.out.println("hello word on step 3 ");
			return RepeatStatus.FINISHED;
		}).build();
	}
	/**
	 * 
	 * @return
	 */
	@Bean
	public Job helloWordJob() {
		return jobBuilderFactory.get("helloWordJiob-5")
				.start(step1())
				.on("COMPLETED")
				.to(step2())
				.next(step3())
				.on("COMPLETED")
				.to(step1())
				.end()
				.build();
		
	}
}
