# 引言

Slick（Scala Language-Integrated Connection Kit）是由 Lightbend 开发的函数式关系映射（Functional Relational Mapping）异步访问库，基于Scala语言编写。可以像使用Scala集合库一样的方式来处理SQL数据访问和存储，同时，它也可以直接使用SQL来访问数据库。

一个示例的数据访问代码如下：

```scala
val limit = 10.0

// 查询代码看起来像这样
( for( c <- coffees if c.price < limit ) yield c.name ).result

// 等价的SQL语句类似：select COF_NAME from COFFEES where PRICE < 10.0
```

当使用Scala而不是原始的SQL来编写查询时，你的代码将获得编译器检查和创建可组合的代码块的能力。Slick可以使用它的可扩展查询编译器为不同的数据库后端生成SQL语句。
