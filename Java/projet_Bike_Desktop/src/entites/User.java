/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

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
      

    public User(String mail, String username, String pass) {
        this.mail = mail;
        this.username = username;
        this.pass = pass;
    }

    public User(String fname, String lname, String mail, int number, String username, int age, String image) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.number = number;
        this.username = username;
        this.age = age;
        this.image = image;
    }

    public User(int id,String fname, String lname, String mail, int number, String username, int age, String image, int enabled) {
        this.id=id;
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.number = number;
        this.username = username;
        this.age = age;
        this.image = image;
        this.enabled = enabled;
    }

    public User(int id, String fname, String lname, String mail, int number, String username, String pass, String role, int age, String image) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fname=" + fname + ", lname=" + lname + ", mail=" + mail + ", number=" + number + ", username=" + username + ", pass=" + pass + ", role=" + role + ", age=" + age + ", image=" + image + ", enabled=" + enabled + '}';
    }

    public int getEnabled() {
        return enabled;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
      
      

    



    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.id;
        hash = 19 * hash + Objects.hashCode(this.fname);
        hash = 19 * hash + Objects.hashCode(this.lname);
        hash = 19 * hash + Objects.hashCode(this.mail);
        hash = 19 * hash + this.number;
        hash = 19 * hash + Objects.hashCode(this.username);
        hash = 19 * hash + Objects.hashCode(this.pass);
        hash = 19 * hash + Objects.hashCode(this.role);
        hash = 19 * hash + this.age;
        hash = 19 * hash + Objects.hashCode(this.image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.fname, other.fname)) {
            return false;
        }
        if (!Objects.equals(this.lname, other.lname)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMail() {
        return mail;
    }

    public int getNumber() {
        return number;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getRole() {
        return role;
    }

    public int getAge() {
        return age;
    }

    public String getImage() {
        return image;
    }
      
}
