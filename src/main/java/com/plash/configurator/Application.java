package com.plash.configurator;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@ComponentScan("com.plash.configurator")
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.plash.configurator.repository"})
@EnableMongoRepositories(basePackages = {"com.plash.configurator.repository.mongo"})
//@EnableElasticsearchRepositories(basePackages = "com.plash.configurator.repository.elasticsearch")
@EnableSwagger
@EnableScheduling
@EnableAsync
@EnableCaching
//@Import(RepositoryRestMvcConfiguration.class)
public class Application extends SpringBootServletInitializer  implements CommandLineRunner {

    /*
	 * for war
	 */

   /* @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }*/
    /*
     * for jar
     */

   /* @Autowired
    private ElasticsearchOperations es;*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        return executor;
    }
    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("************************DATASOURCE = " + dataSource);
//    }

   /* @Override
    public void run(String... args) throws Exception {

        printElasticSearchInfo();

    }*/

    //useful for debug, print elastic search details
   /* private void printElasticSearchInfo() {
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
    }*/

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("prospect-cache");
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
