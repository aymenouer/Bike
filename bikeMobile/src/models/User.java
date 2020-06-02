/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author aymen
 */
public class User {
       private int id;
      private int enabled;
      private String fname ;
      private String lname ;
      private String mail ;
      private int number;
      private String username;
      private String pass ;
      private String role ;
      private int age;
      private String image;

    public User() {
    }

    public User(String fname, String lname, String mail, int number, String username, String pass, String role, int age, String image) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.number = number;
        this.username = username;
        this.pass = pass;
        this.role = role;
        this.age = age;
        this.image = image;
    }

    public User(String fname, String lname, String mail, int number, String username, String pass, int age, String image) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.number = number;
        this.username = username;
        this.pass = pass;
        this.age = age;
        this.image = image;
    }
    

    public User(int id, int enabled, String fname, String lname, String mail, int number, String username, String role, int age, String image) {
        this.id = id;
        this.enabled = enabled;
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.number = number;
        this.username = username;
        this.role = role;
        this.age = age;
        this.image = image;
    }

  
      
   

   
   

   
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", enabled=" + enabled + ", fname=" + fname + ", lname=" + lname + ", mail=" + mail + ", number=" + number + ", username=" + username + ", pass=" + pass + ", role=" + role + ", age=" + age + ", image=" + image + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
      
}
