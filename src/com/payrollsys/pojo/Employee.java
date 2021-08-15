package com.payrollsys.pojo;

import java.util.Date;
import java.util.Objects;

public class Employee {
    private String empid;                   //employee id
    private String password;                //password
    private Integer deptno;                 //department number
    private Integer posid;                  //position ID
    private String mgrid;                   //manager's employee id
    private String realname;                //full name
    private String sex;                     //gender
    private Date birthdate;                 //birthday
    private Date hiredate;                  //hire date
    private Date leavedate;                 //resign date
    private Integer onduty;                 //employment status
    private Integer emptype;                //employee type
    private String phone;                   //phone number
    private String email;                   //email address
    private String emercontactperson;       //emergency contact person information
    private String idcard;                  // id card

    /**
     * Constructs employee with employee id.
     * @param empid String employee id
     */
    public Employee(String empid) {
        this.empid = empid;
    }

    /**
     * Constructs employee with all the parameters.
     * @param empid String employee id
     * @param password  String password
     * @param deptno    Integer department number
     * @param posid     Integer position ID
     * @param mgrid     String manager's employee id
     * @param realname  String full name
     * @param sex       String gender
     * @param birthdate Date birthday
     * @param hiredate  Date hire date
     * @param leavedate Date resign date
     * @param onduty    Integer employment status
     * @param emptype   Integer employee type
     * @param phone     Integer phone number
     * @param email     String email address
     * @param emercontactperson String emergency contact person information
     * @param idcard    String  id card
     */
    public Employee(String empid, String password, Integer deptno, Integer posid, String mgrid, String realname, String sex, Date birthdate, Date hiredate, Date leavedate, Integer onduty, Integer emptype, String phone, String email, String emercontactperson, String idcard) {
        this.empid = empid;
        this.password = password;
        this.deptno = deptno;
        this.posid = posid;
        this.mgrid = mgrid;
        this.realname = realname;
        this.sex = sex;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
        this.leavedate = leavedate;
        this.onduty = onduty;
        this.emptype = emptype;
        this.phone = phone;
        this.email = email;
        this.emercontactperson = emercontactperson;
        this.idcard = idcard;
    }

    /**
     * Getter of employee id.
     * @return String employee id
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * Setter of employee id.
     * @param empid String
     */
    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public Integer getPosid() {
        return posid;
    }

    public void setPosid(Integer posid) {
        this.posid = posid;
    }

    public String getMgrid() {
        return mgrid;
    }

    public void setMgrid(String mgrid) {
        this.mgrid = mgrid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public Integer getOnduty() {
        return onduty;
    }

    public void setOnduty(Integer onduty) {
        this.onduty = onduty;
    }

    public Integer getEmptype() {
        return emptype;
    }

    public void setEmptype(Integer emptype) {
        this.emptype = emptype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmercontactperson() {
        return emercontactperson;
    }

    public void setEmercontactperson(String emercontactperson) {
        this.emercontactperson = emercontactperson;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    //+++++++++++++++++++++++++++++++ equals() and hashCode() +++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // https://docs.jboss.org/hibernate/stable/core.old/reference/en/html/persistent-classes-equalshashcode.html ++//
    // https://blog.csdn.net/rocdream/article/details/5424172 (Chinese) +++++++++++++++++++++++++++++++++++++++++++//
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    /**
     * The euals compares the identifier value of both objects.
     * @param o an object o
     * @return true if this equals to o, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empid, employee.empid) &&
                Objects.equals(password, employee.password) &&
                Objects.equals(deptno, employee.deptno) &&
                Objects.equals(posid, employee.posid) &&
                Objects.equals(mgrid, employee.mgrid) &&
                Objects.equals(realname, employee.realname) &&
                Objects.equals(sex, employee.sex) &&
                Objects.equals(birthdate, employee.birthdate) &&
                Objects.equals(hiredate, employee.hiredate) &&
                Objects.equals(leavedate, employee.leavedate) &&
                Objects.equals(onduty, employee.onduty) &&
                Objects.equals(emptype, employee.emptype) &&
                Objects.equals(phone, employee.phone) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(emercontactperson, employee.emercontactperson) &&
                Objects.equals(idcard, employee.idcard);
    }

    /**
     * hashCode methods returns the integer hashed value of the input value.
     * @return an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(empid, password, deptno, posid, mgrid, realname, sex, birthdate, hiredate, leavedate, onduty, emptype, phone, email, emercontactperson, idcard);
    }

    @Override
    public String toString() {
        return "employee{" +
                "empid='" + empid + '\'' +
                ", password='" + password + '\'' +
                ", deptno=" + deptno +
                ", posid=" + posid +
                ", mgrid='" + mgrid + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthdate=" + birthdate +
                ", hiredate=" + hiredate +
                ", leavedate=" + leavedate +
                ", onduty=" + onduty +
                ", emptype=" + emptype +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", emercontactperson='" + emercontactperson + '\'' +
                ", idcard='" + idcard + '\'' +
                '}';
    }
}
