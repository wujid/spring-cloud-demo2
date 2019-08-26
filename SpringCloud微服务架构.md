#                        					      			**SpringCloud微服务架构**

### 一、什么是微服务

参考:<https://martinfowler.com/articles/microservices.html>

### **二、微服务与微服务架构**

​	**微服务：**强调的是服务的大小，它关注的是某一个点，是具体解决某一个问题/提供落地对应服务的一个服务应用,狭意的看,可以看作Eclipse里面的一个个微服务工程/或者Module

​	**微服务架构：**

![](G:\我的学习资料\image\微服务架构.png)

微服务架构是⼀种架构模式，它提倡将单⼀应⽤程序划分成⼀组⼩的服务，服务之间互相协调、互相配合，为⽤户提供最终价值。每个服务运⾏在其独⽴的进程中，服务与服务间采⽤轻量级的通信机制互相协作（通常是基于HTTP协议的RESTful API）。每个服务都围绕着具体业务进⾏构建，并且能够被独⽴的部署到⽣产环境、类⽣产环境等。另外，应当尽量避免统⼀的、集中式的服务管理机制，对具体的⼀个服务⽽⾔，应根据业务上下⽂，选择合适的语⾔、⼯具对其进⾏构建。

**优点：**

每个服务足够内聚，足够小，代码容易理解这样能聚焦一个指定的业务功能或业务需求

开发简单、开发效率提高，一个服务可能就是专一的只干一件事。

微服务能够被小团队单独开发，这个小团队是2到5人的开发人员组成。

微服务是松耦合的，是有功能意义的服务，无论是在开发阶段或部署阶段都是独立的。

微服务能使用不同的语言开发。

易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，如Jenkins, Hudson, bamboo 。

微服务易于被一个开发人员理解，修改和维护，这样小团队能够更关注自己的工作成果。无需通过合作才能体现价值。

微服务允许你利用融合最新技术。

微服务只是业务逻辑的代码，不会和HTML,CSS 或其他界面组件混合。

每个微服务都有自己的存储能力，可以有自己的数据库。也可以有统一数据库。

**缺点:**

开发人员要处理分布式系统的复杂性

多服务运维难度，随着服务的增加，运维的压力也在增大

系统部署依赖

服务间通信成本

数据一致性

系统集成测试

性能监控……

### 三、SpringCloud是什么

SpringCloud，基于SpringBoot提供了一套微服务解决方案，包括服务注册与发现，配置中心，全链路监控，服务网关，负载均衡，熔断器等组件，除了基于NetFlix的开源组件做高度抽象封装之外，还有一些选型中立的开源组件。

SpringCloud利用SpringBoot的开发便利性巧妙地简化了分布式系统基础设施的开发，SpringCloud为开发人员提供了快速构建分布式系统的一些工具，包括配置管理、服务发现、断路器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等,它们都可以用SpringBoot的开发风格做到一键启动和部署。

SpringBoot并没有重复制造轮子，它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过SpringBoot风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包

### 四、Springboot和SpringCloud的联系

SpringBoot专注于快速方便的开发单个个体微服务。

SpringCloud是关注全局的微服务协调整理治理框架，它将SpringBoot开发的一个个单体微服务整合并管理起来，

为各个微服务之间提供，配置管理、服务发现、断路器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等集成服务

SpringBoot可以离开SpringCloud独立使用开发项目，但是SpringCloud离不开SpringBoot，属于依赖的关系.

SpringBoot专注于快速、方便的开发单个微服务个体，SpringCloud关注全局的服务治理框架。

### 五、Cloud微服务架构构建

父pom

```xml
    <properties>
        <java.version>1.8</java.version>
        <spring-boot.version>2.1.7.RELEASE</spring-boot.version>
        <spring-cloud.version>Greenwich.SR2</spring-cloud.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.15</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.1.12</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-	spring-boot-starter -->
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>1.3.2</version>
            </dependency>
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-core</artifactId>
                <version>1.2.3</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

#### 1.eureka服务注册与发现

Spring Cloud 封装了 Netflix 公司开发的 Eureka 模块来实现服务注册和发现(请对比Zookeeper)。

Eureka 采用了 C-S 的设计架构。Eureka Server 作为服务注册功能的服务器，它是服务注册中心。

而系统中的其他微服务，使用 Eureka 的客户端连接到 Eureka Server并维持心跳连接。这样系统的维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。SpringCloud 的一些其他模块（比如Zuul）就可以通过 Eureka Server 来发现系统中的其他微服务，并执行相关的逻辑。



ureka包含两个组件：Eureka Server和Eureka Client

Eureka Server提供服务注册服务

各个节点启动后，会在EurekaServer中进行注册，这样EurekaServer中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观的看到

EurekaClient是一个Java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的、使用轮询(round-robin)负载算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳(默认周期为30秒)。如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，EurekaServer将会从服务注册表中把这个服务节点移除（默认90秒）

###### **新建模块cloud-eureka**

1-1:pom引入eureka服务端坐标

```pom.xml
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
```

1-2:新建application.yml配置文件

```yml
server:
  port: 7001

