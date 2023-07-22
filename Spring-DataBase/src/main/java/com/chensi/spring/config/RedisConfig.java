package com.chensi.spring.config;

import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.NettyCustomizer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/*
 * @author  chensi
 * @date  2022/8/1
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String dbHost;

    @Value("${spring.redis.port}")
    private String dbPort;

    @Value("${spring.redis.database}")
    private String dbNum;

    @Value("${spring.redis.password}")
    private String password;
    /**
     * @param redisConnectionFactory redis连接工厂
     * @功能描述 redis作为缓存时配置缓存管理器CacheManager，主要配置序列化方式、自定义
     * <p>
     * 注意：配置缓存管理器CacheManager有两种方式：
     * 方式1：通过RedisCacheConfiguration.defaultCacheConfig()获取到默认的RedisCacheConfiguration对象，
     * 修改RedisCacheConfiguration对象的序列化方式等参数【这里就采用的这种方式】
     * 方式2：通过继承CachingConfigurerSupport类自定义缓存管理器，覆写各方法，参考：
     * https://blog.csdn.net/echizao1839/article/details/102660649
     * <p>
     * 切记：在缓存配置类中配置以后，yaml配置文件中关于缓存的redis配置就不会生效，如果需要相关配置需要通过@value去读取
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration
            //设置key采用String的序列化方式
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer.UTF_8))
            //设置value序列化方式采用jackson方式序列化
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer()))
            //当value为null时不进行缓存
            .disableCachingNullValues()
            //配置缓存空间的前缀
            .prefixCacheNameWith("spring-cache:")
            //全局配置缓存过期时间
            .entryTtl(Duration.ofMillis(30l));
        //专门指定某些缓存空间的配置
        Map<String, RedisCacheConfiguration> map = new HashMap<>();
        return RedisCacheManager
            .builder(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration)
            .withInitialCacheConfigurations(map)
            .build();
    }

    /**
     * 自定义缓存的redis的KeyGenerator【key生成策略】
     * 注意: 该方法只是声明了key的生成策略,需在@Cacheable注解中通过keyGenerator属性指定具体的key生成策略
     * 可以根据业务情况，配置多个生成策略
     * 如: @Cacheable(value = "key", keyGenerator = "keyGenerator")
     */
    @Bean
    public KeyGenerator keyGenerator() {
        /*
          target: 类
          method: 方法
          params: 方法参数
         */
        return ((target, method, params) -> {
            //获取代理对象的最终目标对象
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getSimpleName()).append(":");
            sb.append(method.getName()).append(":");
            //调用SimpleKey的key生成器
            Object key = SimpleKeyGenerator.generateKey(params);
            return sb.append(key);
        });
    }

    /**
     * @param redisConnectionFactory：配置不同的客户端，这里注入的redis连接工厂不同： JedisConnectionFactory、LettuceConnectionFactory
     * @功能描述 ：配置Redis序列化，原因如下：
     * （1） StringRedisTemplate的序列化方式为字符串序列化，
     * RedisTemplate的序列化方式默为jdk序列化（实现Serializable接口）
     * （2） RedisTemplate的jdk序列化方式在Redis的客户端中为乱码，不方便查看，
     * 因此一般修改RedisTemplate的序列化为方式为JSON方式【建议使用GenericJackson2JsonRedisSerializer】
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        //Json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //String序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key采用String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        // value的序列化方式（json序列化）
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式（json序列化）
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }

    public GenericJackson2JsonRedisSerializer serializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://" + dbHost + ":" + dbPort).setDatabase(Integer.parseInt(dbNum));
        if (password != null && !"".equals(password)) {
            singleServerConfig.setPassword(password);
        }
        //心跳
        singleServerConfig.setPingConnectionInterval(1000);
        return (Redisson) Redisson.create(config);
    }

    @Bean
    public ClientResources clientResources(){
        NettyCustomizer nettyCustomizer = new NettyCustomizer() {
            @Override
            public void afterChannelInitialized(Channel channel) {
                channel.pipeline().addLast(
                    new IdleStateHandler(600, 0, 0));
                channel.pipeline().addLast(new ChannelDuplexHandler() {
                    @Override
                    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                        if (evt instanceof IdleStateEvent) {
                            //ctx.disconnect();
                            System.out.println("redis断连...");
                        }
                    }
                });
            }

            @Override
            public void afterBootstrapInitialized(Bootstrap bootstrap) {
                NettyCustomizer.super.afterBootstrapInitialized(bootstrap);
                System.out.println("初始Netty客户端时调用 afterBootstrapInitialized ...");
            }
        };
        return ClientResources.builder().nettyCustomizer(nettyCustomizer).build();
    }
}
