package ru.hh.school.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public enum QueryType {

  SELECT(
    List.of('w', 's'),
    (queryInfo) -> queryInfo.selectCount++
  ),
  INSERT(
    List.of('i'),
    (queryInfo) -> queryInfo.insertCount++
  ),
  UPDATE(
    List.of('u'),
    (queryInfo) -> queryInfo.updateCount++
  ),
  DELETE(
    List.of('d'),
    (queryInfo) -> queryInfo.deleteCount++
    ),
  CALL(
    List.of('c', '?'),
    (queryInfo) -> queryInfo.callCount++
  );

  private List<Character> startingChars;
  private Consumer<QueryInfo> incrementer;

  QueryType(List<Character> startingChars, Consumer<QueryInfo> incrementer) {
    this.startingChars = startingChars;
    this.incrementer = incrementer;
  }

  public void increment(QueryInfo info) {
    incrementer.accept(info);
  }

  public static QueryType of(String query) {
    Character queryFirstChar = removeRedundantSymbols(query).charAt(0);
    return Arrays.stream(QueryType.values())
      .filter(queryType -> queryType.startingChars.contains(queryFirstChar))
      .findAny()
      .orElseThrow(() -> new RuntimeException("Unknown query type"));
  }

  private static String removeRedundantSymbols(String query) {
    return query.replaceAll("--.*\n", "")
      .replaceAll("\n", "")
      .replaceAll("/\\*.*\\*/", "")
      .trim();
  }

}
