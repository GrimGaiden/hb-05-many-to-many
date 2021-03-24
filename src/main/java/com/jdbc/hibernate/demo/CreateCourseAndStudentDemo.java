package com.jdbc.hibernate.demo;

import com.jdbc.hibernate.demo.entity.Course;
import com.jdbc.hibernate.demo.entity.Instructor;
import com.jdbc.hibernate.demo.entity.InstructorDetail;
import com.jdbc.hibernate.demo.entity.Review;
import com.jdbc.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseAndStudentDemo {
    
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .addAnnotatedClass(Review.class)
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // create a course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            // save the course... and leverege the cascade all
            System.out.println("\n\n\nSaving the course ...");
            session.save(tempCourse);
            System.out.println("Saved the course " + tempCourse + "\n\n\n");

            // create the students
            Student tempStudent1 = new Student("John", "Doe", "john@email.com");
            Student tempStudent2 = new Student("Mary", "Hills", "mary@email.com");

            // add the students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save the students
            System.out.println("\n\n\nSaving the students ...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students " + tempCourse.getStudents() + "\n\n\n");

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }
    }
}