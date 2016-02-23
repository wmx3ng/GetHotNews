package main.scala.util

/**
  * Created by wang on 1/28/16.
  */
object Utils {
  //停用词;
  val stops = Set("名家专栏", "重点报道", "新闻客户端", "广告服务", "'+tkpu.username +'", "更多", "[详细]", "关于我们", "网站声明", "诚聘英才", "举报投诉须知", "友情链接", "联系我们", "网站地图", "更多>>", "大公首页",
    "网站导航", "注册", "原创", "专栏", "专题", "科技图库", "滚动", "京ICP备13049349号", "京公网安备11010102000014号")

  //网页标识;以此为前缀的需要过滤掉;
  val invalidPrefix = Set("<p>", "</a></p>", "<img src", "<div class=", "<p>　　<strong>", "<b>", "<!--","<span style")
  //无效的网页标识符,需要替换掉;
  val replaceStr = Map("&nbsp;" -> " ", "&quot;" -> "\"", "&ldquo;" -> "\"", "&rdquo;" -> "\"")

  //百度热点;
  val baiduHotPointsBaseURL = "http://top.baidu.com/"
  val flagIndex = "buzz?b=1"
  val baiduHotPointsChannelRegEx = """href="./(.+?)">(.+?)</a>""".r
  val baiduHotPointsTitleRegEx = """keyword"><a class=.+?>(.+?)</a>""".r

  //大公报财经栏目;
  val takungpaoSet = Set("http://finance.takungpao.com/",
    "http://finance.takungpao.com/gscy/",
    "http://finance.takungpao.com/financial/",
    "http://finance.takungpao.com/tech/",
    "http://main.scala.takungpao.com/mainland/"
  )

  val pageRegEx = """target="_blank".*?>(.+?)</a>""".r
}
