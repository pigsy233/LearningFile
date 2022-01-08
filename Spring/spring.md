# IOC--Bean管理(xml配置文件进行配置)

Spring创建对象

Spring注入属性



## xml配置文件创建对象

```xml
<bean id="user" class="com.atguigu.spring5.User"></bean>
```

在配置文件中使用bean标签，标签里添加对应的属性

id：唯一标识

class：类的全路径（包类路径）

name：作用与id一致，但可以加特殊符号

ref：bean标签的id值

按以上过程创建类时，默认调用无参构造

## DI：依赖注入，就是注入属性

### set方法注入

#### 1.构造类

```java
public class Animal {
    String name;

    String categories;

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

#### 2.配置xml

```xml
<bean id="animal" class="pig.testdemo.Animal">
    <!--property完成属性注入-->
	<property name="name" value="猪"></property>
    <property name="categories" value="哺乳动物"></property>
</bean>
```

### 有参构造函数注入

#### 1.构造类

```java
public class Insect {
    private String name;
    private String categories;
    private String extinct;

    public Insect(String name, String categories, String extinct) {
        this.name = name;
        this.categories = categories;
        this.extinct = extinct;
    }
}
```

#### 2.配置xml

```xml
    <bean id="insect" class="creature.testdemo.Insect">
        <!--
        <constructor-arg index="0" value=""/>
        <constructor-arg index="1" value=""/>
        <constructor-arg index="2" value=""/>
        -->
        <constructor-arg name="name" value="spider"></constructor-arg>
        <constructor-arg name="categories" value="spider"></constructor-arg>
        <constructor-arg name="extinct" value="no"></constructor-arg>
    </bean>
```

### p名称空间注入

可以简化set注入

#### 1.添加p名称空间在配置文件中

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       
       xmlns:p="http://www.springframework.org/schema/p"
       
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
</beans>
```

#### 2.在bean标签里面进行属性注入

```xml
    <bean id="animal" class="creature.Animal" p:name="猪" p:categories="哺乳动物">
    </bean>
```

## xml注入其他类型属性

### null值

```xml
<property name="address">
<null></null>
</property>
```

### 特殊符号

```xml
<property name="address">
	<value><![CDATA[<<南京>>]]></value>
</property>
```

### 外部bean

#### 1.创建两个类service  和 dao

#### 2.在service 中调用dao 里面的方法

```java
//一个接口类以及一个实现类
public interface UserDao {
    public void update();
}


public class UserDaoImpl implements UserDao{
    @Override
    public void update() {
        System.out.println("fucking pig");
    }


}
```

```java
public class AnimalService {

    UserDao userDao ;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
        System.out.println("service add............");
        userDao.update();
    }
}

```



#### 3.在spring配置文件中配置

```xml
    <bean id="animalservice" class="creature.service.AnimalService">
        <!--通过property 使用set方法注入属性-->
        <property name="userDao" ref="userdao"></property>
    </bean>
    <bean id="userdao" class="creature.dao.UserDaoImpl"></bean>
```



### 内部bean和级联赋值

一对多关系：部门和员工

```java
//员工类
public class Employee {
    private String ename;
    private String gender;
    private Department department;

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

//部门类
public class Department {
    private String dname;

    public void setDname(String dname) {
        this.dname = dname;
    }
}

```

#### xml配置文件中进行配置（内部bean）

```xml
    <bean id="emp" class="creature.firm.Employee">
        <property name="ename" value="pigsy"></property>
        <property name="gender" value="female"></property>
        <!--内部bean方式进行配置-->
        <property name="department">
            <bean id="dep" class="creature.firm.Department">
                <property name="dname" value="饲料课"></property>
            </bean>
        </property>

    </bean>
```

#### 级联赋值

##### 1.外部bean方式创建对象，并赋值，通过ref引用

```xml
    <bean id="emp" class="creature.firm.Employee">
        <property name="ename" value="dogsy"></property>
        <property name="gender" value="male"></property>
        <property name="department" ref="dept"></property>
    </bean>
    <bean id="dept" class="creature.firm.Department">
        <property name="dname" value="垃圾场"></property>
    </bean>
```

##### 2.通过set与get方法进行赋值

```xml
    <bean id="emp" class="creature.firm.Employee">
        <property name="gender" value="male"></property>
        <property name="ename" value="monkey"></property>
        <property name="department" ref="dept"></property>
        <!--以下需要get方法-->
        <property name="department.dname" value="狗粮"></property>
    </bean>
    <bean id="dept" class="creature.firm.Department">
        <property name="dname" value="猪食"></property>
    </bean>
```

## xml注入集合类型属性

数组类型

List集合类型

