# Kotlin

# 一. Kotlin 基础

 #1.var
    是kotlin保留字，用于声明变量。
 
 #2.val
    用于声明常量，常量意思是引用不可变，但并不代表其引用对象也不可变。
 
 #3. :  
    kotlin中类型是后置的，在变量名后跟上 :类型 就可以显示指定类型。同理，它也用于指定函数返回值类型：
    kotlin 用 : 取代了implements和extends保留字。
 
 #4. fun 
     关键字用于声明函数。
     kotlin中类和方法默认是final的（可省略不写），
     这意味着默认情况下，类和方法是不允许被继承和重写的（
     这是为了防止脆弱的基类，即对基类方法的修改会导致子类出现预期之外的行为）。
     只有通过open保留字显示声明该类或方法可以被继承或重写
     
 #5. when
     用于取代switch-case，不需要在每个分支末尾调用break，如果有一个分支命中则会立即返回。
     when是一个表达式，这意味着它有返回值，返回值等于命中分支中最后一条语句的返回值。
     
 #6. ?., ? ,  !!.,!!
     ?.表示当前对象如果为空则不执行，
     ? 表示当前对象可以为空，即可以 = null
     
     !!.的意思是这个参数如果为空,就抛出异常
     !! 表示当前对象不为空的情况下执行
     
 #7. apply  with、let、also。
     扩展函数 
     函数	返回值	调用者角色	如何引用调用者
     also	调用者本身	作为lambda参数	it
     apply	调用者本身	作为lambda接收者	this
     let	lambda返回值	作为lambda参数	it
     with	lambda返回值	作为lambda接收者	this
     
     
     为泛型T对象添加新功能apply()，它接受一个lambda类型的参数block，且lambda调用的发起者是对象本身
     public inline fun <T> T.apply(block: T.() -> Unit): T {
         contract {
             callsInPlace(block, InvocationKind.EXACTLY_ONCE)
         }
         //执行lambda
         block()
         //返回调用者自身
         return this
     }
     
     //为泛型T对象添加新功能also()，它接受一个lambda类型的参数block，且对象是lambda的参数
     public inline fun <T> T.also(block: (T) -> Unit): T {
         contract {
             callsInPlace(block, InvocationKind.EXACTLY_ONCE)
         }
         block(this)
         return this
     }



   
   
 
