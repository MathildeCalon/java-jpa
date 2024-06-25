package org.example;

import org.example.Entity.Alimentary;
import org.example.Entity.Animal;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.example.Entity.Alimentary.*;
import static org.example.Entity.GlobalVariables.em;
import static org.example.Entity.GlobalVariables.emf;

public class Main {
    public static void main(String[] args) {
        ihmMenu();
        em.close();
        emf.close();
    }

    public static void newAnimal(String name, int age, Enum<Alimentary> diet, LocalDate arrival){
        Animal animal = new Animal();
        animal.setName(name);
        animal.setAge(age);
        animal.setDiet(diet);
        animal.setArrivalDate(arrival);


        em.getTransaction().begin();
        em.persist(animal);
        em.getTransaction().commit();
    }

    private static void getOneAnimal(int id){
        try {
            Animal animal = em.find(Animal.class, id);
            System.out.println(animal);
        } catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private static void getAnimalByName(String name){
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
    }

    private static void getAnimalByDiet(Enum<Alimentary> diet){
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
    }

    private static void ihmMenu(){
        System.out.println("1. Ajouter un animal");
        System.out.println("2. Chercher un animal par son id");
        System.out.println("3. Chercher un/des animaux par leur nom");
        System.out.println("4. Chercher un/des animaux par leur régime");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch(choice) {
            case (1):
                System.out.println("Quel nom ?");
                String name = scanner.next();
                System.out.println("Age ?");
                int age = scanner.nextInt();
                System.out.println("Régime alimentaire ?");
                System.out.println("1. Carnivore");
                System.out.println("2. Herbivore");
                System.out.println("3. Omnivore");
                int dietInt = scanner.nextInt();
                Enum<Alimentary> diet = switch (dietInt) {
                    case (1) -> CARNIVORE;
                    case (2) -> HERBIVORE;
                    case (3) -> OMNIVORE;
                    default -> null;
                };
                System.out.println("Date d'arrivée ?");
                String arrival = scanner.next();
                LocalDate arrivalDate = LocalDate.parse(arrival);
                newAnimal(name, age, diet, arrivalDate); break;
            case (2) :
                System.out.println("Quel id ?");
                int animalId = scanner.nextInt();
                getOneAnimal(animalId); break;
            case (3) :
                System.out.println("Quel nom ?");
                String animalName = scanner.next();
                getAnimalByName(animalName); break;
            case (4) :
                System.out.println("Quel régime alimentaire ?");
                System.out.println("1. Carnivore");
                System.out.println("2. Herbivore");
                System.out.println("3. Omnivore");
                int dietType = scanner.nextInt();
                Enum<Alimentary> diet2 = switch (dietType) {
                    case (1) -> CARNIVORE;
                    case (2) -> HERBIVORE;
                    case (3) -> OMNIVORE;
                    default -> null;
                };
                getAnimalByDiet(diet2); break;
        }
    }
}