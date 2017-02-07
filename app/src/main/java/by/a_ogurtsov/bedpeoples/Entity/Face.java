package by.a_ogurtsov.bedpeoples.Entity;

import java.util.Date;

public class Face {
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String city;
    private String address;
    private String phone;
    private String whatdidhedo;
    private String user;
    private String password;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) { this.surname = surname;}

    public String getSurname() {
        return surname;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getAddress() { return address;  }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getWhatdidhedo() {
        return whatdidhedo;
    }

    public void setWhatdidhedo(String whatdidhedo) {
        this.whatdidhedo = whatdidhedo;
    }


    public String getUser() { return user; }

    public void setUser(String user) {
        this.user = user;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public Face() {

            }


}
