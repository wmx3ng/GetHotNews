package main.scala.main

import java.io.PrintWriter

import main.scala.baidu.hotpoint.GetBaiduHotPoints
import main.scala.takungpao.titles.GetTKPNews

/**
  * Created by wang on 1/28/16.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val baiduHPs = GetBaiduHotPoints.getHotPoints()
    val tkpTitles = GetTKPNews.getTKPNews()
    val res = (baiduHPs ::: tkpTitles).sortWith((kw1, kw2) => kw1.compareToIgnoreCase(kw2) < 0).distinct
    //    res.foreach(println)
    //写到文件中,定时程序处理文件;
    val writer = new PrintWriter("newData", "UTF-8")
    try {
      res.foreach { x => writer.println(x) }
    } finally {
      writer.close()
    }
  }
}
