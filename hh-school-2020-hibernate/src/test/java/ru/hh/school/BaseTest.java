package ru.hh.school;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.hh.school.util.QueryInfoHolder;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class BaseTest {

  protected static EmbeddedPostgres pg;
  protected static SessionFactory sessionFactory;

  @BeforeClass
  public static void setupSessionFactory() throws IOException {
    pg = EmbeddedPostgres.builder().setPort(5433).start();
    sessionFactory = DbFactory.createSessionFactory();
  }

  @Before
  public void setUp() {
    QueryInfoHolder.clear();
  }

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  protected <T> T doInTransaction(Supplier<T> supplier) {
    Optional<Transaction> transaction = beginTransaction();
    try {
      T result = supplier.get();
      transaction.ifPresent(Transaction::commit);
      return result;
    } catch (RuntimeException e) {
      transaction
        .filter(Transaction::isActive)
        .ifPresent(Transaction::rollback);
      throw e;
    }
  }

  protected void doInTransaction(Runnable runnable) {
    doInTransaction(() -> {
      runnable.run();
      return null;
    });
  }

  private Optional<Transaction> beginTransaction() {
    Transaction transaction = getSession().getTransaction();
    if (transaction.isActive()) {
      return Optional.empty();
    }
    transaction.begin();
    return Optional.of(transaction);
  }

  // методы ниже возвращают количество подготовленных JDBC стейтментов

  protected long getSelectCount() {
    return QueryInfoHolder.getQueryInfo().selectCount;
  }

  protected long getUpdateCount() {
    return QueryInfoHolder.getQueryInfo().updateCount;
  }

  protected long getInsertCount() {
    return QueryInfoHolder.getQueryInfo().insertCount;
  }

  protected long getDeleteCount() {
    return QueryInfoHolder.getQueryInfo().deleteCount;
  }

  protected long getCallCount() {
    return QueryInfoHolder.getQueryInfo().callCount;
  }

  protected long getTotalQueries() {
    return QueryInfoHolder.getQueryInfo().getTotalQueriesCount();
  }

  protected void clearQueriesCounts() {
    QueryInfoHolder.clear();
  }
}
