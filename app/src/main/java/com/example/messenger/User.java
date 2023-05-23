package com.example.messenger;

public class User {

    private String id;
    private String name;
    private String surname;
    private int age;
    private boolean online;

    public User(String id, String name, String surname, int age, boolean isOnline) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.online = isOnline;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public boolean isOnline() {
        return online;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", isOnline=" + online +
                '}';
    }
}