eureka:
  instance:
    #eureka服务端的实例名称
    hostname: localhost 
  client:
    #表示是否将自己注册在EurekaServer上，默认为true。由于当前应用就是EurekaServer，所以置为false
    register-with-eureka: false
    #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false 
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址。
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

1-3:主启动类添加注解@EnableEurekaServer

1-4:访问：<http://localhost:7001/>

###### **新建公共模块(cloud-api)**

2-1:pom文件主要引入lombok（小辣椒）

```xml
		<dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
```

2-2:新建User

```java
package com.cloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 16:33
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class User implements Serializable {

    private String id;
    private String age;
    private String name;
    private String dbSource;
}

```

2-3:新建IUserService

```java
package com.cloud.service;

import com.cloud.model.User;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 16:36
 * @Description:
 */
public interface IUserService {

    public boolean add(User user);

    public User get(String id);

    public List<User> list();
}

```

###### 新建服务提供者模块(cloud-provider-1)

3-1:修改pom文件

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
    
        <!--引入API-->
        <dependency>
            <groupId>com.cloud</groupId>
            <artifactId>cloud-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
    </dependencies>
```

3-2:新建application.yml文件：

```yml
server:
  port: 8001

# MyBatis
mybatis:
  # 搜索指定包别名
  type-aliases-package: com.cloud
  # 配置mapper的扫描，找到所有的mapper.xml映射文件
  mapper-locations:  classpath:mybatis/mapper/**/*.xml
  config-location: classpath:mybatis/mybatis-config.xml

spring:
  application:
    name: provider-user
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource            # 当前数据源操作类型
    driver-class-name: com.mysql.cj.jdbc.Driver             # mysql驱动包
    url: jdbc:mysql://11.222.0.41:3306/cloudDB01             # 数据库名称
    username: kfccspms
    password: ccsad!123$
    dbcp2:
      min-idle: 5                                           # 数据库连接池的最小维持连接数
      initial-size: 5                                       # 初始化连接数
      max-total: 5                                          # 最大连接数
      max-wait-millis: 200                                  # 等待连接获取的最大超时时间

eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka
```

3-3:mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <settings>
        <setting name="cacheEnabled" value="true" />
    </settings>
</configuration>


```

3-4:新建数据访问接口UserDao.java

```java
package com.cloud.dao;

import com.cloud.model.User;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 17:14
 * @Description:
 */
public interface UserDao {
    public boolean addUser(User dept);

    public User findById(String id);

    public List<User> findAll();
}

```

3-5:新建实现类UserServiceImpl

```java
package com.cloud.service.impl;

import com.cloud.dao.UserDao;
import com.cloud.model.User;
import com.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 17:19
 * @Description:
 */
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    public boolean add(User user) {
        return userDao.addUser(user);
    }

    public User get(String id) {
        return userDao.findById(id);
    }

    public List<User> list() {
        return userDao.findAll();
    }
}

```

3-6:新建UserController.java

```java
package com.cloud.controller;

import com.cloud.model.User;
import com.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 17:28
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public boolean add(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> list() {
        return userService.list();
    }
}

```

3-7:新建UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.cloud.dao.UserDao">
	<resultMap type="User" id="user">
		<id     property="id"       column="id"     />
		<result property="age"      column="age"    />
		<result property="name"     column="name"     />
		<result property="dbSource" column="db_source"   />
	</resultMap>

    <select id="findById" resultMap="user" parameterType="String">
		select id,age,name,db_source from sys_user where id=#{id};
	</select>
    <select id="findAll" resultMap="user">
		select id,age,name,db_source from sys_user;
	</select>
    <insert id="addUser" parameterType="User">
		INSERT INTO sys_user(id,name,db_source) VALUES(#{id},#{dname},DATABASE());
	</insert>

</mapper>
```

3-8:启动类添加注解(正常编码中此步骤应该在最前面完成)

```java
package com.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.cloud.dao")
@EnableEurekaClient
public class CloudProvider1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider1Application.class, args);
    }

}
```

3-9:先启动eureka注册中心，再启动provider

3-10:访问<http://localhost:8001/user/list>

##### A.actuator与注册微服务信息完善

**注册信息修改**

pom.xml新增坐标

```xml
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-commons</artifactId>
        </dependency>
```

修改yml配置文件

```yml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka
  instance:
    #自定义显示名称
    instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}
    #是否显示真实地址
    prefer-ip-address: true
```

**info信息完善**

pom.xml新增坐标

```xml
    <!-- actuator监控信息完善 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

yml配置新增

```yml
info:
  app.name: ${spring.application.name}
  company.name: ccs
```

最后的yml信息

