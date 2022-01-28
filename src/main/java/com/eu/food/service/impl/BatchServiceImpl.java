package com.eu.food.service.impl;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eu.food.service.BatchService;
import com.eu.food.util.Utils;

@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    @Qualifier("vendorJob")
    Job vendorKeeperJob;
    
    @Autowired
    @Qualifier("productJob")
    Job productKeeperJob;
	
	@Override
	public ResponseEntity<String> handleProduct(String source) {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("source", source)
				.toJobParameters();
		try {
			jobLauncher.run(productKeeperJob, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobParametersInvalidException e) {
			e.printStackTrace();
			return Utils.makeStatus("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
			return Utils.makeStatus("A job instance already exists and is complete, change the parameters.", HttpStatus.BAD_REQUEST);
		}
        
		return Utils.makeStatus("Batch job has been invoked", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> handleVendor(String source) {

        JobParameters jobParameters = new JobParametersBuilder()
        								.addString("source", source)
        								.toJobParameters();
        try {
			jobLauncher.run(vendorKeeperJob, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobParametersInvalidException e) {
			e.printStackTrace();
			return Utils.makeStatus("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
			return Utils.makeStatus("A job instance already exists and is complete, change the parameters.", HttpStatus.BAD_REQUEST);
		}
        
        return Utils.makeStatus("Batch job has been invoked", HttpStatus.OK);
	}

}
