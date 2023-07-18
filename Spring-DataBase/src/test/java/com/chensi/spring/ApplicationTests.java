package com.chensi.spring;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	/*操作Redis*/
	@Resource
	private RedisTemplate redisTemplate;

	//@Resource
	//private RedisUtil redisUtil;

	//@Test
	//public void redisTest2() {
	//	//未序列化
	//	redisUtil.set("message", "你好,habi！");
	//}
	//
	//@Test
	//public void redisTest3() {
	//	redisUtil.set("message", "你好,habi！");
	//}

	@Test
	public void processRedisDirtyKey() {
		Set<String> keys = redisTemplate.keys("*-union");
		System.out.println("size: " + keys.size());
		Map<Object, Object> map = new HashMap<>();

		List<String> list = new LinkedList<>();
		for (String key : keys) {
			Object value = redisTemplate.opsForValue().get(key);
			map.put(key, value);
			int index = key.indexOf("-union");
			String newKey = key.substring(0, index);
			list.add(newKey);
		}
		System.out.println("size: " + list.size());
		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i);
			System.out.println(key);
			redisTemplate.opsForValue().set(key, 1, i + 5, TimeUnit.SECONDS);
		}
	}

	//正则匹配字符串
	private boolean isMatcherText(String key, String text) {
		String regx = "(" + key + ")";
		Pattern pattern = Pattern.compile(regx);
		Matcher mather = pattern.matcher(text);
		if (!mather.find()) {
			return false;
		}
		return true;
	}

	//===========================

	/**
	 * RedisTemplate常见方法
	 * String/Hash/List/Set/zSet
	 */
	@Test
	public void testRedisTemplate() {
		String key = "message";
		redisTemplate.opsForValue().set(key, "hello,world");
		//判断是否有key所对应的值
		System.out.println(redisTemplate.hasKey(key));
		//有则取出key值所对应的值
		System.out.println(redisTemplate.opsForValue().get(key));
		//删除单个key值
		//System.out.println(redisTemplate.delete(key));
		//将当前传入的key值序列化为byte[]类型
		System.out.println(redisTemplate.dump(key));
		//设置过期时间
		redisTemplate.expire(key, 2, TimeUnit.SECONDS);
		//设置过期时间
		//redisTemplate.expireAt(key,new Date());
		//查找匹配的key值，返回一个Set集合类型
		//redisTemplate.keys(pattern);
		//返回传入key所存储的值的类型
		//redisTemplate.rename(oldKey,newKey);
		//如果旧值存在时，将旧值改为新值
		//redisTemplate.renameIfAbsent(oldKey,newKey);
		//从redis中随机取出一个key
		//redisTemplate.randomKey();
		//返回当前key所对应的剩余时间
		//redisTemplate.getExpire(key);
		//返回剩余过期时间并且指定时间单位
		//redisTemplate.getExpire(key,unit);
		//将key持久化保存
		//redisTemplate.persist(key);
		//将当前数据库的key移动到指定redis中数据库当中
		//redisTemplate.move(key,6);

		//String 类型
		ValueOperations opsForValue = redisTemplate.opsForValue();
		//opsForValue.set(key, value);    //设置当前的key以及value值
		//opsForValue.set(key, value, offset);//用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
		//opsForValue.set(key, value, timeout, unit);     //设置当前的key以及value值并且设置过期时间
		//opsForValue.setBit(key, offset, value);    //将二进制第offset位值变为value
		//opsForValue.setIfAbsent(key, value);//重新设置key对应的值，如果存在返回false，否则返回true
		//opsForValue.get(key, start, end);    //返回key中字符串的子字符
		//opsForValue.getAndSet(key, value);    //将旧的key设置为value，并且返回旧的key
		//opsForValue.multiGet(keys);            //批量获取值
		//opsForValue.size(key);                //获取字符串的长度
		//opsForValue.append(key, value);    //在原有的值基础上新增字符串到末尾
		//opsForValue.increment(key,double increment);//以增量的方式将double值存储在变量中
		//opsForValue.increment(key,long  increment);    //通过increment(K key, long delta)方法以增量方式存储long值（正值则自增，负值则自减）
		//
		//Map valueMap = new HashMap();
		//valueMap.put("valueMap1","map1");
		//valueMap.put("valueMap2","map2");
		//valueMap.put("valueMap3","map3");
		//opsForValue.multiSetIfAbsent(valueMap);     //如果对应的map集合名称不存在，则添加否则不做修改
		//opsForValue.multiSet(valueMap);                //设置map集合到redis

		//Hash类型
		HashOperations opsForHash = redisTemplate.opsForHash();
		//opsForHash.get(key, field);    //获取变量中的指定map键是否有值,如果存在该map键则获取值，没有则返回null
		//opsForHash.entries(key);    //获取变量中的键值对
		//opsForHash.put(key, hashKey, value);    //新增hashMap值
		//opsForHash.putAll(key, maps);    //以map集合的形式添加键值对
		//opsForHash.putIfAbsent(key, hashKey, value);    //仅当hashKey不存在时才设置
		//opsForHash.delete(key, fields);    //删除一个或者多个hash表字段
		//opsForHash.hasKey(key, field);    //查看hash表中指定字段是否存在
		//opsForHash.increment(key, field, long increment);    //给哈希表key中的指定字段的整数值加上增量increment
		//opsForHash.increment(key, field, double increment);    //给哈希表key中的指定字段的整数值加上增量increment
		//opsForHash.keys(key);                //获取所有hash表中字段
		//opsForHash.values(key);                //获取hash表中存在的所有的值
		//opsForHash.scan(key, options);        //匹配获取键值对，ScanOptions.NONE为获取全部键对

		//List类型
		ListOperations opsForList = redisTemplate.opsForList();
		//opsForList.index(key, index);    //通过索引获取列表中的元素
		//opsForList.range(key, start, end);    //获取列表指定范围内的元素(start开始位置, 0是开始位置，end 结束位置, -1返回所有)
		//opsForList.leftPush(key, value);    //存储在list的头部，即添加一个就把它放在最前面的索引处
		//opsForList.leftPush(key, pivot, value);        //如果pivot处值存在则在pivot前面添加
		//opsForList.leftPushAll(key, value);        //把多个值存入List中(value可以是多个值，也可以是一个Collection value)
		//opsForList.leftPushIfPresent(key, value);    //List存在的时候再加入
		//opsForList.rightPush(key, value);    //按照先进先出的顺序来添加(value可以是多个值，或者是Collection var2)
		//opsForList.rightPushAll(key, value);    //在pivot元素的右边添加值
		//opsForList.set(key, index, value);        //设置指定索引处元素的值
		//opsForList.trim(key, start, end);        //将List列表进行剪裁
		//opsForList.size(key);    //获取当前key的List列表长度
		//
		////移除并获取列表中第一个元素(如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止)
		//opsForList.leftPop(key);
		//opsForList.leftPop(key, timeout, unit);
		//
		////移除并获取列表最后一个元素
		//opsForList.rightPop(key);
		//opsForList.rightPop(key, timeout, unit);
		//
		////从一个队列的右边弹出一个元素并将这个元素放入另一个指定队列的最左边
		//opsForList.rightPopAndLeftPush(sourceKey, destinationKey);
		//opsForList.rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
		//
		////删除集合中值等于value的元素(index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素; index<0, 从尾部开始删除第一个值等于value的元素)
		//opsForList.remove(key, index, value);

		//Set类型
		SetOperations opsForSet = redisTemplate.opsForSet();
		//opsForSet.add(key, values);            //添加元素
		//opsForSet.remove(key, values);        //移除元素(单个值、多个值)
		//opsForSet.pop(key);                    //删除并且返回一个随机的元素
		//opsForSet.size(key);                //获取集合的大小
		//opsForSet.isMember(key, value);        //判断集合是否包含value
		//opsForSet.intersect(key, otherKey);    //获取两个集合的交集(key对应的无序集合与otherKey对应的无序集合求交集)
		//opsForSet.intersect(key, otherKeys);//获取多个集合的交集(Collection var2)
		//opsForSet.intersectAndStore(key, otherKey, destKey);    //key集合与otherKey集合的交集存储到destKey集合中(其中otherKey可以为单个值或者集合)
		//opsForSet.intersectAndStore(key, otherKeys, destKey);    //key集合与多个集合的交集存储到destKey无序集合中
		//opsForSet.union(key, otherKeys);    //获取两个或者多个集合的并集(otherKeys可以为单个值或者是集合)
		//opsForSet.unionAndStore(key, otherKey, destKey);    //key集合与otherKey集合的并集存储到destKey中(otherKeys可以为单个值或者是集合)
		//opsForSet.difference(key, otherKeys);    //获取两个或者多个集合的差集(otherKeys可以为单个值或者是集合)
		//opsForSet.differenceAndStore(key, otherKey, destKey);    //差集存储到destKey中(otherKeys可以为单个值或者集合)
		//opsForSet.randomMember(key);    //随机获取集合中的一个元素
		//opsForSet.members(key);            //获取集合中的所有元素
		//opsForSet.randomMembers(key, count);    //随机获取集合中count个元素
		//opsForSet.distinctRandomMembers(key, count);    //获取多个key无序集合中的元素（去重），count表示个数
		//opsForSet.scan(key, options);    //遍历set类似于Interator(ScanOptions.NONE为显示所有的)
	}


	/**
	 * 测试ping
	 */
	@Test
	public void test() {
		RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
		RedisConnection connection = connectionFactory.getConnection();
		String ping = connection.ping();
		System.out.println(ping);
	}

	/**
	 * 生成uuid
	 */
	@Test
	public void generateUUID() {
		int count = 20;
		List<String> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			UUID uuid = UUID.randomUUID();
			list.add(uuid.toString());
		}
		list.stream().forEach(f -> {
			System.out.print(f + ',');
		});
	}

	@Test
	public void testPrintList(){
		List<String> list = new LinkedList<>();
		list.add("2");
		list.add("1");
		list.add("4");
		list.add("3");
		System.out.println(list);
	}

	@Test
	public void testJsonArray(){
		JSONArray jsonArray = new JSONArray();
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		JSONObject json3 = new JSONObject();
		JSONObject json4 = new JSONObject();
		json1.put("a", 1);
		json2.put("b", 2);
		json3.put("c", 3);
		json4.put("d", 4);

		jsonArray.add(json1);
		jsonArray.add(json2);
		jsonArray.remove(0);
		jsonArray.add(json3);
		jsonArray.add(1,json4);

		System.out.println(jsonArray);
	}

}
