package org.superbiz.moviefun;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class Application {

    @Value("${VCAP.SERVICES}")
    private String VCAP_SERVICES;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Autowired
    public ServletRegistrationBean actionServletRegistration(ActionServlet actionServlet) {
        return new ServletRegistrationBean(actionServlet, "/moviefun/*");
    }



    /*@Bean(name ="movieEntityManagerFactory")
    @Qualifier("movieEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean movieEntityManagerFactory(HibernateJpaVendorAdapter hibernateJpaVendorAdapter, DataSource moviesDataSource){
        LocalContainerEntityManagerFactoryBean movieEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        movieEntityManagerFactory.setDataSource(moviesDataSource);
        movieEntityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        movieEntityManagerFactory.setPackagesToScan("org.superbiz.movieFun.movies");
        movieEntityManagerFactory.setPersistenceUnitName("uniqueMovie");
        return movieEntityManagerFactory;
    }

    @Bean(name ="albumEnityManagerFactory")
    @Qualifier("albumEnityManagerFactory")
    public LocalContainerEntityManagerFactoryBean albumEnityManagerFactory(HibernateJpaVendorAdapter hibernateJpaVendorAdapter,DataSource albumsDataSource){
        LocalContainerEntityManagerFactoryBean albumEnityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        albumEnityManagerFactory.setDataSource(albumsDataSource);
        albumEnityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        albumEnityManagerFactory.setPackagesToScan("org.superbiz.movieFun.albums");
        albumEnityManagerFactory.setPersistenceUnitName("uniqueAlbum");
        return albumEnityManagerFactory;
    }

    @Bean(name="movieTransactionManager")
    @Qualifier("movieTransactionManager")
    public PlatformTransactionManager movieTransactionManager(EntityManagerFactory movieEntityManagerFactory){
        PlatformTransactionManager movieTransactionManager = new JpaTransactionManager(movieEntityManagerFactory);
       // ((JpaTransactionManager) movieTransactionManager).setEntityManagerFactory((EntityManagerFactory) movieEntityManagerFactory);
        return movieTransactionManager;
    }

    @Bean(name="albumTransactionManager")
    @Qualifier("albumTransactionManager")
    public PlatformTransactionManager albumTransactionManager(EntityManagerFactory albumEnityManagerFactory){
        PlatformTransactionManager albumTransactionManager = new JpaTransactionManager(albumEnityManagerFactory);
        return albumTransactionManager;
    }*/
}
