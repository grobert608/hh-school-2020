package ru.hh.school.util;

public class QueryInfo {

  public int selectCount;
  public int insertCount;
  public int updateCount;
  public int deleteCount;
  public int callCount;

  public void clear() {
    selectCount = 0;
    insertCount = 0;
    updateCount = 0;
    deleteCount = 0;
    callCount = 0;
  }

  public int getTotalQueriesCount() {
    return selectCount + insertCount + updateCount + deleteCount + callCount;
  }

}
