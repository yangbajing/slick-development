
scalafmtOnCompile in ThisBuild := true

lazy val root = Project("slick-development-root", base = file("."))
  .aggregate(basic, common)

lazy val book = project.in(file("book"))
  .dependsOn(basic, common)
  .enablePlugins(ParadoxPlugin)
  .settings(basicSettings)

lazy val basic = project.in(file("basic"))
  .dependsOn(common % "compile->compile;test->test")
  .settings(basicSettings)
  .settings()

lazy val common = project.in(file("common"))
  .settings(basicSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.25",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % "2.9.6",
      "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.9.6",
      "com.fasterxml.jackson.module" % "jackson-module-parameter-names" % "2.9.6",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.6",
      "mysql" % "mysql-connector-java" % "6.0.6",
      "org.postgresql" % "postgresql" % "42.2.5",
      "com.typesafe.slick" %% "slick" % "3.2.3",
      "com.github.tminglei" %% "slick-pg" % "0.16.3",
      "com.github.tminglei" %% "slick-pg_circe-json" % "0.16.3",
      "com.typesafe.slick" %% "slick-testkit" % "3.2.3" % Test,
      "org.scalatest" %% "scalatest" % "3.0.5" % Test
    )
  )

lazy val basicSettings = Seq(
  organization := "me.yangbajing",
  organizationName := "Yangbajing's Garden",
  organizationHomepage := Some(url("https://yangbajing.me")),
  homepage := Some(url("http://www.yangbajing.me/slick-development/")),
  startYear := Some(2018),
  scalaVersion := "2.12.6",
  scalacOptions ++= Seq(
    "-encoding",
    "UTF-8", // yes, this is 2 args
    "-feature",
    "-deprecation",
    "-unchecked",
    "-Xlint",
    "-Yno-adapted-args", //akka-http heavily depends on adapted args and => Unit implicits break otherwise
    "-Ypartial-unification",
    "-Ywarn-dead-code"
  ),
  javacOptions in Compile ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),
  shellPrompt := { s =>
    Project.extract(s).currentProject.id + " > "
  },
  test in assembly := {},
  fork in run := true,
  fork in Test := true,
  parallelExecution in Test := false
)
