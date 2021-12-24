# redisson-spring-boot-starter 
目前有很多项目还在使用jedis的 `setNx` 充当分布式锁,然而这个锁是有问题的,redisson是java支持redis的redlock的`唯一`实现,
集成该项目后只需要极少的配置.就能够使用redisson的全部功能. 目前支持
`集群模式`,`云托管模式`,`单Redis节点模式`,`哨兵模式`,`主从模式` 配置. 支持 `可重入锁`,`公平锁`,`联锁`,`红锁`,`读写锁` 锁定模式

#### 介绍
1. 我们为什么需要`redisson`?

>`redisson`目前是官方唯一推荐的java版的分布式锁,他支持 `redlock`.具体请查看 [官方文档](https://redis.io/topics/distlock)

1. jedis为什么有问题? 

> 目前jedis是只支持单机的.

> jedis setNx 和设置过期时间是不同步的,在某些极端的情况下会发生死锁.导致程序崩溃.如果没有设置value,
线程1可能会释放线程2的锁


#### 软件架构
1. [redisson](https://github.com/redisson/redisson) 
2. spring boot

#### 使用说明

---

>1.如何使用`分布式锁`?

在方法增加 `@Lock` 注解 [lock参数介绍](readme/lock.md)

####添加依赖
```
<dependency>
  <groupId>io.github.maoxianglong</groupId>
  <artifactId>mxl-redisson-spring-boot-starter</artifactId>
  <version>1.0.0</version>
</dependency>
```
####锁维度支持spel表达式
```
//1.支持spel表达式,keyConstant是常量(可选)
@Lock(keys = "#user.name",keyConstant = "常量"))
public String test(User user) {
    System.out.println("进来了test");
    return "test";
}

```
---

>2.如何存储数据?(目前实现了三个对象模板)

1.RedissonObject 这个是比较通用的模板,任何对象都可以存在这里面,在spring 容器中注入对象即可
```
    @Resource
    private RedissonObject redissonObject;
```
2.RedissonBinary 这个是存储二进制的模板.可以存放图片之内的二进制文件,在spring 容器中注入对象即可
```
    @Resource
    private RedissonBinary redissonBinary;
```
3.RedissonCollection 这个是集合模板,可以存放`Map`,`List`,`Set`集合元素,在spring 容器中注入对象即可
```
    @Resource
    private RedissonCollection redissonCollection;
```

---

>3. 如何session集群?

> 在启动类标注 `@EnableRedissonHttpSession` 即可


# 进阶篇

---

#### 1.如何使用`redisson` 客户端实现自定义操作,只需要在spring 容器中注入redisson客户端就行,如下:

```
    @Autowired
    private RedissonClient redissonClient;
```

---

#### 2.使用spring cache
待更新...

#### 技术交流
mxl_xt@163.com
