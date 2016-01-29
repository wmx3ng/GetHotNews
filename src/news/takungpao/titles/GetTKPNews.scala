package news.takungpao.titles

import java.net.URL

import news.util.Utils

import scala.io.Source

/**
  * Created by wang on 1/28/16.
  */
object GetTKPNews {

  def main(args: Array[String]) {
    getTKPNews().foreach {
      println
    }
    //    analysisTkpPage("http://news.takungpao.com/mainland/").foreach(println)
  }

  //取指定页面集合;
  def getTKPNews() = {
    val news = for (channel <- Utils.takungpaoSet) yield {
      analysisTkpPage(channel)
    }
    news.toList.flatten
  }

  //分析单个网页;
  def analysisTkpPage(url: String) = {
    val source = Source.fromURL(new URL(url)).getLines().map { x => x.trim() }.mkString

    val keywords = for (
      Utils.pageRegEx(kw) <- Utils.pageRegEx findAllIn source;
      if (!(Utils.stops contains kw)) && (!Utils.invalidPrefix.exists { invalid => kw.startsWith(invalid) })
    ) yield {
      //      kw
      //替换掉一些无用的标识;
      Utils.replaceStr.foldLeft(kw)((tmp, rela) => if (tmp.contains(rela._1)) tmp.replaceAll(rela._1, rela._2) else tmp).trim
    }

    keywords.toList
  }

}
