package com.student;	
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // INSERT
    public void addStudent(Student student) {

        String sql = "INSERT INTO students (id, name, course, marks) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getCourse());
            ps.setInt(4, student.getMarks());

            ps.executeUpdate();
            System.out.println("✅ Student Added");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("❌ Duplicate ID! Already exists: " + student.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FETCH ALL
    public List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, name, course, marks FROM students";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getInt("marks")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE
    public void updateStudent(int id, String name, String course, int marks) {

        String sql = "UPDATE students SET name=?, course=?, marks=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setInt(3, marks);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Student Updated Successfully!");
            else
                System.out.println("❌ Student Not Found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Student Deleted Successfully! ID = " + id);
            else
                System.out.println("❌ Student Not Found! ID = " + id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 