package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DatabaseConfig {

    @Bean
    public DatabaseServiceCredentials getDatabaseServiceCredentials(@Value("${VCAP.SERVICES}") String VCAP_SERVICES){
        String vcap=System.getenv("VCAP_SERVICES");
        DatabaseServiceCredentials databaseServiceCredentials = new DatabaseServiceCredentials(VCAP_SERVICES);
        System.out.println(VCAP_SERVICES);
        return databaseServiceCredentials ;
    }



    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter =  new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }
}
