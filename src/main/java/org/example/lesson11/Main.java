package org.example.lesson11;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();

        //CRUD operations
        Session session=null;

        try {
            //Create
//            Person person=new Person();
//            person.setFirstName("Tim");
//            person.setLastName("Finn");
//            person.setAge(25);
//
//            session=factory.getCurrentSession();
//            session.beginTransaction();
//            session.save(person);
//            session.getTransaction().commit();

            //Read
//            session=factory.getCurrentSession();
//            session.beginTransaction();
//            Person retrievedPerson=session.get(Person.class, 3L);
//            session.getTransaction().commit();
//            System.out.println("Retrieved Person: " + retrievedPerson);

            //Update
//            session=factory.getCurrentSession();
//            session.beginTransaction();
//            Person personToUpdate=session.get(Person.class, 3L);
//            System.out.println("Person before update: " + personToUpdate);
//            personToUpdate.setFirstName("Tom");
//            session.getTransaction().commit();
//            System.out.println("Updated Person: " + personToUpdate);

            //Delete
            session=factory.getCurrentSession();
            session.beginTransaction();
            Person personToDelete=session.get(Person.class, 3L);
            session.delete(personToDelete);
            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
