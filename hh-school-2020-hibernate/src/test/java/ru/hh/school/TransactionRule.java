package ru.hh.school;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.rules.ExternalResource;

/**
 * Оборачивает каждый @Test в транзакцию.
 * В конце теста роллбечит транзакцию, чтобы не сохранять изменения.
 */
public class TransactionRule extends ExternalResource {

  private SessionFactory sessionFactory;

  public TransactionRule(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  protected void before() {
    sessionFactory.getCurrentSession().beginTransaction();
  }

  @Override
  protected void after() {
    Transaction transaction = sessionFactory.getCurrentSession().getTransaction();
    if (transaction.isActive()) {
      transaction.rollback();
    }
  }
}
