package com.eu.food.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.eu.food.entity.Product;

@Component
public class ProductKeeperJob extends JobExecutionListenerSupport {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${input.file2}") 
	Resource resource;
	
	@Autowired
	ProductWriter writer;
	
	@Autowired
	ProductProcessor processor;
	
	@Bean(name = "productJob")
	public Job vendorKeeperJob() {

		Step step = stepBuilderFactory.get("step-1")
				.<Product, Product> chunk(2)
				.reader(new ProductReader(resource))
				.processor(processor)
				.writer(writer)
				.build();
		
		Job job = jobBuilderFactory.get("product-job")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();

		return job;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH PRODUCT COMPLETED SUCCESSFULLY!");
		}
	}

}
