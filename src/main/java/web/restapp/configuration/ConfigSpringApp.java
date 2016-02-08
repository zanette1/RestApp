package web.restapp.configuration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import web.restapp.model.Products;
import web.restapp.service.ProductsServiceImpl;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("web.restapp")
@PropertySource(value = "file:C://Users/db/jdbc.properties")
public class ConfigSpringApp {
	
	@Autowired
	Environment env;
	
	@Bean
	public DataSource configDataSource() {
		
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		return dataSource;
	}
	
	@Bean
	public AnnotationSessionFactoryBean configSessionFactory() {
		AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
		sessionFactory.setDataSource(configDataSource());
		sessionFactory.setPackagesToScan("web.restapp.model");
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setAnnotatedClasses(new Class[]{Products.class});
		return sessionFactory;
	}
	
	@Bean
	public ProductsServiceImpl configProductsService() {
		return new ProductsServiceImpl();
	}
	
	@Bean
	public HibernateTransactionManager configTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(configSessionFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public ObjectMapper configObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
	
	
	Properties getHibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
				setProperty("hibernate.show_sql", "true");
				setProperty("hibernate.hbm2ddl.auto", "update");
			}
		};
	}
	
}
