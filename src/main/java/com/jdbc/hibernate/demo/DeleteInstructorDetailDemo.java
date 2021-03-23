package com.jdbc.hibernate.demo;

import com.jdbc.hibernate.demo.entity.Instructor;
import com.jdbc.hibernate.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteInstructorDetailDemo {
    
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

            // start a transaction
            session.beginTransaction();

            // get the instructor detail object
            int theId = 3;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

            // print the instractor detail
            System.out.println("\n\n\ntempInstructorDetail: " + tempInstructorDetail + "\n\n\n");

            // print the associated instructor
            System.out.println("\n\n\nThe associated instructor: " + tempInstructorDetail.getInstructor() + "\n\n\n");

            // now let's delete the instructor detail
            System.out.println("\n\n\nDeleting tempInstructorDetail: " + tempInstructorDetail + "\n\n\n");

            // remove associated object reference
            // break bi-directional link
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }
        catch(Exception exc){
            exc.printStackTrace();
        } finally {
            // handle connection leak issue
            session.close();

            factory.close();
        }
    }
}