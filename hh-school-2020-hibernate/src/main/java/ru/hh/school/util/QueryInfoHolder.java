package ru.hh.school.util;

public class QueryInfoHolder {

  private static final ThreadLocal<QueryInfo> QUERY_INFO_HOLDER = ThreadLocal.withInitial(QueryInfo::new);

  public static QueryInfo getQueryInfo() {
    return QUERY_INFO_HOLDER.get();
  }

  public static void clear() {
    QUERY_INFO_HOLDER.get().clear();
  }

}
