package model;

public class User {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String department;
    private int salary;
    private int vacation;
    private String request_vacation;


    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getVacation() {
        return vacation;
    }

    public void setVacation(int vacation) {
        this.vacation = vacation;
    }

    public String getRequest_vacation() {
        return request_vacation;
    }

    public void setRequest_vacation(String request_vacation) {
        this.request_vacation = request_vacation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", vacation=" + vacation +
                ", request_vacation='" + request_vacation + '\'' +
                '}';
    }

    public boolean equals(User user) {
        boolean flag = false;
        if(fname == user.getFname())
            if(lname == user.getLname())
                return true;
        else
            flag = false;

        return flag;
    }
}
