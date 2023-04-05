package com.example.week8.batch.job;

import com.example.week8.batch.process.CsvProcesser;
import com.example.week8.batch.writer.CustomItemWriter;
import com.example.week8.entity.csv.DadReportCsv;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class CsvChunkJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource; // DataSource DI
    private final CsvProcesser csvProcesser;
    private final CustomItemWriter customItemWriter;

    @Bean
    public Job CSVJob(){
        System.out.println("여기는 잡 잡ㅈ 잡");
        return jobBuilderFactory.get("csvJob")
                .start(step1())
//                .listener(csvJobListenerJob)
                .build();
    }

//    @JobScope
    @Bean
    public Step step1() {
        System.out.println("----------------- 스텝1 ------------------------");
        return stepBuilderFactory.get("step1")
                .<DadReportCsv, DadReportCsv>chunk(1)
                .reader(itemReader())
                .processor(csvProcesser)
                .writer(customItemWriter)
//                .listener(csvWriteListener)A
                .build();
    }

    @Bean
//    @StepScope
    public FlatFileItemReader itemReader() {
        System.out.println("여기는 잡 리더");
        return new FlatFileItemReaderBuilder<DadReportCsv>()
                .name("DadReportCsv")
                .resource(new ClassPathResource("/file/sample.csv"))
//                .strict(false)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(DadReportCsv.class)
                .linesToSkip(1)
                .delimited()
                .names("requestDate", "dadDetId", "showCount", "clickCount", "avgShowRank", "avgCpc", "adCost")
                .build();
    }
//    @Bean
//    public DadReportCsv process(DadReportCsv item){
//        System.out.println("Sdsdsdsdsddd");
//        return item;
//    }
//    @Bean
//    public ItemWriter<? super DadReportCsv> itemWriter(){
//        return new JdbcBatchItemWriterBuilder<DadReportCsv>()
//                .dataSource(dataSource)
//                .sql("insert into csv_entity(code, code_name, cost_resource, ad_variable, campaign, down_campaign, device, channel, portal, name, brand, brand_number, team, keyword, date, show_count) values (:code, :codeName, :costResource, :adVariable, :campaign, :downCampaign, :device, :channel, :portal, :name, :brand, :brandNumber, :team, :keyword, :date, :showCount) ") //insert into CSV_TEST values(1);
//                .beanMapped()
//                .build();
//    }

}