Map集合类型

### 1.创建相应类型

```java
public class Student {

    private String[] courses;

    private List<String> list;

    private Map<String, String> maps;

    private Set<String> set;

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }
}

```

### 2.xml进行配置

```xml
    <bean id="student" class="spring5.collectiontype.Student">
        <property name="data" value="20"></property>
        <property name="courses">
            <array><!--list也可-->
                <value>java</value>
                <value>数据库</value>
            </array>
        </property>
        <property name="list">
            <list>
                <value>鳖</value>
                <value>王八</value>
                <value>乌龟</value>
            </list>
        </property>
        <property name="maps">
            <map>
                <entry key="Java" value="java"></entry>
                <entry key="C#" value="C#"></entry>
            </map>
        </property>
        <property name="set">
            <set>
                <value>1</value>
                <value>3</value>
            </set>
        </property>
    </bean>
```

## xml注入集合类型属性（集合中存储对象类型值）

```java
public class Course {

    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
```

```java
public class Student {
    private List<Course> coursesList;

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }
}
```

```xml
    <bean id="student" class="spring5.collectiontype.Student"><property name="coursesList">
            <list>
                <ref bean="course1"></ref>
                <ref bean="course2"></ref>
            </list>
        </property>
    </bean>
    <bean id="course1" class="spring5.collectiontype.Course">
        <property name="name" value="Java"></property>
    </bean>
    <bean id="course2" class="spring5.collectiontype.Course">
        <property name="name" value="JDBC"></property>
    </bean>
```



## xml集合注入部分提取出来公用

### 1.xml中引入util名称空间

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">
    
</beans>
```

### 2.提取list集合类型属性注入使用

```xml
   <util:list id="bookList">
        <value>母猪的产后护理</value>
        <value>法兰街舞厅入门手册</value>
        <value>精通人性的生活指南</value>
    </util:list>
    <bean id="book" class="spring5.collectiontype.Book">
        <property name="list" ref="bookList"></property>
    </bean>
```

## FactoryBean

Spring有两种类型的bean，一种普通bean，一种工厂bean（factorybean）

普通bean：xml中定义是什么类型就返回什么类型

工厂bean：xml中返回类型可以不一样

### 1.创建类并实现接口的方法，在实现方法中定义返回的bean的类型

需要继承FactoryBean

并以泛型的方式设置返回类型

```java
public class MyBean implements FactoryBean<Course> {
    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setName("fuck");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
```

### 2.xml正常配置

```xml
    <bean id="myBean" class="spring5.factorybean.MyBean">
    </bean>
```

## bean作用域

在spring中设置bean是单实例还是多实例

默认情况下是单实例模式

### 如何设置单实例还是多实例

xml的bean标签中的scope属性值

默认值为 singleton 为单实例，在加载xml配置文件是就会创建对象

可选值 prototype为多实例模式，只有在调用getBean方法的时候才会去创建对象

request

session

```xml
    <bean id="myBean" class="spring5.factorybean.MyBean" scope="singleton">
    </bean>
```

## bean生命周期

1）通过构造器创建bean实例

2）为bean的属性设置值和对其他bean的引用（调用set方法）

3）调用bean初始化的方法（需要进行配置）

4）bean使用（对象获取到了）

5）当容器关闭是，调用bean的销毁方法（需进行配置）

### 演示bean生命周期

```java
public class Orders {

    private String oName;

    public Orders() {
        System.out.println("第一步");
    }

    public void setoName(String oName) {
        System.out.println("第二步");
    }

    public void initMethod(){
        System.out.println("第三步");
    }

    public void destroyMethod(){
        System.out.println("第五步");
    }
}

//测试类
public void testOrders(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");

        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("第四步");

        ((ClassPathXmlApplicationContext)context).close();

}
```

```xml
    <bean id="orders" class="spring5.bean.Orders" init-method="initMethod" destroy-method="destroyMethod">
        <property name="oName" value="wangba"></property>
    </bean>
```

### bean的后置处理器

1）通过构造器创建bean实例

2）为bean的属性设置值和对其他bean的引用（调用set方法）

后置1）把bean实例传递bean后置处理器的方法

执行postProcessBeforeInitialization()

3）调用bean初始化的方法（需要进行配置）

后置2）把bean实例传递bean后置处理器的方法

执行postProcessAfterInitialization()

4）bean使用（对象获取到了）

5）当容器关闭是，调用bean的销毁方法（需进行配置）

### 演示bean后置处理器

```java
public class MyBeanPost implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("do before init");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("do after init");
        return bean;
    }

}
```

```xml
    <bean id="orders" class="spring5.bean.Orders" scope="singleton" init-method="initMethod" destroy-method="destroyMethod">
        <property name="oName" value="wangba"></property>
    </bean>

    <!--配置后置处理器  会为当前配置文件的所有实例添加后置处理器（只要该类impl了BeanPostProcessor）-->
    <bean id="myBeanPost" class="spring5.bean.MyBeanPost"></bean>
