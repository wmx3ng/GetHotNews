package cn.com.deepdata.retrieval.hotpoint

import java.net.URL

import cn.com.deepdata.retrieval.util.Utils

import scala.io.Source

/**
  * Created by wang on 1/28/16.
  */
object GetBaiduHotPoints {

  def main(args: Array[String]) {
    getHotPoints().foreach(println)
  }

  def getHotPoints() = {
    val baiduUrls = getBaiduHotPointsChannels()
    val baiduHPs =
      for (testUrl <- baiduUrls) yield {
        GetBaiduHotPoints.getBaiduHotPointsPages(testUrl)
      }
    baiduHPs.toList.flatten
  }

  //取出六个频道的URL;
  def getBaiduHotPointsChannels() = {
    //注意是gbk编码;
    val source = Source.fromURL(new URL(Utils.baiduHotPointsBaseURL + Utils.flagIndex), "gbk").getLines().map { x => x.trim() }.mkString
    val fromIndex = source.indexOf("hblock")
    val endIndex = source.indexOf("mainBody", fromIndex)
    val urlSet = source.substring(fromIndex, endIndex)

    val urls = for (Utils.baiduHotPointsChannelRegEx(url, keyword) <- Utils.baiduHotPointsChannelRegEx findAllIn urlSet; if (url contains ("buzz"))) yield {
      Utils.baiduHotPointsBaseURL + url
    }

    urls.toList
  }

  //解析 URL,取出标题;
  def getBaiduHotPointsPages(url: String) = {
    val source = Source.fromURL(new URL(url), "gbk").getLines().map { x => x.trim() }.mkString
    val fromIndex = source.indexOf("keyword")
    val endIndex = source.indexOf("</table>", fromIndex)

    val originDate = source.substring(fromIndex, endIndex)

    val hotPoints = for (Utils.baiduHotPointsTitleRegEx(hp) <- Utils.baiduHotPointsTitleRegEx findAllIn originDate) yield {
      hp
    }

    hotPoints.toList
  }

}