```yml
server:
  port: 8001

# MyBatis
mybatis:
  # 搜索指定包别名
  type-aliases-package: com.cloud
  # 配置mapper的扫描，找到所有的mapper.xml映射文件
  mapper-locations:  classpath:mybatis/mapper/**/*.xml
  config-location: classpath:mybatis/mybatis-config.xml

spring:
  application:
    name: provider-user
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource            # 当前数据源操作类型
    driver-class-name: com.mysql.cj.jdbc.Driver             # mysql驱动包
    url: jdbc:mysql://11.222.0.41:3306/cloudDB01             # 数据库名称
    username: kfccspms
    password: ccsad!123$
    dbcp2:
      min-idle: 5                                           # 数据库连接池的最小维持连接数
      initial-size: 5                                       # 初始化连接数
      max-total: 5                                          # 最大连接数
      max-wait-millis: 200                                  # 等待连接获取的最大超时时间

eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka
  instance:
    #自定义显示名称
    instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}
    #是否显示真实地址
    prefer-ip-address: true


info:
  app.name: ${spring.application.name}
  company.name: ccs
```

##### B.服务发现Discovery

对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息

1.修改controller

```java
package com.cloud.controller;

import com.cloud.model.User;
import com.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 17:28
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public boolean add(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> list() {
        return userService.list();
    }

    @RequestMapping(value = "/user/discovery", method = RequestMethod.GET)
    public Map<String, Object> discovery() {
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<String> list = discoveryClient.getServices();
        System.out.println("**********" + list);

        List<ServiceInstance> srvList = discoveryClient.getInstances("provider-user");
        for (int i = 0; i < srvList.size(); i++) {
            ServiceInstance element = srvList.get(i);
            int index = i + 1;
            map.put(index + "-serviceId", element.getServiceId());
            map.put(index + "-host", element.getHost());
            map.put(index + "-port", element.getPort());
            map.put(index + "-url", element.getUri());
        }
        return map;
    }
}
```

2.主启动类添加@EnableDiscoveryClient注解

```java
package com.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.cloud.dao")
@EnableEurekaClient
@EnableDiscoveryClient
public class CloudProvider1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider1Application.class, args);
    }

}

```

3.访问<http://localhost:8001/user/discovery>

4.最终效果

![](G:\我的学习资料\image\服务发现效果图.png)

##### C.eureka集群

**1.C:\Windows\System32\drivers\etc\hosts增加端口映射**

```text
127.0.0.1       eureka7001.com
127.0.0.1       eureka7002.com
127.0.0.1       eureka7003.com
```

**2.同理新建模块cloud-eureka-2/同理新建模块cloud-eureka-3，端口号分别为7002/7003**

**3.修改各自XML中的client.service-url.defaultZone如**

```yml
server:
  port: 7001

eureka:
  instance:
    #eureka服务端的实例名称
    hostname: localhost
  client:
    #表示是否将自己注册在EurekaServer上，默认为true。由于当前应用就是EurekaServer，所以置为false
    register-with-eureka: false
    #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址。
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
```

**4.修改cloud-provider-1的yml配置文件**

```yml
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka
  instance:
    #自定义显示名称
    instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}
    #是否显示真实地址
    prefer-ip-address: true
```

**5.分别启动三个注册服务后启动provider服务**

**6.分别访问     <http://eureka7001.com:7001/>       <http://eureka7002.com:7002/>             <http://eureka7003.com:7003/>**



##### D.eureka的自我保护机制

什么是自我保护模式？

默认情况下，如果EurekaServer在一定时间内没有接收到某个微服务实例的心跳，EurekaServer将会注销该实例（默认90秒）。但是当网络分区故障发生时，微服务与EurekaServer之间无法正常通信，以上行为可能变得非常危险了——因为微服务本身其实是健康的，此时本不应该注销这个微服务。Eureka通过“自我保护模式”来解决这个问题——当EurekaServer节点在短时间内丢失过多客户端时（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。一旦进入该模式，EurekaServer就会保护服务注册表中的信息，不再删除服务注册表中的数据（也就是不会注销任何微服务）。当网络故障恢复后，该Eureka Server节点会自动退出自我保护模式。

在自我保护模式中，Eureka Server会保护服务注册表中的信息，不再注销任何服务实例。当它收到的心跳数重新恢复到阈值以上时，该Eureka Server节点就会自动退出自我保护模式。它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。

综上，自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留），也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮、稳定。

在Spring Cloud中，可以使用eureka.server.enable-self-preservation = false 禁用自我保护模式。

##### E.consumer通过eureka调用provider

新建模块

#### 2.Ribbon负载均衡

Spring Cloud Ribbon是基于Netflix Ribbon实现的一套***客户端* 负载均衡的工具**。

简单的说，Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将Netflix的中间层服务连接在一起。Ribbon客户端组件提供一系列完善的配置项如连接超时，重试等。简单的说，就是在配置文件中列出Load Balancer（简称LB）后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随机连接等）去连接这些机器。我们也很容易使用Ribbon实现自定义的负载均衡算法。

##### A.新建子模块cloud-consumer-1

**A-1：其中pom.xml为**

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--引入API-->
        <dependency>
            <groupId>com.cloud</groupId>
            <artifactId>cloud-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

**A-2：application.yml为**

```yml
server:
  port: 9001
```

**新建ConfigBean.java**

```java
package com.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 19:54
 * @Description:
 */
@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }
}
```

**A-3：新建ConsumerController.java**