```

## xml自动装配

根据指定装配规则（属性名称或属性类型），spring将自动匹配的属性值进行注入

### 演示自动装配过程

```xml
    <!--实现自动装配
        byName 根据属性名称进行注入
        要求bean的id与实际类的属性名称相同
        byType 根据属性类型进行注入
        要求bean的type与实际类的属性类型相同
    -->
    <bean id="employee" class="spring5.autowire.Employee" autowire="byName"></bean>
    <bean id="department" class="spring5.autowire.Department"></bean>
```

## 外部属性文件

### 1.直接配置数据库信息（以德鲁伊数据库为例）

```xml
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/userDb"></property>
        <property name="username" value="root"></property>
        <property name="password" value="root"></property>
    </bean>
```



### 2.引入外部属性文件

```xml
    <context:property-placeholder location="classpath:jdbc.properties"

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${prop.draverClass}"></property>
        <property name="url" value="${prop.url}"></property>
        <property name="username" value="${prop.userName}"></property>
        <property name="password" value="${prop.password}"></property>
    </bean>
```

# IOC--Bean管理（基于注解方式）

目的是简化xml配置

## 针对bean管理创建对象提供注解

1）@Component

2）@Service

3）@Controller

4）@Repository

*以上四个注解功能一样，均可创建bean实例

## 基于注解方式创建对象

1）引入spring-aop依赖

2）开启组件扫描

xml配置

```xml
<!--开启组件扫描
        如果扫描多个包，中间加逗号
        或输入上级包
    -->
    <context:component-scan base-package="spring5.dao,spring5.service"></context:component-scan>

```

用户类

```java
//在注解中value可不写（默认值为首字母小写）
@Component(value = "userService")//形同<bean id="userService" class=".."/>
public class UserService {

    public void add(){
        System.out.println("UserService activated");
    }
}
```

### 自动扫描细节属性

user-default-filters 表示不使用默认filter（默认是所有标签）

include-filter 设置扫描哪些内容

```xml
    <context:component-scan base-package="spring5.service" use-default-filters="false">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
```

exclude-filter设置不扫描哪些内容

```xml
<context:component-scan base-package="spring5.dao">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Repository"/>
    </context:component-scan>
```

## 基于注解方式实现属性注入

xml配置文件需配置自动装配

1）@Autowired：根据属性类型自动注入

```java
//UserDao
public interface UserDao {


    public void add();
}


//UserDao实现类
@Repository
public class UserDaoImpl implements UserDao{

    @Override
    public void add() {
        System.out.println("UserDaoImpl activated");
    }
}

//UserService类
//在注解中value可不写（默认值为首字母小写）
@Service(value = "userService")//形同<bean id="userService" class=".."/>
public class UserService {

    //无需set方法，底层自带
    @Autowired
    private UserDao userDao;

    public void add(){
        System.out.println("UserService activated");
    }
}

```

2）@Qualifier：根据属性名称进行注入

该标签需要与@Autowire一起使用

```java
    @Autowired
    @Qualifier(value = "userDaoImpl")
    private UserDao userDao;
```

3）@Resource：可以根据类型或名称

jdk15已移除

4）@Value：注入普通类型（以上三种针对引用类型）

```java
    @Value(value = "pig")
    private String name;
```

## 完全注解开发

1）创建配置类，替代xml

```java
@Configuration//标记此为配置文件
@ComponentScan(basePackages = "spring5")//形同<context:component-scan base-package="spring5.dao,spring5.service"></context:component-scan>
public class SpringConfig {
}

