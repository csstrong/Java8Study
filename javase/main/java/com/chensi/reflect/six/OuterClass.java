package com.chensi.reflect.six;

import java.util.Date;
import java.util.StringJoiner;

/***********************************
 * @author chensi
 * @date 2022/4/13 9:05
 ***********************************/
public class OuterClass implements Parents {
    private Long id;
    private String name;
    private Integer sex;
    private Double age;
    private Date birthDay;

    public OuterClass(Long id, String name, Integer sex, Double age, Date birthDay) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public void function() {
        System.out.println("OuterClass{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", sex=" + sex +
            ", age=" + age +
            ", birthDay=" + birthDay +
            '}');
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OuterClass.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("name='" + name + "'")
            .add("sex=" + sex)
            .add("age=" + age)
            .add("birthDay=" + birthDay)
            .toString();
    }
}
