package org.example.lesson11;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collections;
import java.util.List;

public class MainStrore {
    private static Buyer buyer1;
    private static Buyer buyer2;
    private static Product product1;
    private static Product product2;
    private static Product product3;
    private static Product product4;
    private static SessionFactory factory;

    static {
        buyer1 = new Buyer("Tom");
        buyer2 = new Buyer("Ben");

        product1 = new Product("onion",2.5);
        product2 = new Product("bread",1);
        product3 = new Product("butter",3.5);
        product4 = new Product("milk",5);

        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Buyer.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        Session session=null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(buyer1);
            session.save(buyer2);
            session.save(product1);
            session.save(product2);
            session.save(product3);
            session.save(product4);
            session.getTransaction().commit();

            System.out.println("Tom" + " buys " + getProductsForBuyer("Tom"));
            System.out.println("Ben" + " buys " + getProductsForBuyer("Ben"));
            System.out.println("Persons who bought bread: " + findPersonsWhoBoughtProduct("bread"));
            buyProduct("Tom", "milk");
            System.out.println("Persons who bought milk: " + findPersonsWhoBoughtProduct("milk"));
            buyProduct("Ben", "milk");
            System.out.println("Persons who bought milk: " + findPersonsWhoBoughtProduct("milk"));
            buyProduct("Tom", "onion");
            buyProduct("Tom", "butter");
            buyProduct("Ben", "bread");
            System.out.println("Tom" + " buys " + getProductsForBuyer("Tom"));
            System.out.println("Ben" + " buys " + getProductsForBuyer("Ben"));
            System.out.println("______________________________");
            changeProductPrice("milk",4);
            System.out.println("After changing price:");
            System.out.println("Tom" + " buys " + getProductsForBuyer("Tom"));
            System.out.println("Ben" + " buys " + getProductsForBuyer("Ben"));
            System.out.println("______________________________");
            removeProduct("bread");
            System.out.println("After removing bread:");
            System.out.println("Tom" + " buys " + getProductsForBuyer("Tom"));
            System.out.println("Ben" + " buys " + getProductsForBuyer("Ben"));
            System.out.println("______________________________");
            System.out.println("Persons who bought milk: " + findPersonsWhoBoughtProduct("milk"));
            removeBuyer(buyer2);
            System.out.println("After removing Ben:");
            System.out.println("Tom" + " buys " + getProductsForBuyer("Tom"));
            System.out.println("Ben" + " buys " + getProductsForBuyer("Ben"));
            System.out.println("Persons who bought milk: " + findPersonsWhoBoughtProduct("milk"));
        }
        finally {
            session.close();
            factory.close();
        }
    }

    private  static List<Product> getProductsForBuyer(String buyerName){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Buyer buyer = (Buyer) session.createQuery("FROM Buyer b JOIN FETCH b.productList WHERE b.name = :name")
                .setParameter("name", buyerName)
                .uniqueResult();
        session.getTransaction().commit();
        return buyer != null ? buyer.getProductList() : Collections.emptyList();
    }

    private static List<Buyer> findPersonsWhoBoughtProduct(String productName){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = (Product) session.createQuery("FROM Product p JOIN FETCH p.buyerList WHERE p.productName = :productName")
                .setParameter("productName", productName)
                .uniqueResult();
        session.getTransaction().commit();
        return  product != null ? product.getBuyerList() : Collections.emptyList();
    }

    private static void removeProduct(String productName){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.createQuery(
                        "SELECT p FROM Product p LEFT JOIN FETCH p.buyerList WHERE p.productName = :name", Product.class)
                .setParameter("name", productName)
                .uniqueResult();

        if (product != null) {
            for (Buyer buyer : product.getBuyerList()) {
                buyer.getProductList().remove(product);
            }
            session.remove(product);
        }

        session.getTransaction().commit();
    }

    private static void removeBuyer(Buyer buyer){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Buyer buyerToDelete = session.get(Buyer.class, buyer.getId());
        session.remove(buyerToDelete);
        session.getTransaction().commit();
    }

    private static void buyProduct(String buyerName, String productName){
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Buyer buyer = (Buyer) session.createQuery("FROM Buyer b WHERE b.name = :name")
                .setParameter("name", buyerName)
                .uniqueResult();

        Product product = (Product) session.createQuery("FROM Product p WHERE p.productName = :productName")
                .setParameter("productName", productName)
                .uniqueResult();

        if (buyer != null && product != null) {
            buyer.getProductList().add(product);
            product.getBuyerList().add(buyer);
            session.save(buyer);
            session.save(product);
        } else {
            System.out.println("Buyer or Product not found.");
        }
        session.getTransaction().commit();
    }

    private static void changeProductPrice(String productName, double newPrice){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = (Product) session.createQuery("FROM Product p WHERE p.productName = :productName")
                .setParameter("productName", productName)
                .uniqueResult();
        if (product != null) {
            product.setPrice(newPrice);
            session.save(product);
        } else {
            System.out.println("Product not found.");
        }
        session.getTransaction().commit();
    }
}
