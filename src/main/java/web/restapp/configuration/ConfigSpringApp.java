package web.restapp.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
@ComponentScan({"web.restapp"})
public class ConfigSpringApp {

	@Bean
	public DataSource configDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/restdb");
		dataSource.setUsername("root");
		dataSource.setPassword("wakacje1234");
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
