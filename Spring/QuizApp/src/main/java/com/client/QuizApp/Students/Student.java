package com.client.QuizApp.Students;

import java.time.LocalDate;

public class Student {
    
    private Integer id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Integer age;

    public Student(){}
    
    public Student(Integer id, String name, String email, LocalDate dateOfBirth, Integer age){
        this.id = id;    
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public void setid(Integer id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setEmail(String email){ this.email = email; }
    public void setDateOFBirth(LocalDate dateOfBirth){ this.dateOfBirth = dateOfBirth; }
    public void setAge(Integer age){ this.age = age; }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            "name='" + name + "\'" +
            "email='" + email + "\'" +
            "dateOfBirth=" + dateOfBirth +
            "age=" + age +
        "}";
    }
    
}