```

2）编写测试类

```java
    @Test
    public void testConfig(){
        //加载配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }
```

# AOP

不改变源代码就可以增加功能

增强功能大致形式

```java
public void method1(){
    super.method1();
    //增强部分
}
```



## 1.有接口情况

创建接口实现类代理对象

## 2.无接口情况

创建子类的代理对象

## AOP（JDK动态代理）

使用proxy类里的方法创建代理对象

### 1）调用newProxyInstance方法

```java
static Object newProxyInstance(ClassLoader loader, class<?>[] interfaces, InvocationHandler)
```

第一参数，类加载器

第二参数，增强方法所在的类这个类实现的接口，支持多个接口

第三参数，实现这个类的InvocationHandler，创建代理对象，写增强的方法

### 2）编写InvocationHandler的子类

编写其中的invoke方法

```java
class UserDaoImpl implements UserDao{

    private Object obj;
    public void setObj(Object obj){
        this.obj = obj;
    }
	public Object invoke(Object proxy, Method method, Object[] args){
	//before
    Object obj = method.invoke(obj, args);
	//after
    }
}
```

### AOP操作（准备）

Spring框架一般是基于Aspectj实现AOP操作，Aspectj独立于Spring与AOP框架，一般与Spring一起使用

#### 导入依赖

#### 1）基于xml配置文件实现

#### 2）基于注解方式实现

### 切入点表达式

execution(\[权限修饰符\]\[返回类型\]\[类全路径\]\[方法名称\]\([参数列表\])

#### 对com.atguigu.dao.BookDao类里面的add进行增强

execution(* com.atguigu.dao.BookDao.add(..))

\*代表全部权限 

..代表参数列表

#### 对com.atguigu.dao所有类里面的所有方法进行增强

execution(* com.atguigu.dao.\*.\*(..))

## AspectJ注解方式增强

创建两个类

开启**自动扫描**

使用注解创建对象

在增强类上添加注解@Aspect

在spring配置文件中开启生成代理对象/使用注解开启代理对象

在增强类里在作为通知的方法上面添加类型注解，并使用切入点表达式

```java
@Component
public class Dog {

    public void bark(){
        System.out.println("fucking pig............");
    }
}

//被UserProxy增强的类
@Component
public class User {

    @Autowired
    private Dog dog;

    public void add(){
        dog.bark();
        //int a = 10/0;
        System.out.println("User add.............");
    }
}

//User增强类
@Component
@Aspect
public class UserProxy {

    @Before(value = "execution(* spring5.aopanno.User.add(..))")
    public void beforeAdd(){
        System.out.println("before User exec...........");
    }

    @After(value = "execution(* spring5.aopanno.User.add(..))")
    public void afterAdd(){
        System.out.println("after User exec...........");
    }

    @AfterThrowing(value = "execution(* spring5.aopanno.User.add(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing ...................");
    }

    @AfterReturning(value = "execution(* spring5.aopanno.User.add(..))")
    public void afterReturning(){
        System.out.println("afterReturning................");
    }

    @Around(value = "execution(* spring5.aopanno.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around before User exec");

        proceedingJoinPoint.proceed();

        System.out.println("around after User exec");
    }
}
```

```java
@Configuration
@ComponentScan(basePackages = "spring5.aopanno")
@EnableAspectJAutoProxy(proxyTargetClass = true)//true使用cglib代理，false使用jdk代理
public class SpringConfig {
}

```

在未出现异常时，执行顺序如下

```
around before User exec
before User exec...........
fucking pig............
User add.............
afterReturning................
after User exec...........
around after User exec
```

出现异常时,执行顺序如下

around before User exec
before User exec...........
fucking pig............
afterThrowing ...................
after User exec...........

## 细节问题

### 相同的切入点进行抽取

```java
//User增强类
@Component
@Aspect
public class UserProxy {

    
    //提取切入点
    @Pointcut(value = "execution(* spring5.aopanno.User.add(..))")
    public void pointDemo(){

    }

    
    //通过方法名调用
    @Before(value = "pointDemo()")
    public void beforeAdd(){
        System.out.println("before User exec...........");
    }
}
```

### 多个增强类对同一个方法进行增强

可设置优先级对执行顺序进行调整（数值越小优先级越高）

```java
@Component
@Aspect
@Order(3)
public class PersonProxy 

@Component
@Aspect
@Order(1)
public class UserProxy 
```

## AspectJ配置文件增强

创建两个类

在spring配置文件中创建两个类对象

在spring配置文件中配置切入点

```xml
    <bean id="user" class="spring5.aopxml.User"></bean>
    <bean id="userProxy" class="spring5.aopxml.UserProxy"></bean>

    <!--配置aop增强-->
    <aop:config>
        <!--配置切入点-->
        <aop:pointcut id="p" expression="execution(* spring5.aopxml.User.add(..))"/>
        <!--配置切面-->
        <aop:aspect ref="userProxy">
            <aop:before method="beforeAdd" pointcut-ref="p"></aop:before>
        </aop:aspect>
    </aop:config>
```

# JdbcTemplate

Spring对JDBC进行封装，使用JdbcTemplate方便实现对数据库的操作

导入依赖

```xml
        <dependency>
            <groupId>com.github.spt-oss</groupId>
            <artifactId>mysql-connector-java-plus</artifactId>
            <version>0.1.0</version>
        </dependency>    
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.27</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.14</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.8</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>5.2.19.RELEASE</version>
        </dependency>
```

