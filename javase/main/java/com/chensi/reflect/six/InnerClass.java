package com.chensi.reflect.six;

import java.util.Date;
import java.util.StringJoiner;

/***********************************
 * @author chensi
 * @date 2022/4/13 9:09
 ***********************************/
public class InnerClass implements Parents {
    private Long id;
    private String name;
    private Integer sex;
    private Double age;
    private Date birthDay;

    private String address;

    public InnerClass(Long id, String name, Integer sex, Double age, Date birthDay, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthDay = birthDay;
        this.address = address;
    }

    public InnerClass() {

    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void function() {
        System.out.println("反射实现 InnerClass{" +
            "id=" + getId() +
            ", name='" + getName() + '\'' +
            ", sex=" + getSex() +
            ", age=" + getAge() +
            ", birthDay=" + getBirthDay() +
            ", address='" + address + '\'' +
            '}');
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", InnerClass.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("name='" + name + "'")
            .add("sex=" + sex)
            .add("age=" + age)
            .add("birthDay=" + birthDay)
            .add("address='" + address + "'")
            .toString();
    }
}
