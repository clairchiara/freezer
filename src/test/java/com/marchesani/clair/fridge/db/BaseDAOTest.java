package com.marchesani.clair.fridge.db;

import static org.mockito.Mockito.*;

import org.assertj.core.api.JUnitSoftAssertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

@RunWith(MockitoJUnitRunner.class)
public class BaseDAOTest {

    private BaseDAO baseDAO;

    @Mock
    private SessionFactory mockSessionFactory;

    @Mock
    private Session mockSession;

    private AtomicBoolean inTransaction = new AtomicBoolean(false);

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Before
    public void before() throws Exception {
        baseDAO = new BaseDAO(mockSessionFactory) {};
        when(mockSessionFactory.openSession()).thenReturn(mockSession);
        when(mockSession.beginTransaction()).thenAnswer(args -> {
            inTransaction.set(true);
            return mock(Transaction.class);
        });
        when(mockSession.close()).thenAnswer(args -> {
            inTransaction.set(false);
            return mock(Connection.class);
        });
    }

    @Test
    public void testgetTransactionally() throws Exception {
        final String testObject = "testObject";
        Supplier<Object> testInTransaction = () -> {
            softly.assertThat(inTransaction.get())
                    .as("Code should be executed transactionally")
                    .isTrue();
            return testObject;
        };
        Object result = baseDAO.getTransactionally(testInTransaction);
        softly.assertThat(result).as("The supplier should've been called by BaseDAO").isSameAs(testObject);
        softly.assertThat(inTransaction.get())
                .as("Transaction should be closed after code has been executed")
                .isFalse();
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        AtomicBoolean savedOrUpdated = new AtomicBoolean(false);
        doAnswer(args -> {
            softly.assertThat(inTransaction.get())
                    .as("SaveOrUpdate should be executed transactionally")
                    .isTrue();
            savedOrUpdated.set(true);
            return null;
        }).when(mockSession).saveOrUpdate(any(DBEntity.class));
        baseDAO.saveOrUpdate(mock(DBEntity.class));
        softly.assertThat(savedOrUpdated.get()).as("The object should've been saved or updated").isTrue();
        softly.assertThat(inTransaction.get())
                .as("Transaction should be closed after saveOrUpdate operation has concluded")
                .isFalse();
    }

}
