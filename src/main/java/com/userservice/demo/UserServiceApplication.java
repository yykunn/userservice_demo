package com.userservice.demo;

import static springfox.documentation.builders.PathSelectors.regex;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import com.userservice.demo.configuration.DBConfiguration;
import com.userservice.demo.controller.JooqToSpringExceptionTransformer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({"com.userservice.demo"})
@EnableSwagger2
@Configuration
public class UserServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

  @Bean
  public Docket lawyersApi() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("All")
        .apiInfo(new ApiInfoBuilder().title("问题").description("Create by NiuFa").build()).select()
        .paths(regex("/*.*")).build();
  }

  @Autowired
  DBConfiguration dbConfiguration;

  // Database related configurations
  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:mysql://" + dbConfiguration.getUrl());
    config.setUsername(dbConfiguration.getUsername());
    config.setPassword(dbConfiguration.getPassword());
    config.addDataSourceProperty("characterEncoding", "utf8");
    config.addDataSourceProperty("connectionCollation", "utf8_unicode_ci");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    config.setMaximumPoolSize(10);

    return new HikariDataSource(config);
  }

  @Bean
  public LazyConnectionDataSourceProxy lazyConnectionDataSource() {
    return new LazyConnectionDataSourceProxy(dataSource());
  }

  @Bean
  public TransactionAwareDataSourceProxy transactionAwareDataSource() {
    return new TransactionAwareDataSourceProxy(lazyConnectionDataSource());
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(lazyConnectionDataSource());
  }

  @Bean
  public DataSourceConnectionProvider connectionProvider() {
    return new DataSourceConnectionProvider(transactionAwareDataSource());
  }

  @Bean
  public JooqToSpringExceptionTransformer jooqToSpringExceptionTransformer() {
    return new JooqToSpringExceptionTransformer();
  }

  @Bean
  public org.jooq.Configuration configuration() {
    DefaultConfiguration jooqConfiguration = new DefaultConfiguration();

    jooqConfiguration.set(connectionProvider());
    jooqConfiguration.set(new DefaultExecuteListenerProvider(jooqToSpringExceptionTransformer()));
    jooqConfiguration.set(SQLDialect.MYSQL);

    return jooqConfiguration;
  }

  @Bean
  public DSLContext dsl() {
    return new DefaultDSLContext(configuration());
  }
}
