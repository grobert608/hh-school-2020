package ru.hh.school.service;

import ru.hh.school.dao.VacancyDao;
import ru.hh.school.employers.StatisticsDto;
import ru.hh.school.entity.Area;
import ru.hh.school.util.TransactionHelper;

public class VacancyService {
  private final VacancyDao vacancyDao;
  private final TransactionHelper transactionHelper;

  public VacancyService(VacancyDao vacancyDao, TransactionHelper transactionHelper) {
    this.vacancyDao = vacancyDao;
    this.transactionHelper = transactionHelper;
  }

  public StatisticsDto getSalaryStatistics(Area area) {
    return transactionHelper.inTransaction(() -> vacancyDao.getSalaryStatistics(area));
  }

}
