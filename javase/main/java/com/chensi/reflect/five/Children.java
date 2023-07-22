package com.chensi.reflect.five;

/***********************************
 * @author chensi
 * @date 2022/4/12 19:36
 ***********************************/
public class Children implements Parents {

 @Override
 public String hello(String name) {
  return "I'm " + name;
 }
}
