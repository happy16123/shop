package com.myshop.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
class RootConfig {
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikari = new HikariConfig();
		hikari.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikari.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:orcl");
		hikari.setUsername("scott");
		hikari.setPassword("tiger");
		
		HikariDataSource datsSource = new HikariDataSource(hikari);
		
		return datsSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean session = new SqlSessionFactoryBean();
		session.setDataSource(dataSource());
		
		return (SqlSessionFactory)session.getObject();
	}
}
