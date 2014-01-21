package com.jaredstemen.blogspot;

import com.jaredstemen.blogspot.jsonimport.JsonFileImporter;
import com.jaredstemen.blogspot.repository.CategoryDataRepository;
import com.jaredstemen.blogspot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import rest.Rest;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Spring config for project
 * User: Jared Stemen
 * Date: 1/18/14
 * Time: 4:19 PM
 */

@Configuration
@EnableTransactionManagement //allows interaction with db
@EnableJpaRepositories("com.jaredstemen.blogspot.repository")  //enables Spring data
public class AppConfig {

    //Injects location of db
    @Value("${database.location}")
    private String dataBaseLocation;

    @Value("${database.driver.name}")
    private String driverClassName;

    @Value("${database.strategy}")
    private String databaseStrategy;

    @Bean
    public Rest rest(CategoryDataRepository categoryDataRepository, ProductRepository productRepository) {
        return new Rest(categoryDataRepository, productRepository);
    }

    @Bean
    public JsonFileImporter jsonFileImporter() {
        return new JsonFileImporter();
    }

    //Boilerplate database code:

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

    Properties additionalProperties() {
        return new Properties() {
            {  // Hibernate Specific:
                setProperty("hibernate.hbm2ddl.auto", databaseStrategy);//automatically update db schema as needed
            }
        };
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
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dataBaseLocation);
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer pspc =
                new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]
                {
                        new ClassPathResource("propertyFile.properties")
                        , new ClassPathResource("test.propertyFile.properties") //file to house test properties
                };
        pspc.setLocations(resources);
        pspc.setIgnoreResourceNotFound(true);  //Don't complain about not finding the test properties file
        return pspc;
    }
}
