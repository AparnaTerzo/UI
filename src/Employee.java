import java.time.LocalDate;
import java.util.Date;

public class Employee {
    String name;
    String email;
    String mobile;
    String salary;
    String department;
    LocalDate joining_date;

    public Employee(String name, String email, String mobile, String salary, String department, LocalDate joining_date) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.salary = salary;
        this.department = department;
        this.joining_date = joining_date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
    public void setJoiningDate(LocalDate joining_date){
        this.joining_date=joining_date;
    }
    public LocalDate getJoiningDate(){
        return joining_date;
    }
    @Override
    public String toString() {
        return
                "name: " + name +
                "\nemail: " + email +
                "\nmobile: " + mobile+
                "\nsalary: " + salary+
                "\ndepartment: " + department +
                "\nJoining_date: " + joining_date +
               "\n";
    }


}
