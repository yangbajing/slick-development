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
