package org.example.spring_module.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.example.spring_module.repository.bike",
        entityManagerFactoryRef = "bikeEntityManagerFactory",
        transactionManagerRef   = "bikeTransactionManager"
)
public class BikeDataSourceConfig {

  @Bean
  @ConfigurationProperties("datasource.bike.hikari")
  public HikariDataSource bikeDataSource() {
    return new HikariDataSource();
  }

  @Bean
  @ConfigurationProperties("datasource.bike.jpa")
  public DatasourceJpaProperties bikeJpa() {
    return new DatasourceJpaProperties();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean bikeEntityManagerFactory(
          @Qualifier("bikeDataSource") DataSource dataSource,
          @Qualifier("bikeJpa") DatasourceJpaProperties bikeJpa,
          EntityManagerFactoryBuilder builder) {

    return builder
            .dataSource(dataSource)
            .packages("org.example.spring_module.entity.bike")
            .persistenceUnit("bike")
            .properties(Map.of(
                    "hibernate.hbm2ddl.auto", bikeJpa.getDdlAuto(),
                    "hibernate.dialect",      bikeJpa.getDialect(),
                    "hibernate.show_sql",     String.valueOf(bikeJpa.isShowSql())
            ))
            .build();
  }

  @Bean
  public PlatformTransactionManager bikeTransactionManager(
          @Qualifier("bikeEntityManagerFactory") EntityManagerFactory bikeEntityManagerFactory) {
    return new JpaTransactionManager(bikeEntityManagerFactory);
  }
}