package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil
{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("uam");

    private static EntityManager getEntityManager() { return emf.createEntityManager(); }

    private static void close() { emf.close(); }
}
