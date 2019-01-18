package org.superbiz.moviefun;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class MoviesDatabaseConfig {

    @Bean
    public DataSource moviesDataSource(DatabaseServiceCredentials databaseServiceCredentials) {
        MysqlDataSource moviesDataSource = new MysqlDataSource();
        moviesDataSource.setURL(databaseServiceCredentials.jdbcUrl("movies-mysql"));
        HikariConfig conf = new HikariConfig();
        conf.setDataSource(moviesDataSource);
        HikariDataSource hikariDataSource = new HikariDataSource(conf);
        return moviesDataSource;
    }





    @Bean
    public LocalContainerEntityManagerFactoryBean movieEntityManagerFactory(HibernateJpaVendorAdapter hibernateJpaVendorAdapter, DataSource moviesDataSource){
        LocalContainerEntityManagerFactoryBean movieEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        movieEntityManagerFactory.setDataSource(moviesDataSource);
        movieEntityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        movieEntityManagerFactory.setPackagesToScan(DatabaseConfig.class.getPackage().getName());
        movieEntityManagerFactory.setPersistenceUnitName("uniqueMovie");
        return movieEntityManagerFactory;
    }



  //  @Bean(name="movieTransactionManager")
    @Bean
    public PlatformTransactionManager movieTransactionManager(EntityManagerFactory movieEntityManagerFactory){
        PlatformTransactionManager movieTransactionManager = new JpaTransactionManager(movieEntityManagerFactory);
        // ((JpaTransactionManager) movieTransactionManager).setEntityManagerFactory((EntityManagerFactory) movieEntityManagerFactory);
        return movieTransactionManager;
    }


}
