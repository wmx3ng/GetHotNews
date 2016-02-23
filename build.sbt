lazy val commonSettings = Seq(
  organization := "cn.com.deepdata.retrieval",
  version := "0.0.1",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "gethotnews-core"
  )