```java
package com.cloud.controller;

import com.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 19:55
 * @Description:
 */
@RestController
public class ConsumerController {

    private static final String REST_URL_PREFIX = "http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/user/add")
    public boolean add(User user) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/user/add", user, Boolean.class);
    }

    @RequestMapping(value = "/consumer/user/get/{id}")
    public User get(@PathVariable("id") String id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/user/get/" + id, User.class);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/consumer/user/list")
    public List<User> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/user/list", List.class);
    }
}
```

**A-4：通过直连方式调用分别启动eureka，provider，conusmer后访问**<http://localhost:9001/consumer/dept/list>

**pom.xml添加新坐标**

```xml
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
```

**A-5：ConsumerController.java修改**

```java
private static final String REST_URL_PREFIX = "http://provider-user";
```

**A-6：ConfigBean.java修改**

```java
 	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }
```

**A-7：重新启动访问**<http://localhost:9001/consumer/user/list>

##### B.为了体现出负载均衡，参考provider-1新建模块provider-2，provider-3

**B-1：修改provider-2，provider-3端口号分别为8002，8003；连接的数据库分别为cloudDB02，cloudDB03**

注意:因模仿服务端集群，应用名需要一致

**B-2：分别启动eureka，三个provider，consumer**

**B-3：重新访问http://localhost:9001/consumer/user/list**

##### **C.默认使用轮询算法，cloud提供了7种算法，也可以实现IRule接口使用自定义算法**

**C-1：算法有**

RoundRobinRule：轮询（默认）。

RandomRule：随机。

RetryRule：先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务。

AvailabilityFilteringRule：会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问。

WeightedResponseTimeRule：根据平均响应时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高。刚启动时如果统计信息不足，则使用RoundRobinRule策略，等统计信息足够，会切换到WeightedResponseTimeRule。

BestAvailableRule：会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务。

ZoneAvoidanceRule： 默认规则,复合判断server所在区域的性能和server的可用性选择服务器

**C-2：切换算法**

**修改ConfigBean.java**

```java
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }

    @Bean
    public IRule rule() {
        return new RandomRule();
    }
}
```

##### D.使用自定义算法

**D-1：com下新建包myloadbalance并新增CustomerLoadBalance.java**

<注>官方文档明确给出了警告：

这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，

否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，也就是说

我们达不到特殊化定制的目的了。

```java
package com.myloadbalance;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: wujid
 * @Date: 2019/8/19 20:43
 * @Description: 若要使用自定义轮询算法,那么该类不能放在compentScan及其子包下
 */
@Configuration
public class CustomerLoadBalance {

    @Bean
    public IRule rule() {
        return new RoundRobinRule();
    }
}
```

**D-2:修改启动类**

```java
package com.cloud;

import com.myloadbalance.CustomerLoadBalance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//指定哪个服务使用哪种自定义算法
@RibbonClient(name = "provider-user", configuration = CustomerLoadBalance.class)
public class CloudConsumer1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumer1Application.class, args);
    }

}
```

#### 3.Feign声明式REST调用

```txt
官网解释：
http://projects.spring.io/spring-cloud/spring-cloud.html#spring-cloud-feign
Feign是一个声明式WebService客户端。使用Feign能让编写Web Service客户端更加简单, 它的使用方法是定义一个接口，然后在上面添加注解，同时也支持JAX-RS标准的注解。Feign也支持可拔插式的编码器和解码器。Spring Cloud对Feign进行了封装，使其支持了Spring MVC标准注解和HttpMessageConverters。Feign可以与Eureka和Ribbon组合使用以支持负载均衡。
 Feign是一个声明式的Web服务客户端，使得编写Web服务客户端变得非常容易，只需要创建一个接口，然后在上面添加注解即可。
参考官网：https://github.com/OpenFeign/feign 
Feign能干什么
Feign旨在使编写Java Http客户端变得更容易。
前面在使用Ribbon+RestTemplate时，利用RestTemplate对http请求的封装处理，形成了一套模版化的调用方法。但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。在Feign的实现下，我们只需创建一个接口并使用注解的方式来配置它(以前是Dao接口上面标注Mapper注解,现在是一个微服务接口上面标注一个Feign注解即可)，即可完成对服务提供方的接口绑定，简化了使用Spring cloud Ribbon时，自动封装服务调用客户端的开发量。
Feign集成了Ribbon
利用Ribbon维护了MicroServiceCloud-Dept的服务列表信息，并且通过轮询实现了客户端的负载均衡。而与Ribbon不同的是，通过feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用

```

##### **A.新建cloud-consumer-a-1子模块（参考cloud-consumer-1）**

**A-1:pom.xml新加入坐标**

```XML
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId> spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

完整部分

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--引入API-->
        <dependency>
            <groupId>com.cloud</groupId>
            <artifactId>cloud-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId> spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

**A-2:主启动类新增新注解@EnableFeignClients**

```java
package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//basePackages为必要属性
@EnableFeignClients(basePackages = "com.cloud")
public class CloudConsumer2Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumer2Application.class, args);
    }

}
```

**A-3:修改ConsumerController.java**

```java
package com.cloud.controller;

import com.cloud.model.User;
import com.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 19:55
 * @Description:
 */
@RestController
public class ConsumerController {

   @Autowired
   private IUserService userService;

