package org.example.spring_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class})
public class SpringModuleApplication {

  public static void main(String[] args) {
    ApplicationContext context  = SpringApplication.run(SpringModuleApplication.class, args);

    App app = context.getBean(App.class);
    app.show();
  }

}
