package org.example;

import org.example.Entity.Alimentary;
import org.example.Entity.Animal;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static org.example.Entity.Alimentary.CARNIVORE;

public class Main {
    public static void main(String[] args) {
//        newAnimal("Simba", 4, CARNIVORE, LocalDate.now());
//        newAnimal("Baloo", 10, Alimentary.CARNIVORE, LocalDate.now());
//        newAnimal("Bambi", 1, Alimentary.HERBIVORE, LocalDate.now());
//        newAnimal("Bambi", 5, Alimentary.HERBIVORE, LocalDate.now());


        getOneAnimal(4);
        getAnimalByName("Bambi");
        getAnimalByDiet(CARNIVORE);
    }

    public static void newAnimal(String name, int age, Enum<Alimentary> diet, LocalDate arrival){
        Animal animal = new Animal();
        animal.setName(name);
        animal.setAge(age);
        animal.setDiet(diet);
        animal.setArrivalDate(arrival);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_exercice01");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(animal);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    private static void getOneAnimal(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_exercice01");
        EntityManager em = emf.createEntityManager();

        try {
            Animal animal = em.find(Animal.class, id);
            System.out.println(animal);
        } catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
        }

        em.close();
        emf.close();
    }

    private static void getAnimalByName(String name){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_exercice01");
        EntityManager em = emf.createEntityManager();

        try {
            Query query = em.createQuery("select a from Animal a where a.name= :name");
            query.setParameter("name", name);
            List<Animal> resultList = query.getResultList();
            for (Object animal : resultList) {
                System.out.println(animal);
            }
        } catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
        }
        em.close();
        emf.close();
    }

    private static void getAnimalByDiet(Enum<Alimentary> diet){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_exercice01");
        EntityManager em = emf.createEntityManager();

        try {
            Query query = em.createQuery("select a from Animal a where a.diet= :diet");
            query.setParameter("diet", diet);
            List<Animal> resultList = query.getResultList();
            for (Object animal : resultList) {
                System.out.println(animal);
            }
        } catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
        }
        em.close();
        emf.close();
    }

    private static void ihmMenu(){
        System.out.println("1. Ajouter un animal");
        System.out.println("2. Chercher un animal par son id");
        System.out.println("2. Chercher un/des animaux par leur nom");
        System.out.println("2. Chercher un/des animaux par leur régime");
    }
}