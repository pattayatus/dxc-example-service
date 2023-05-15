package th.go.dxc.share.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAsync
@Slf4j
public class SpringAsyncConfig {
	public static final String QUERY_THREAD_POOL_TASK_EXECUTOR = "queryThreadPoolTaskExecutor";
	public static final String QUERY_THREAD_PREFIX = "Query-";
	@Bean(name = QUERY_THREAD_POOL_TASK_EXECUTOR)
	public Executor threadPoolTaskExecutor() {
//		https://engineering.zalando.com/posts/2019/04/how-to-set-an-ideal-thread-pool-size.html		
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    Integer corePoolSize = Runtime.getRuntime().availableProcessors();
	    log.debug("current number of cores is {}",corePoolSize);
	    if(corePoolSize==null  || corePoolSize<=0)corePoolSize=2;
	    // web service call not cpu bound so add 10 times
	    corePoolSize = corePoolSize*100;
//	    Number of threads = Number of Available Cores * (1 + Wait time / Service time)
	    Integer waitTimeInMillis = 100;
	    Integer serviceTimeInMillis = 5;	    
	    Integer maxPoolSize = (corePoolSize)*(1+(waitTimeInMillis/serviceTimeInMillis));
	    Integer queueCapacity = 500;
	    
	    log.info("create {} with corePoolSize={}, maxPoolSize={}, queueCapacity={}, prefix={}",QUERY_THREAD_POOL_TASK_EXECUTOR,corePoolSize,maxPoolSize,queueCapacity,QUERY_THREAD_PREFIX);
	    executor.setCorePoolSize(corePoolSize);
	    executor.setMaxPoolSize(maxPoolSize);
	    executor.setQueueCapacity(queueCapacity);
	    executor.setThreadNamePrefix(QUERY_THREAD_PREFIX);
	    executor.initialize();
	    return executor;
	}
}
