package org.superbiz.moviefun;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class AlbumsDatabaseConfig {




    @Bean
    public DataSource albumsDataSource(DatabaseServiceCredentials databaseServiceCredentials) {
        MysqlDataSource albumDataSouce = new MysqlDataSource();
        albumDataSouce.setURL(databaseServiceCredentials.jdbcUrl("albums-mysql"));
        HikariConfig conf = new HikariConfig();
        conf.setDataSource(albumDataSouce);
        HikariDataSource  hikariDataSource = new HikariDataSource(conf);
        return hikariDataSource;
    }





    @Bean
    public LocalContainerEntityManagerFactoryBean albumEnityManagerFactory(HibernateJpaVendorAdapter hibernateJpaVendorAdapter,DataSource albumsDataSource){
        LocalContainerEntityManagerFactoryBean albumEnityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        albumEnityManagerFactory.setDataSource(albumsDataSource);
        albumEnityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        albumEnityManagerFactory.setPackagesToScan(DatabaseConfig.class.getPackage().getName());
        albumEnityManagerFactory.setPersistenceUnitName("uniqueAlbum");
        return albumEnityManagerFactory;
    }



    //@Bean( name="albumTransactionManager")
    @Bean
    //@Qualifier("albumTransactionManager")
    public PlatformTransactionManager albumTransactionManager(EntityManagerFactory albumEnityManagerFactory){
        PlatformTransactionManager albumTransactionManager = new JpaTransactionManager(albumEnityManagerFactory);
        return albumTransactionManager;
    }
}
