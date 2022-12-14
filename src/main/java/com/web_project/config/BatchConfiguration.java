package com.web_project.config;

import com.web_project.listner.JobCompletionNotificationListener;
import com.web_project.model.entity.CreditCard;
import com.web_project.model.repository.CreditCardRepository;
import com.web_project.processor.CreditCardProcessor;
import lombok.RequiredArgsConstructor;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@EnableScheduling
@RequiredArgsConstructor
public class BatchConfiguration {
    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;
    private final CreditCardRepository cardRepository;
    private final JobCompletionNotificationListener listener;
    private final JobLauncher jobLauncher;

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
