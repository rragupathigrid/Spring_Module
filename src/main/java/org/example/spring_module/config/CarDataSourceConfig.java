package org.example.spring_module.config;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.example.spring_module.repository.car",
        entityManagerFactoryRef = "carEntityManagerFactory",
        transactionManagerRef   = "carTransactionManager"
)
public class CarDataSourceConfig {

  @Bean
  @Primary
  @ConfigurationProperties("datasource.car")
  public DataSourceProperties carDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  public DataSource carDataSource(
          @Qualifier("carDataSourceProperties") DataSourceProperties props) {
    return props.initializeDataSourceBuilder().build();
  }

  @Bean
  @Primary
  @ConfigurationProperties("datasource.car.jpa")
  public DatasourceJpaProperties carJpaProperties() {
    return new DatasourceJpaProperties();
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean carEntityManagerFactory(
          @Qualifier("carDataSource") DataSource dataSource,
          @Qualifier("carJpaProperties") DatasourceJpaProperties carJpaProperties,
          EntityManagerFactoryBuilder builder) {

    return builder
            .dataSource(dataSource)
            .packages("org.example.spring_module.entity.car")
            .persistenceUnit("car")
            .properties(Map.of(
                    "hibernate.hbm2ddl.auto", carJpaProperties.getDdlAuto(),
                    "hibernate.dialect",      carJpaProperties.getDialect(),
                    "hibernate.show_sql",     String.valueOf(carJpaProperties.isShowSql())
            ))
            .build();
  }

  @Bean
  @Primary
  public PlatformTransactionManager carTransactionManager(
          @Qualifier("carEntityManagerFactory") EntityManagerFactory carEntityManagerFactory) {
    return new JpaTransactionManager(carEntityManagerFactory);
  }
}