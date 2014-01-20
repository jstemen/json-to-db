package com.jaredstemen.blogspot;

import com.jaredstemen.blogspot.jsonimport.JsonFileImporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: jared
 * Date: 1/18/14
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@EnableTransactionManagement
//@PropertySource("classpath:propertyFile.properties")
@EnableJpaRepositories("com.jaredstemen.blogspot.repository")
public class AppConfig {


    @Value("${databaselocation}")
    private String dataBaseLocation;

    Properties additionalProperties() {
        return new Properties() {
            {  // Hibernate Specific:
                setProperty("hibernate.hbm2ddl.auto", "update");;
            }
        };
    }
    @Bean
    public JsonFileImporter jsonFileImporter(){
        return new JsonFileImporter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.jaredstemen.blogspot"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        //dataSource.setUrl("jdbc:h2:file:~/jsonDataDb");
        dataSource.setUrl(dataBaseLocation);
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer pspc =
                new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[ ]
                {
                        new ClassPathResource( "propertyFile.properties" )
                  ,new ClassPathResource( "test.propertyFile.properties" )
                };
        pspc.setLocations( resources );
        pspc.setIgnoreUnresolvablePlaceholders( true );
        pspc.setIgnoreResourceNotFound(true);
        return pspc;
    }
}
