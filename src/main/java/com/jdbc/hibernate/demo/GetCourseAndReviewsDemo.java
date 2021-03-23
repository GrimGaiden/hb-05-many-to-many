package com.jdbc.hibernate.demo;

import com.jdbc.hibernate.demo.entity.Course;
import com.jdbc.hibernate.demo.entity.Instructor;
import com.jdbc.hibernate.demo.entity.InstructorDetail;
import com.jdbc.hibernate.demo.entity.Review;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetCourseAndReviewsDemo {
    
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .addAnnotatedClass(Review.class)
                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get the course
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);

            // print the course
            System.out.println("\n\n\n" + tempCourse + "\n\n\n");

            // print the course reviews
            System.out.println("\n\n\n" + tempCourse.getReviews() + "\n\n\n");

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }
    }
}