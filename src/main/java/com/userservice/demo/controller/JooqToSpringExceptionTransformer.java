package com.userservice.demo.controller;

import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

public class JooqToSpringExceptionTransformer extends DefaultExecuteListener {

  private static final long serialVersionUID = -7089191489442623230L;

  @Override
  public void exception(ExecuteContext ctx) {
    SQLDialect dialect = ctx.configuration().dialect();
    SQLExceptionTranslator translator =
        (dialect != null) ? new SQLErrorCodeSQLExceptionTranslator(dialect.name())
            : new SQLStateSQLExceptionTranslator();

    ctx.exception(translator.translate("jOOQ", ctx.sql(), ctx.sqlException()));
  }
}
