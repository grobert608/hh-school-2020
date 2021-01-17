package ru.hh.school.employers;

/**
 * DTO - Data transfer object
 * https://martinfowler.com/eaaCatalog/dataTransferObject.html
 *
 */
public class StatisticsDto {

  private final static String REPORT_TEMPLATE = "total vacancies=%d, min=%d, max=%d";

  private final Long vacancyCount;
  private final Integer minSalary;
  private final Integer maxSalary;

  public StatisticsDto(Long vacancyCount, Integer minSalary, Integer maxSalary) {
    this.vacancyCount = vacancyCount;
    this.minSalary = minSalary;
    this.maxSalary = maxSalary;
  }

  public String summaryReport() {
    return String.format(REPORT_TEMPLATE, vacancyCount, minSalary, maxSalary);
  }

  @Override
  public String toString() {
    return summaryReport();
  }

  public long getVacancyCount() {
    return vacancyCount;
  }

  public long getMinSalary() {
    return minSalary;
  }

  public long getMaxSalary() {
    return maxSalary;
  }
}
