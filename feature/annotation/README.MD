#元注解（meta-annotation）
什么叫元注解，意思就是在JDK中定义好的注解。在JDK1.5中提供了4个元注解

#1.0 
@Target
表示该注解用于哪个位置，例如作用在类、方法等上面，详细的作用范围如下：

ElementType.TYPE 类、接口（包括注解类型）或枚举声明
ElementType.FIELD 字段声明
ElementType.METHOD 方法声明
ElementType.PARAMETER 方法的参数声明
ElementType.CONSTRUCTOR 类的构造法声明
ElementType.LOCAL_VARIABLE 局部变量声明
ElementType.ANNOTATION_TYPE 注解声明
ElementType.PACKAGE 包声明
ElementType.TYPE_PARAMETER JDK1.8新加的，类型参数声明
ElementType.TYPE_USE JDK1.8新加的，类型使用声明


#2.0
@Retention
表示注解的生命周期

RetentionPolicy.SOURCE 源码阶段，在编译时会去除
RetentionPolicy.CLASS 注解会保存在class文件中，运行时会去除
RetentionPolicy.RUNTIME 注解会一直存在，在运行时可以利用反射去获取注解上面对应的值

#3.0
@Inherited
表示允许子类继承父类的注解类型

#4.0
@Documented
表示文档注解




# others
关于反射
getMethod和getDeclaredMethod的区别

getMethod 可以获取自身以及父类的被public修饰的方法
getDeclaredMethod 可以获取自身任何的方法，父类的方法是获取不到，哪怕父类的方法是用public修饰的也无法获取。