    @RequestMapping(value = "/consumer/user/add")
    public boolean add(User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/consumer/user/get/{id}")
    public User get(@PathVariable("id") String id) {
        return userService.get(id);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/consumer/user/list")
    public List<User> list() {
        return userService.list();
    }
}
```

##### **B.修改cloud-api模块**

**B-1:pom.xml同样引入新坐标**

```xml
 		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId> spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

完整：

```xml
		<dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId> spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

**B-2:IUserService.java修改后**

```java
package com.cloud.service;

import com.cloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 16:36
 * @Description:
 */
@FeignClient(value = "provider-user-a")
public interface IUserService {

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public boolean add(User user);

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id);

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> list();
}
```

##### C:启动一个服务注册中心，三个提供者及cloud-consumer-a-1消费者进行测试

##### **D：问题：每一个提供者都必须带一个controller，太麻烦**

**D-1:分别新建三个子模块cloud-provider-a-1，cloud-provider-a-2，cloud-provider-a-3（参考cloud-provider-1）**

**D-2：修改其中的UserServiceImpl.java**

```java
package com.cloud.service.impl;

import com.cloud.dao.UserDao;
import com.cloud.model.User;
import com.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 17:19
 * @Description:
 */
@RestController
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public boolean add(User user) {
        return userDao.addUser(user);
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id) {
        User dept = userDao.findById(id);
        return dept;
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> list() {
        return userDao.findAll();
    }

}
```

**D-3：启动测试**

#### 4.Hystrix断路器（服务熔断与降级）

```txt
分布式系统面临的问题:
复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某些时候将不可避免地失败。
服务雪崩:
多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其它的微服务，这就是所谓的“扇出”。如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应”.
对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几秒钟内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障。这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。
```

```txt
Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。
“断路器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

```

##### A.服务熔断

```txt
服务熔断
熔断机制是应对雪崩效应的一种微服务链路保护机制。
当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，进而熔断该节点微服务的调用，快速返回"错误"的响应信息。当检测到该节点微服务调用响应正常后恢复调用链路。在SpringCloud框架里熔断机制通过Hystrix实现。Hystrix会监控微服务间调用的状况，当失败的调用到一定阈值，缺省是5秒内20次调用失败就会启动熔断机制。熔断机制的注解是@HystrixCommand。
```

![](G:\我的学习资料\image\hystrix-1.png)

![](G:\我的学习资料\image\hystrix-2.png)

**A-1:cloud-provider-a-1引入新坐标**

```xml
 		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
```

完整

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-commons</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <!-- actuator监控信息完善 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!--引入API-->
        <dependency>
            <groupId>com.cloud</groupId>
            <artifactId>cloud-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
```

**A-2:主启动类新增注解@EnableCircuitBreaker**

```java
package com.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.cloud.dao")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
public class CloudProviderA1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProviderA1Application.class, args);
    }
}
```

**A-3:UserServiceImpl.java进行修改**

```java
package com.cloud.service.impl;

import com.cloud.dao.UserDao;
import com.cloud.model.User;
import com.cloud.service.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 17:19
 * @Description:
 */
@RestController
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public boolean add(User user) {
        return userDao.addUser(user);
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "hystrixProccess")
    public User get(@PathVariable("id") String id) {
        User dept = userDao.findById(id);
        if (dept == null) {
            throw new NullPointerException("未找到该用户!");
        }
        return dept;
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> list() {
        return userDao.findAll();
    }

    public User hystrixProccess(@PathVariable("id") String id) {
        User user = new User().setName("该方法已被熔断!");
        return user;
    }
}

```

**A-4:修改cloud-consumer-a-1的application.yml文件**

```yml
server:
  port: 9002


eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://localhost:7001/eureka


feign:
  hystrix:
    #开启服务降级
    enabled: true
```

**A-5:分别启动三个服务进行测试**

##### B.服务降级

```txt
什么是服务降级：
整体资源快不够了，忍痛将某些服务先关掉，待渡过难关，再开启回来。
服务降级处理是在客户端实现完成的，与服务端没有关系。
```

**B-1:cloud-api新增ErrorUserServiceImpl.java**

```java
package com.cloud.service;

import com.cloud.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/22 11:31
 * @Description:
 */
@Component
public class ErrorUserServiceImpl implements IUserService {
    public boolean add(User user) {
        return false;
    }

    public User get(String id) {
        return new User().setName("user服务种的get已停止使用!");
    }

    public List<User> list() {
        List<User> list = new ArrayList<User>(10);
        User user = new User().setName("user服务种的list已停止使用!");
        list.add(user);
        return list;
    }
}
```

**B-2:修改IUserService.java**

```java
package com.cloud.service;

import com.cloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Auther: wujid
 * @Date: 2019/8/21 16:36
 * @Description:
 */
//@FeignClient(value = "provider-user-a")
@FeignClient(value = "provider-user-a", fallback = ErrorUserServiceImpl.class)
public interface IUserService {

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public boolean add(User user);

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id);

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> list();
}
```

**B-3:启动三个服务进行测试**

#### 5.hystrixDashboard服务监控

```txt
除了隔离依赖服务的调用以外，Hystrix还提供了准实时的调用监控（Hystrix Dashboard），Hystrix会持续地记录所有通过Hystrix发起的请求的执行信息，并以统计报表和图形的形式展示给用户，包括每秒执行多少请求多少成功，多少失败等。Netflix通过hystrix-metrics-event-stream项目实现了对以上指标的监控。Spring Cloud也提供了Hystrix Dashboard的整合，对监控内容转化成可视化界面。
<注>该监控只针对使用了服务hystrix进行服务熔断的方法，为实现熔断的将不进行记录

```

##### A.新建项目cloud-hystrix-dashboard

**A-1：pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.cloud</groupId>
        <artifactId>spring-cloud-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <groupId>com.cloud</groupId>
    <artifactId>cloud-hystrix-dashboard</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>cloud-hystrix-dashboard</name>
    <description>dashboard服务监控</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

**A-2:application.yml**

```yml
server:
  port: 6001
```

**A-3:主启动类添加注解@EnableHystrixDashboard**

```java
package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class CloudHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixDashboardApplication.class, args);
    }

}
```

**A-4:修改模块cloud-provider-a-1的application.yml**

```yml
server:
  port: 8001

# MyBatis
mybatis:
  # 搜索指定包别名
  type-aliases-package: com.cloud
  # 配置mapper的扫描，找到所有的mapper.xml映射文件
  mapper-locations:  classpath:mybatis/mapper/**/*.xml
  config-location: classpath:mybatis/mybatis-config.xml

spring:
  application:
    name: provider-user-a
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource            # 当前数据源操作类型
    driver-class-name: com.mysql.cj.jdbc.Driver             # mysql驱动包
    url: jdbc:mysql://11.222.0.41:3306/cloudDB01             # 数据库名称
    username: kfccspms
    password: ccsad!123$
    dbcp2:
      min-idle: 5                                           # 数据库连接池的最小维持连接数
      initial-size: 5                                       # 初始化连接数
      max-total: 5                                          # 最大连接数
      max-wait-millis: 200                                  # 等待连接获取的最大超时时间

eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
  instance:
    #自定义显示名称
    instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}
    #是否显示真实地址
    prefer-ip-address: true

#查看监控信息
management:
  endpoints:
    web:
      exposure:
        include: '*'

info:
  app.name: ${spring.application.name}
  company.name: ccs
```

**A-5:分别启动三个服务**

<http://localhost:6001/hystrix>

<http://localhost:8001/user/get/1>

<http://localhost:8001/actuator/hystrix.stream>

![](G:\我的学习资料\image\dashboard.png)

#### 6.zuul路由网关

```txt
Zuul包含了对请求的路由和过滤两个最主要的功能：
其中路由功能负责将外部请求转发到具体的微服务实例上，是实现外部访问统一入口的基础而过滤器功能则负责对请求的处理过程进行干预，是实现请求校验、服务聚合等功能的基础.Zuul和Eureka进行整合，将Zuul自身注册为Eureka服务治理下的应用，同时从Eureka中获得其他微服务的消息，也即以后的访问微服务都是通过Zuul跳转后获得。
注意：Zuul服务最终还是会注册进Eureka
提供=代理+路由+过滤三大功能
```

##### A.新建模块cloud-zuul

**A-1:pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.cloud</groupId>
        <artifactId>spring-cloud-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <groupId>com.cloud</groupId>
    <artifactId>cloud-zuul</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>cloud-zuul</name>
    <description>zuul路由网关</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
        </dependency>
        <!-- actuator监控信息完善 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

**A-2:新建application.yml**

```yml
server:
  port: 8100


spring:
  application:
    name: cloud-zuul

eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
  instance:
    #自定义显示名称
    instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}
    #是否显示真实地址
    prefer-ip-address: true

management:
  endpoints:
    web:
      exposure:
        include: '*'

info:
  app.name: ${spring.application.name}
  company.name: ccs
```

**A-3:主启动类添加@EnableZuulProxy注解**

```java
package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class CloudZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZuulApplication.class, args);
    }
}
```

**A-4:启动一个eureka,一个服务提供者cloud-provider-a-1和当前服务**

**A-5:修改C:\Windows\System32\drivers\etc\hosts新增映射**

```txt
127.0.0.1       zuul.com
```

不启用路由：<http://localhost:8001/user/get/1>

启用路由：zuul服务的ip+需要路由到的服务名<http://zuul.com:8100/provider-user-a/user/get/1>

##### B:自定义路由规则

**B-1:修改yml配置文件**

```yml
server:
  port: 8100


spring:
  application:
    name: cloud-zuul

eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
  instance:
    #自定义显示名称
    instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}
    #是否显示真实地址
    prefer-ip-address: true

zuul:
  #忽略真实服务名如果要忽略全部使用*否则指定要忽略的如:ignored-services: provider-user-a
  ignored-services: "*"
  routes:
    provider-a:
      serviceId: provider-user-a
      path: /my-provider/**
  #设置统一公共前缀
  prefix: /cloud

management:
  endpoints:
    web:
      exposure:
        include: '*'

info:
  app.name: ${spring.application.name}
  company.name: ccs
```

**B-2:重新启动zuul服务**

访问：<http://zuul.com:8100/provider-user-a/user/get/1>

<http://zuul.com:8100/cloud/my-provider/user/get/1>

#### 7.config服务配置中心

```txt
解决的问题：
服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务。由于每个服务都需要必要的配置信息才能运行，所以一套集中式的、动态的配置管理设施是必不可少的。
作用：
不同环境不同配置，动态化的配置更新，分环境部署比如dev/test/prod
运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
```

##### A:git远程配置中心

**A-1:git上新建一个仓库cloud-demo-config**

**A-2:新建一个文件夹用来同步此仓库项目**

```sh
shadow@DESKTOP-7CPSTBJ MINGW64 /d/cloudconfg/cloud-demo-config
$ git status
fatal: not a git repository (or any of the parent directories): .git

shadow@DESKTOP-7CPSTBJ MINGW64 /d/cloudconfg/cloud-demo-config
$ git clone https://github.com/wujid/cloud-demo-config.git
Cloning into 'cloud-demo-config'...
warning: You appear to have cloned an empty repository.

shadow@DESKTOP-7CPSTBJ MINGW64 /d/cloudconfg/cloud-demo-config
$

```

**A-3:cloud-demo-config文件夹下新建一个application.yml**

```yml
spring:
  profiles:
    active:
    - dev
---
spring:
  profiles: dev     #开发环境
  application: 
    name: cloud-config-dev
---
spring:
  profiles: test   #测试环境
  application: 
    name: cloudd-config-test
#  请保存为UTF-8格式
```

**A-4:将此文件推送至git上**

```shell

shadow@DESKTOP-7CPSTBJ MINGW64 /d/cloudconfg/cloud-demo-config/cloud-demo-config (master)
$ git status
On branch master

No commits yet

Untracked files:
  (use "git add <file>..." to include in what will be committed)

        application.yml

nothing added to commit but untracked files present (use "git add" to track)

shadow@DESKTOP-7CPSTBJ MINGW64 /d/cloudconfg/cloud-demo-config/cloud-demo-config (master)
$ git add .

shadow@DESKTOP-7CPSTBJ MINGW64 /d/cloudconfg/cloud-demo-config/cloud-demo-config (master)
$ git commit -m "add yml"
[master (root-commit) bd4e795] add yml
 1 file changed, 15 insertions(+)
 create mode 100644 application.yml

shadow@DESKTOP-7CPSTBJ MINGW64 /d/cloudconfg/cloud-demo-config/cloud-demo-config (master)
$ git push origin master
Enumerating objects: 3, done.
Counting objects: 100% (3/3), done.
Delta compression using up to 8 threads
Compressing objects: 100% (2/2), done.
Writing objects: 100% (3/3), 357 bytes | 357.00 KiB/s, done.
Total 3 (delta 0), reused 0 (delta 0)
To https://github.com/wujid/cloud-demo-config.git
 * [new branch]      master -> master

shadow@DESKTOP-7CPSTBJ MINGW64 /d/cloudconfg/cloud-demo-config/cloud-demo-config (master)
$
```

##### **B:新建模块cloud-config远程配置服务中心**

**B-1:pom.xm**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.cloud</groupId>
        <artifactId>spring-cloud-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <groupId>com.cloud</groupId>
    <artifactId>cloud-config</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>cloud-config</name>
    <description>config远程配置服务中心</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <!-- actuator监控信息完善 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

**B-2:新增application.yml**

```yml
server:
  port: 1001
spring:
  application:
    name: cloud-config
  cloud:
    config:
      server:
        git:
          uri: https://github.com/wujid/cloud-demo-config.git
          username: 
          password: 

```

**B-3:启动类添加注解@EnableConfigServer**

```java
package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }

}
```

**B-4:分别访问**

<http://localhost:1001/application-dev.yml>

<http://localhost:1001/application-test.yml>

**B-5:多种访问规则**

```txt
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

##### C.将**config-client-consumer.yml推送至git**

```yml
server:
  port: 9001

spring:
  application:
    name: config-consumer-config
  profiles: test


eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://localhost:7001/eureka
#      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
feign:
  hystrix:
#    开启服务降级
    enabled: true

---
server:
  port: 9002

spring:
  application:
    name: config-consumer-config
  profiles: dev


eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://localhost:7001/eureka
#      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
feign:
  hystrix:
    #    开启服务降级
    enabled: true

```

##### **D.新建客户端模块cloud-config-client-consumer(参考cloud-config-client-consumer-a-1)**

**D-1:pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.cloud</groupId>
        <artifactId>spring-cloud-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <groupId>com.cloud</groupId>
    <artifactId>cloud-config-client-consumer</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>cloud-config-client-consumer</name>
    <description>config客户端和消费者结合使用</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--引入API-->
        <dependency>
            <groupId>com.cloud</groupId>
            <artifactId>cloud-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId> spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

**D-2:新建bootstrap.yml**

```yml
spring:
  application:
    #需要从github上读取的资源名称，注意没有yml后缀名
    name: config-client-consumer
  cloud:
    config:
      uri: http://config-service.com:1001/
      profile: dev
      label: master
```

**D-3:C:\Windows\System32\drivers\etc\hosts新建域名映射**

```txt
127.0.0.1       config-service.com
127.0.0.1       config-client.com
```

**D-4:分别启动eureka,config,一个provider,本consumer**

访问<http://config-service.com:1001/config-client-consumer-dev.yml>测试联通性

访问<http://localhost:9002/consumer/user/list>

#### 8.Sleuth链路追踪

```txt
微服务架构是通过业务来划分服务的，对外暴露的接口，可能需要很多个服务协同才能完成一个接口功能，如果链路上任何一个服务出现问题，都会形成导致接口调用失败。此时查找出现问题的微服务是很困难的。Spring Cloud Sleuth主要功能就是在分布式系统中提供追踪解决方案，并且兼容支持了zipkin。
```

##### A.zipkin服务端

```
zipkin服务端并非不能自己代码构建，但官方目前建议用已经提供好的server。构建方法也可以查看官网或者github（https://github.com/openzipkin/zipkin#quick-start），默认端口是9411
```

##### B.手动构建服务端

**B-1:新建模块cloud-zipkin-server**

pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.cloud</groupId>
        <artifactId>spring-cloud-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <groupId>com.cloud</groupId>
    <artifactId>cloud-zipkin-server</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>cloud-zipkin-server</name>
    <description>链路追踪服务端</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin-server</artifactId>
            <version>2.10.1</version>
        </dependency>
        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin-autoconfigure-ui</artifactId>
            <version>2.10.1</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

application.yml

```yml
server:
  port: 2001

management:
  metrics:
    web:
      server:
        ##解决IllegalArgumentException: Prometheus requires that all meters with the same name have the same set of tag keys.
        auto-time-requests: false
spring:
  main:
    ##解决The bean 'characterEncodingFilter', defined in class path resource [zipkin/autoconfigure/ui/ZipkinUiAutoConfiguration.class], could not be registered. A bean with that name has already been defined in class path resource [org/springframework/boot/autoconfigure/web/servlet/HttpEncodingAutoConfiguration.class] and overriding is disabled.Action:
    allow-bean-definition-overriding: true
  application:
    name: cloud-zipkin-demo


eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
  instance:
    #自定义显示名称
    instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}
    #是否显示真实地址
    prefer-ip-address: true
