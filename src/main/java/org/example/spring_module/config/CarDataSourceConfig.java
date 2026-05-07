package org.example.spring_module.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
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

  /**
   * Manually provide EntityManagerFactoryBuilder since we excluded
   * HibernateJpaAutoConfiguration. Shared by both datasource configs.
   *
   * Spring Boot 4: second parameter is Function<DataSource, Map<String, ?>>
   * Pass null — we supply all JPA properties explicitly via .properties()
   */
  @Bean
  @Primary
  public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    return new EntityManagerFactoryBuilder(adapter, ds -> Map.of(), null);
  }

  @Bean
  @Primary
  @ConfigurationProperties("datasource.car.hikari")
  public HikariDataSource carDataSource() {
    return new HikariDataSource();
  }

  @Bean
  @Primary
  @ConfigurationProperties("datasource.car.jpa")
  public DatasourceJpaProperties carJpa() {
    return new DatasourceJpaProperties();
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean carEntityManagerFactory(
          @Qualifier("carDataSource") DataSource dataSource,
          @Qualifier("carJpa") DatasourceJpaProperties carJpa,
          EntityManagerFactoryBuilder builder) {

    return builder
            .dataSource(dataSource)
            .packages("org.example.spring_module.entity.car")
            .persistenceUnit("car")
            .properties(Map.of(
                    "hibernate.hbm2ddl.auto", carJpa.getDdlAuto(),
                    "hibernate.dialect",      carJpa.getDialect(),
                    "hibernate.show_sql",     String.valueOf(carJpa.isShowSql())
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