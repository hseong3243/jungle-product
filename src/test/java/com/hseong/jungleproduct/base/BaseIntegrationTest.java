package com.hseong.jungleproduct.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class BaseIntegrationTest {

    @Autowired
    private EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        DatabaseCleaner databaseCleaner = new DatabaseCleaner(em);
        databaseCleaner.clear();
        em.getTransaction().commit();
    }
}
