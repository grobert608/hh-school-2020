package ru.hh.school.util;

import org.hibernate.resource.jdbc.spi.StatementInspector;

public class StatementCounter implements StatementInspector {

  @Override
  public String inspect(String sql) {
    QueryInfo queryInfo = QueryInfoHolder.getQueryInfo();
    QueryType.of(sql).increment(queryInfo);
    return sql;
  }

}
