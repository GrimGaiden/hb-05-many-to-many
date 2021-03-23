package com.jdbc.hibernate.demo;

import com.jdbc.hibernate.demo.entity.Instructor;
import com.jdbc.hibernate.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateDemo {
    
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // create the objects
            /* Instructor tempInstructor = 
                new Instructor("Chad", "Darby", "darby@email.com");

            InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.youtube.com", "coding"); */

            Instructor tempInstructor = 
                new Instructor("Madhu", "Patel", "madhu@email.com");

            InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.youtube.com/channel", "guitar");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            System.out.println("\n\n\n\nSaving instructor: " + tempInstructor + "\n\n\n\n");
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}