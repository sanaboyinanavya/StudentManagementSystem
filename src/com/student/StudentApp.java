package com.student;
import java.util.List;

public class StudentApp {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        // INSERT (Change id if already exists)
        dao.addStudent(new Student(104, "pavan", "Java", 90));

       //  UPDATE Example //
         dao.updateStudent(102, "gayathri", "Full Stack Java", 98);

        // DELETE Example //
         dao.deleteStudent(104);
         dao.updateStudent(101, "pavan", "Python", 90);
        // FETCH + PRINT
        List<Student> students = dao.getAllStudents();

        System.out.println("----- Student List -----");
        for (Student s : students) {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getCourse() + " " + s.getMarks());
        }
    }
}