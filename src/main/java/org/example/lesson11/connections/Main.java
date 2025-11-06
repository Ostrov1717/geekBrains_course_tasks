package org.example.lesson11.connections;

import org.example.lesson11.connections.many_to_many.Course;
import org.example.lesson11.connections.many_to_many.Student;
import org.example.lesson11.connections.many_to_many_adv.Buyer;
import org.example.lesson11.connections.many_to_many_adv.Product;
import org.example.lesson11.connections.many_to_many_adv.Purchase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hi_connections.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            //Create
//            session= factory.getCurrentSession();
//            session.beginTransaction();
//            Product product1=new Product("Fiesta");
//            session.save(product1);
//            session.getTransaction().commit();
            //Update
            session= factory.getCurrentSession();
            session.beginTransaction();
            Product productToUpdate=session.get(Product.class, 6L);
            productToUpdate.setName("Fiesta Zero");
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }

    }
}
