package com.cpuploadprocess.config;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class CPUploadConfig {

	@Autowired
	Environment environment;
	

	@Bean(name = "dbusername")
	public String dbusername() {
		return environment.getProperty("cpupload.jdbc.username");
	}
	
	@Bean(name = "dbpassword")
	public String dbpassword() {
		return environment.getProperty("cpupload.jdbc.password");
	}
	
	@Bean(name = "dbURL")
	public String dbURL() {
		return environment.getProperty("cpupload.jdbc.url");
	}
	
	
	@Bean(name = "dbdriver")
	public String dbdriver() {
		return environment.getProperty("cpupload.jdbc.driver");
	}

	@Bean(name="dataSource")
	public javax.sql.DataSource getDataSource(@Qualifier("dbusername") String username,
			@Qualifier("dbpassword") String password,
			@Qualifier("dbURL") String url,
			@Qualifier("dbdriver") String driver)
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);

		dataSource.setPassword(password);
		dataSource.setDriverClassName(driver);
		return  dataSource;
	}
	

	@Bean(name="jdbcTemplate")
	public JdbcTemplate getJDBCTemplate(@Qualifier("dataSource") javax.sql.DataSource ds)
	{
		return new JdbcTemplate(ds);
	}
	
	
}


