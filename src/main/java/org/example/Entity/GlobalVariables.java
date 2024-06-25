package org.example.Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GlobalVariables {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_exercice01");
    public static EntityManager em = emf.createEntityManager();
}
