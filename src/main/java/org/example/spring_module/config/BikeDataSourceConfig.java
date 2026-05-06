package org.example.spring_module.config;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
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
  @ConfigurationProperties("datasource.bike")
  public DataSourceProperties bikeDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public DataSource bikeDataSource(
          @Qualifier("bikeDataSourceProperties") DataSourceProperties props) {
    return props.initializeDataSourceBuilder().build();
  }

  @Bean
  @ConfigurationProperties("datasource.bike.jpa")
  public DatasourceJpaProperties bikeJpaProperties() {
    return new DatasourceJpaProperties();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean bikeEntityManagerFactory(
          @Qualifier("bikeDataSource") DataSource dataSource,
          @Qualifier("bikeJpaProperties") DatasourceJpaProperties bikeJpaProperties,
          EntityManagerFactoryBuilder builder) {

    return builder
            .dataSource(dataSource)
            .packages("org.example.spring_module.entity.bike")
            .persistenceUnit("bike")
            .properties(Map.of(
                    "hibernate.hbm2ddl.auto", bikeJpaProperties.getDdlAuto(),
                    "hibernate.dialect",      bikeJpaProperties.getDialect(),
                    "hibernate.show_sql",     String.valueOf(bikeJpaProperties.isShowSql())
            ))
            .build();
  }

  @Bean
  public PlatformTransactionManager bikeTransactionManager(
          @Qualifier("bikeEntityManagerFactory") EntityManagerFactory bikeEntityManagerFactory) {
    return new JpaTransactionManager(bikeEntityManagerFactory);
  }
}