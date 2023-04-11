package com.example.week8;

//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.Charset;
import java.util.logging.Filter;

@EnableJpaAuditing
@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
public class Week8Application {

    public static void main(String[] args) {
        SpringApplication.run(Week8Application.class, args);
    }
//    @Bean
//    Hibernate5Module hibernate5Module(){
//        return new Hibernate5Module();
//    }
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return (Filter) characterEncodingFilter;
    }

}
