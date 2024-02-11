package com.adrmanagement.adr;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;


@Configuration
public class PersistenceConfig {
	
//	@Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//          .setType(EmbeddedDatabaseType.H2)
//          .addScript("schema.sql")
//          .build();
//    }
	
	@Bean
	public XStream xStream() {
	    XStream xStream = new XStream();
	    xStream.addPermission(AnyTypePermission.ANY);
	    xStream.allowTypesByWildcard(new String[] { "com.adrmanagement.common.adr.domain.**",  "java.util.**", "java.base.**" });
	    return xStream;
	}
	

}
