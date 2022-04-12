> 反射

反射就是在运行时才知道要操作的类是什么，并且可以在运行时获取类的完整构造，并调用对应的方法。

在JDK中，反射相关的API可以分为以下几个方面：获取反射的Class对象、通过反射创建类对象，通过反射获取类属性方法及构造器。

> 获取反射中的Class对象

- 第一种，使用Class.forName静态方法。当知道类的全路径名时，可以使用这种方法。

``` java
Class clz=Class.forName("java.lang.String");
```

- 第二种，使用,class方法，这种方法只适合在编译前就知道操作的Class.

``` java
Class clz=String.class;
```

- 第三种，使用类对象的getClass()方法。

``` java
String str=Apple.class;
Apple apple=(Apple)clz.newInstance();
```

> 反射API

- Field类：提供类的属性信息，以及对它的动态访问权限，它是一个封装反射类的属性的类。
    - getDeclaredFields:获取所有声明属性，包括公私有；
    - getFields:只获取公有属性。
- Constructor类：提供有关类的构造方法的信息，以及对它的动态访问权限，它是一个封装反射类的构造方法的类。
    - getDeclaredConstructors:获取所有声明的构造方法，包括公私有；
    - getConstructors:只获取公有构造方法。
- Method类：提供关于类的方法的信息，包括抽象方法，它是用来封装反射类方法的一个类。
- Class类：表示正在运行的Java程序中类的实例。
- Object类：object是所有类的父类，所有对象都默认实现了object类的方法。

> 反射案例

- 基本的反射
- 动态代理实现反射
- 通过代理类设置方法拦截器
- 抽象服务的接口
- 使用FastClass完成反射，执行类中的所有方法
- 使用PropertyUtil和BeanUtil拷贝参数
- 使用注解和反射实现日志打印