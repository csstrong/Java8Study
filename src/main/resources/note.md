- lambda的语法
- 在哪里如何使用lambda
- execute around pattern
- Functional interfaces
- Method references
- Type inference
- Composing lambdas

-----
- Predicate boolean test(T t)
- Consumer accept(T t)
- Function<T,R> R apply(T t)
- Supplier<T> T get()

- 匿名内部类 lambda 函数值接口

```java
//第一种方式
  list.sort(new Comparator<Apple>{
     public int compare(Apple o1,Apple o2){
        return o1.getColor().compareTo(o2.getColor());
    }   
  });
```
```java
//第二种方式
list.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));
```
```java
//第三种方式
list.sort(Comparator.comparing(Apple::getColor));
```
#### 创建Stream 的方式
1. Streams from collections
2. Streams from values
3. Streams from arrays
4. Streams from files
5. Streams from functions:creating infinite streams!\
    Fibonacci tuples series
   
6. Generate

7.iteator

### Stream function
- filter
- limit
- skip
- map
- flatmap
- distinct
- match{boolean [nonematch|anymatch|allmatch]}
- find
- reduce
- IntStream
- DoubleStream
- LongStream
- <<Stream in really action>> & number
- NumericStream

function programming style\

You just tell "how" not "do what".\

满分5\

lambda              1\
Stream              3\
Function interface  1\
Optional            1-1.5\
Collector           2-2.5\

(Akka/Docker/Hadoop/Hbase/JVM/Kafka/Linux-c/Lucence/Mahout/Nginx/NIO、AIO、Mina、Netty\
    /Pig/Restful/Scala/Spark/Squid/Storm/ZooKeeper)\
(实战 Concordion/Flume/Hive/Java8/MapReduce/Oozie/Powermock/Sqoop/Yarn)\
\
1. Reducing and summarizing stream elements to a single value
2. Grouping elements
3. Partitioning elements
4. user custom the collector.\
\
averagingDouble()\
averagingInt()\
averagingLong()\
collectingAndThen()\
counting()\
groupingBy()\
groupingBY()\
   
