package com.adrmanagement.member;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;



@Configuration
public class PersistenceConfig {
	
//	@Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//          .setType(EmbeddedDatabaseType.H2)
//          .addScript("data.sql")
//          .build();
//    }
	

}