```

主启动类

```java
package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class CloudZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZipkinServerApplication.class, args);
    }

}
```

##### C.新建zipkin客户端

**C-1:新建模块cloud-zipkin-consumer-1（参考其他consumer）**

pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.cloud</groupId>
        <artifactId>spring-cloud-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <groupId>com.cloud</groupId>
    <artifactId>cloud-zipkin-consumer-1</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>cloud-zipkin-consumer-1</name>
    <description>zipkin链路追踪客户端</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--引入API-->
        <dependency>
            <groupId>com.cloud</groupId>
            <artifactId>cloud-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId> spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--开启zipkin服务链路跟踪-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

application.yml

```yml
server:
  port: 9001

spring:
  application:
    name: zipkin-consumer-1
  zipkin:
    base-url: http://localhost:2001
    #开启zipkin
    enabled: true
  sleuth:
    sampler:
      #收集追踪信息的比率，如果是0.1则表示只记录10%的追踪数据，
      #如果要全部追踪，设置为1（实际场景不推荐，因为会造成不小的性能消耗）
      probability: 1

eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://localhost:7001/eureka

feign:
  hystrix:
    #开启服务降级
    enabled: true

```

##### D.修改模块cloud-provider-a-1

**D-1:pom.xml新增坐标**

```XML
`		<!--开启zipkin服务链路跟踪-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```

