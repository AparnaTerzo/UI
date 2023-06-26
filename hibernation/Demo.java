package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.util.Properties;

public class Demo {


    private static Configuration configuration;
    private static SessionFactory sf;
    private static Session session;
    private static Transaction transaction;
    private static void setConfiguration() {

        configuration = new Configuration().addAnnotatedClass(Employee.class).addAnnotatedClass(Role.class).addAnnotatedClass(Department.class);
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.mariadb.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mariadb://localhost:3306/alien?useSSL=false");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "root");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MariaDBDialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        configuration.setProperties(settings);
    }
    private static void  openSession(){

        sf = configuration.buildSessionFactory();
        session = sf.openSession();
        transaction = session.beginTransaction();
    }
    private static void fetchRecords(){
        System.out.println("Fetch the records");
        openSession();
        Employee emp= (Employee) session.get(Employee.class,3);
        System.out.println(emp.toString());
        transaction.commit();
    }


    private static void saveRecords(){
        System.out.println("2. save Records");
        openSession();
        Department dept = new Department();
        dept.setDepartmentID(6);
        dept.setDepartmentName("analyst");
        session.save(dept);
        transaction.commit();
    }




    private static void newDept() {
        System.out.println("3.  Create new department");
        openSession();
        Department dept = new Department();
        dept.setDepartmentID(5);
        dept.setDepartmentName("Business");
        session.save(dept);

        transaction.commit();
    }



    public static void mapEmployee() {
        openSession();
        System.out.println("4. Map department  to Employee");
       Employee emp = (Employee) session.get(Employee.class, 3);
       Department dept = (Department) session.get(Department.class, 6);
        emp.setDeptId(dept);
        session.save(emp);
        transaction.commit();
    }

    private static void updateEmployee() {
        openSession();
        System.out.println("5.Update the Role name with id=1");

       Role role = (Role) session.get(Role.class, 1);
        role.setRoleName("PLATFORM");
        session.save(role);
        transaction.commit();
    }
    private static void deleteEmployee() {

       openSession();
        System.out.println("6. Delete one Employee");
         Employee emp = session.get(Employee.class, 3);
        session.remove(emp);
        transaction.commit();
    }


    public static void main(String[] args) {

        setConfiguration();
        openSession();
        fetchRecords();
       saveRecords();
       newDept();
        mapEmployee();
        updateEmployee();
        deleteEmployee();

    }


}