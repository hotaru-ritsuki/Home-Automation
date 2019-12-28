//package com.softserve.lv460.device.config;
//
//import com.softserve.lv460.device.document.DeviceData;
//import lombok.AllArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//@AllArgsConstructor
//public class BatchConfig {
//  private JobBuilderFactory jobBuilderFactory;
//  private StepBuilderFactory stepBuilderFactory;
//
//
//  @Bean
//  public Job job(
//          ItemReader<DeviceData> itemReader,
//          ItemProcessor<DeviceData, DeviceData> itemProcessor,
//          ItemWriter<DeviceData> itemWriter) {
//    Step step = stepBuilderFactory.get("ETL-Load-Step")
//            .<DeviceData, DeviceData>chunk(100)
//            .reader(itemReader)
//            .processor(itemProcessor)
//            .writer(itemWriter)
//            .build();
//
//    return jobBuilderFactory.get("ETL-Load")
//            .incrementer(new RunIdIncrementer())
//            .start(step)
//            .build();
//  }
//
//  @Bean
//  public ItemReader<DeviceData> itemReader(){
//
//  }
//}
