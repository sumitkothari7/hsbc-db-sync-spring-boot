package com.hsbc.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by ASUS on 08-03-2018.
 */
@Configuration
// Load to Environment
// (@see resources/datasource-cfg.properties).)
@PropertySources({ @PropertySource("classpath:application.properties") })
public class DBConfig {


    @Autowired
    private Environment env; // Contains Properties Load by @PropertySources

    @Bean
    public DataSource ds1Datasource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean ds1EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds1Datasource());

        // Scan Entities in Package:
        em.setPackagesToScan(new String[] { "com.hsbc.springboot.mysql" });
        em.setPersistenceUnitName(Constants.DB_MYSQL); // Important !!

        //
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();

        // JPA & Hibernate
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        properties.put("hibernate.naming-strategy", env.getProperty("spring.jpa.hibernate.naming-strategy"));

        // Solved Error: PostGres createClob() is not yet implemented.
        // PostGres Only:
        // properties.put("hibernate.temp.use_jdbc_metadata_defaults",  false);

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean
    public PlatformTransactionManager ds1TransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ds1EntityManager().getObject());
        return transactionManager;
    }


    @Bean
    public DataSource ds2Datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();


        dataSource.setUrl(env.getProperty("app.datasource.first.url"));

       /* dataSource.setUsername(env.getProperty("spring.datasource.username.2"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.2"));*/

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean ds2EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds2Datasource());
        // Scan Entities in Package:
        em.setPackagesToScan(new String[] { "com.hsbc.springboot.derby" });

        em.setPersistenceUnitName(Constants.DB_DERBY);
        HashMap<String, Object> properties = new HashMap<>();
        // JPA & Hibernate
        properties.put("spring.jpa.generate-ddl", "true");
        properties.put("spring.jpa.hibernate.ddl-auto", "create-drop");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean
    public PlatformTransactionManager ds2TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ds2EntityManager().getObject());
        return transactionManager;
    }

}
