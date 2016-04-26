package cn.com.deepdata.retrieval.startup

import java.io.PrintWriter

import cn.com.deepdata.retrieval.hotpoint.GetBaiduHotPoints
import cn.com.deepdata.retrieval.titles.GetTKPNews

/**
  * Created by wang on 1/28/16.
  */
object Bootstrap {
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
