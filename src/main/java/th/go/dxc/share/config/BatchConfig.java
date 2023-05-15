package th.go.dxc.share.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {
	
	
	@Autowired
	public BatchConfig(DataSource dataSource) {
		super(dataSource);
	}

	@Primary
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		log.info("init taskExecutor");
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(15);
		taskExecutor.setMaxPoolSize(20);
		taskExecutor.setQueueCapacity(30);
		return taskExecutor;
	}

//	@Primary
//	@Bean
//	public JobLauncher jobLauncher(ThreadPoolTaskExecutor taskExecutor, JobRepository jobRepository) throws Exception {
//		log.info("Init jobLauncher");
//		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//		jobLauncher.setTaskExecutor(taskExecutor);
//		jobLauncher.setJobRepository(jobRepository);
//		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//		jobLauncher.afterPropertiesSet();
//		return jobLauncher;
//	}
	@Override
	protected JobLauncher createJobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setTaskExecutor(taskExecutor());
		jobLauncher.setJobRepository(this.getJobRepository());
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}



}