**D-2:修改application.yml**

```yml
server:
  port: 8001

# MyBatis
mybatis:
  # 搜索指定包别名
  type-aliases-package: com.cloud
  # 配置mapper的扫描，找到所有的mapper.xml映射文件
  mapper-locations:  classpath:mybatis/mapper/**/*.xml
  config-location: classpath:mybatis/mybatis-config.xml

spring:
  application:
    name: provider-user-a
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource            # 当前数据源操作类型
    driver-class-name: com.mysql.cj.jdbc.Driver             # mysql驱动包
    url: jdbc:mysql://11.222.0.41:3306/cloudDB01             # 数据库名称
    username: kfccspms
    password: ccsad!123$
    dbcp2:
      min-idle: 5                                           # 数据库连接池的最小维持连接数
      initial-size: 5                                       # 初始化连接数
      max-total: 5                                          # 最大连接数
      max-wait-millis: 200                                  # 等待连接获取的最大超时时间
  zipkin:
    base-url: http://localhost:2001
      #开启zipkin
    enabled: true
  sleuth:
    sampler:
      probability: 1
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
  instance:
    #自定义显示名称
    instance-id: ${spring.cloud.client.ip-address}:${spring.application.name}:${server.port}
    #是否显示真实地址
    prefer-ip-address: true

#查看监控信息
management:
  endpoints:
    web:
      exposure:
        include: '*'

info:
  app.name: ${spring.application.name}
  company.name: ccs

```

