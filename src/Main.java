import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mariadb://localhost:3306/employee";
        String username = "root";
        String password = "root";

        try {

            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            //System.out.println("Connected to the database!");

            String query = "SELECT * FROM details";

            ResultSet resultSet = statement.executeQuery(query);

            List<Employee> emp = new ArrayList<>();

            while (resultSet.next()) {
                String name = resultSet.getString("name");

                    String email = resultSet.getString("email");
                    String mobile = resultSet.getString("mobile");
                    String salary = resultSet.getString("salary");
                    String department = resultSet.getString("department");
                    Date joining_date = resultSet.getDate("joining_date");
                    LocalDate date = joining_date.toLocalDate();
                    Employee employee = new Employee(name, email, mobile, salary, department, date);
                    emp.add(employee);
            }
            //Exercise 1 —  Filter the Employee with Name starts with "D"
            System.out.println("Employees whose name starts with D : ");
            emp.stream()
                    .filter(i->i.getName().startsWith("D"))
                    .forEach(System.out::println);
            //Exercise 2 — Filter the Employee who did not have mobile number updated in DB
            System.out.println("Employees without a mobile number:");
            emp.stream()
                    .filter(i -> i.getMobile() == null || i.getMobile().isEmpty())
                    .forEach(System.out::println);
           //Exercise 3: Obtain a list of employees belongs to category “QA” with salary > 10000
            System.out.println("QA employees with salary > 10000:");
            emp.stream()
                    .filter(i -> i.getDepartment().equals("QA"))
                    .filter(i -> Double.parseDouble(i.getSalary()) > 10000)
                    .forEach(System.out::println);
            //Exercise 4 Obtain a list of employees with products belong to department “IT”
            System.out.println("Employee belong to IT");
            emp.stream()
                    .filter(i -> i.getDepartment().equals("IT"))
                    .forEach(System.out::println);
            //Exercise 5 Obtain a list of employees with department = “DEV” and then apply 10% increment in the salary
            System.out.println("Employee belong to department DEV");
            emp.stream()
                    .filter(i -> i.getDepartment().equals("DEV"))
                    .forEach(System.out::println);
            System.out.println("Employee belong to department DEV and 10% increment in salary");
            emp.stream()
                    .filter(i -> i.getDepartment().equals("DEV"))
                    .peek(i-> {
                        int newSalary =(int)(Double.parseDouble(i.getSalary())*1.1);
                        i.setSalary(String.valueOf(newSalary));
                    })
                    .forEach(System.out::println);
            //Exercise 6 — Obtain a list of employees joined between 01-Feb-2021 and 01-Apr-2021

            System.out.println("\n\nEmployees joined between 01-Feb-2021 and 01-Apr-2021");
            LocalDate startDate = LocalDate.of(2021, 2, 1);
            LocalDate endDate = LocalDate.of(2021, 4, 1);
            emp.stream()
                    .filter(i -> i.getJoiningDate().isAfter(startDate) && i.getJoiningDate().isBefore(endDate))
                    .forEach(System.out::println);

            //            Exercise 7 — Get the lowest salary of employee
            Optional<Employee> lowestSalaryEmployee = emp.stream()
                    .min(Comparator.comparingDouble(employee -> Double.parseDouble(employee.getSalary())));
            if (lowestSalaryEmployee.isPresent()) {
                System.out.println("Employee with the lowest salary:");
                System.out.println(lowestSalaryEmployee.get());
            }


//            Exercise 8 — Get the 3 most recently joined
            System.out.println("Three most recently joined employees:");
            emp.stream()
                    .sorted(Comparator.comparing(Employee::getJoiningDate).reversed())
                    .limit(3)
                    .forEach(System.out::println);

//            Exercise 9 — Calculate total  sum of all salary joined in Feb 2021
            System.out.println("Sum of salary joined in FEburary 2021");
            LocalDate febStrart = LocalDate.of(2021,2,1);
            LocalDate febEnd = LocalDate.of(2021,2,28);
            List<String>sal=emp.stream().filter(i->i.getJoiningDate().isBefore(febEnd)&&i.getJoiningDate().isAfter(febStrart)).map(Employee::getSalary).collect(Collectors.toList());
            int res = 0;
            for(String s:sal){
                res+=Integer.valueOf(s);
            }
            System.out.println(res);
//            Exercise 10 — Calculate average salary for employee joined on 14-Mar-2021
            LocalDate March = LocalDate.of(2021,3,14);
            System.out.println("\n\nAverage salary for employee joined on 14-Mar-2021");
            sal = emp.stream().filter(i->i.getJoiningDate().isEqual(March)).map(Employee::getSalary).collect(Collectors.toList());
            res=0;
            for(String s:sal){
                res+=Integer.valueOf(s);
            }
            System.out.println(res/sal.size());



            List<Employee> dev = emp.stream().filter(i->i.getDepartment().equals("DEV")).collect(Collectors.toList());
            List<Employee> qa = emp.stream().filter(i->i.getDepartment().equals("QA")).collect(Collectors.toList());
            List<Employee> it = emp.stream().filter(i->i.getDepartment().equals("IT")).collect(Collectors.toList());
            List<Employee> manager = emp.stream().filter(i->i.getDepartment().equals("MANAGER")).collect(Collectors.toList());

//            Exercise 11  — Obtain a data map with  Salary and employees count
            System.out.println("Salary and employee count map :");
            Map<String, Long> salaryEmployeeCountMap = emp.stream()
                    .collect(Collectors.groupingBy(Employee::getSalary, Collectors.counting()));
            System.out.println(salaryEmployeeCountMap);

//            Exercise 12  — Produce a data map with employee records grouped by department
            System.out.println("Data map with employee records grouped by department");
            Map<String,List<Employee>> map1 = new HashMap<>();
            map1.put("DEV",dev);
            map1.put("QA", qa);
            map1.put("IT",it);
            map1.put("MANAGER", manager);
            System.out.println(map1);

//            Exercise 13 — Produce a data map with department and their salary
            Map<String,List<String>> deptSalaryMap=new HashMap<>();
            List<String> itSalary=new ArrayList<>();
            List<String> devSalary=new ArrayList<>();
            List<String> qaSalary=new ArrayList<>();
            List<String> managerSalary=new ArrayList<>();
            for(var x:it){
                itSalary.add(x.getSalary());
            }
            for(var x:qa){
                qaSalary.add(x.getSalary());
            }
            for(var x:dev){
                devSalary.add(x.getSalary());
            }
            for(var x:manager){
                managerSalary.add(x.getSalary());
            }
            deptSalaryMap.put("IT",itSalary);
            deptSalaryMap.put("DEV",devSalary);
            deptSalaryMap.put("QA",qaSalary);
            deptSalaryMap.put("MANAGER",managerSalary);
            System.out.println("Map of department and their salary list:");
            System.out.println(deptSalaryMap);
            System.out.println();

//            Exercise 14 — Get the most highest paid by category
            Map<String, Optional<Employee>> highestPaidByCategoryMap = emp.stream()
                    .collect(Collectors.groupingBy(Employee::getDepartment,
                            Collectors.maxBy(Comparator.comparingDouble(employee -> Double.parseDouble(employee.getSalary())))));
            System.out.println("Highest paid employee by category:");
            System.out.println(highestPaidByCategoryMap);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
