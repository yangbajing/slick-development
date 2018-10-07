# 开始

## Slick 安装

要使用Slick访问数据库，需要添加 slick 相关依赖到 sbt 项目中，可以将如下依赖加入 `build.sbt` 或 `project/Build.scala` 配置文件：

```scala
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3"
)
```

Slick是一个非侵入式的框架，它的操作方法和函数都定义在 **JdbcProfile** 这样的 trait 里。trait 里面的 `val api: API` 定义了一系列的函数和隐式转换来支持FRM式的数据库操作。我们可以决定在什么时候需要引入Slick的支持并导入函数和隐匿转换。就像下面这样：

```scala
import slickdev.slick.mysql.MyProfile.api._
```

访问MySQL数据库，需要先定义一个 **MySQLProfile**。这里，我们实现一个自己的Profile。

@@snip [MyProfile.scala](../../../../../common/src/main/scala/slickdev/slick/mysql/MyProfile.scala) { #MyProfile }
