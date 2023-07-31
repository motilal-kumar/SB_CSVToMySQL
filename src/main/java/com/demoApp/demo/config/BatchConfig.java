/*
package com.demoApp.demo.config;


import com.demoApp.demo.entity.Content;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public FlatFileItemReader<Content>   reader(){

        FlatFileItemReader<Content> reader =  new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("content.csv"));
        reader.setLineMapper(getLineMapper());
        reader.setLinesToSkip(1);

        return reader;

    }

    public LineMapper<Content> getLineMapper() {

            DefaultLineMapper<Content> lineMapper  = new  DefaultLineMapper<>();

            DelimitedLineTokenizer lineTokenizer =     new DelimitedLineTokenizer();

            //lineTokenizer.setNames(new String[]{ "Id", "Title","Author", "Description","Email", "Type", "State", "Published" });
            lineTokenizer.setNames(new String[]{ "Id", "Type", "Description"});
            //  lineTokenizer.setIncludedFields(new int[] {0,1,2,4});

            BeanWrapperFieldSetMapper<Content> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
            fieldSetMapper.setTargetType(Content.class);

            lineMapper.setLineTokenizer(lineTokenizer);
            lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }


    @Bean
    public  ContentItemProcessor processor(){

        return new ContentItemProcessor();

    }

    @Bean
    public JdbcBatchItemWriter<Content> writer(){

        JdbcBatchItemWriter<Content> writer =  new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Content>());
       // writer.setSql("insert into content(id, title,author, description,email, type, state, published) values (:id, :title, :author, :description, :email, :type, :state, :published)");
        writer.setSql("insert into content(id, type, description) values (:id, :type, :description)");
        writer.setDataSource(this.dataSource);


        return writer;

    }

    @Bean
    public Job importContentJob(){

        return this.jobBuilderFactory.get("CONTENT-IMPORT-JOB")
                .incrementer(new RunIdIncrementer())
                .flow(stet1())
                .end()
                .build();
    }

    @Bean
    public Step stet1() {

      return   this.stepBuilderFactory.get("step1")
                .<Content, Content>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
*/
