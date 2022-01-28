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

import com.eu.food.entity.Vendor;


@Component
public class VendorKeeperJob extends JobExecutionListenerSupport {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${input.file1}") 
	Resource resource;
	
	@Autowired
	VendorProcessor processor;
	
	@Autowired
	VendorWriter writer;
	
	@Bean(name = "vendorJob")
	public Job vendorKeeperJob() {

		Step step = stepBuilderFactory.get("step-1")
				.<Vendor, Vendor> chunk(2)
				.reader(new VendorReader(resource))
				.processor(processor)
				.writer(writer)
				.build();
		
		Job job = jobBuilderFactory.get("vendor-job")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();

		return job;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH VENDOR COMPLETED SUCCESSFULLY!");
		}
	}

}
