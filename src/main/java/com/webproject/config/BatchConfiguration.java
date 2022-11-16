package com.webproject.config;

import com.webproject.listner.JobCompletionNotificationListener;
import com.webproject.model.entity.CreditCard;
import com.webproject.model.repository.CreditCardRepository;
import com.webproject.processor.CreditCardProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CreditCardRepository cardRepository;
    @Autowired
    private JobCompletionNotificationListener listener;
    @Autowired
    private JobLauncher jobLauncher;

    @Scheduled(cron = "@monthly")
    public void launchJob() throws Exception {
        Date date = new Date();
        JobExecution jobExecution = jobLauncher.run(userCardUpdateExpiredStatusJob(), new JobParametersBuilder()
                .addDate("launchDate", date)
                .toJobParameters());
    }

    @Bean
    public RepositoryItemReader<CreditCard> reader() {
        RepositoryItemReader<CreditCard> reader = new RepositoryItemReader<>();
        reader.setRepository(cardRepository);
        reader.setMethodName("findAll");
        reader.setPageSize(100);
        Map<String, Direction> sorts = Map.of("id", Direction.ASC);
        reader.setSort(sorts);
        return reader;
    }

    @Bean
    public RepositoryItemWriter<CreditCard> writer() {
        RepositoryItemWriter<CreditCard> writer = new RepositoryItemWriter<>();
        writer.setRepository(cardRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public CreditCardProcessor processor() {
        return new CreditCardProcessor();
    }

    @Bean
    public Step step() {
        return this.stepBuilderFactory.get("step")
                .<CreditCard, CreditCard>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job userCardUpdateExpiredStatusJob() {
        return jobBuilderFactory.get("userCardUpdateExpiredStatus")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step())
                .build();
    }
}
