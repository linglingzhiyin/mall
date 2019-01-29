<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String staticUrl = "http://static.mall.com";
    request.setAttribute("staticUrl", staticUrl);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>网上商城-综合网购首选-正品低价、品质保障、货到付款、配送及时、放心服务、轻松购物！</title>
    <meta name="description"
          content="专业的综合网上购物商城，在线销售家电、数码通讯、电脑、家居百货、服装服饰、母婴、图书、食品、在线旅游等数万个品牌千万种优质商品。便捷、诚信的服务，为您提供愉悦的网上商城购物体验! ">
    <meta name="Keywords" content="网上购物,网上商城,手机,笔记本,电脑,MP3,CD,VCD,DV,相机,数码,配件,手表,存储卡,淘淘商城">
    <link href="${staticUrl }/css/taotao.css" rel="stylesheet"/>
    <script type="text/javascript">
        window.pageConfig = {
            compatible: true,
            navId: "home",
            enableArea: true
        };
    </script>
    <style type="text/css">
        #categorys-2013 .mc {
            display: block;
        }

        #categorys-2013 .mt {
            background: 0
        }
    </style>
</head>
<body>
<!-- header start -->
<jsp:include page="../commons/header.jsp"/>
<!-- header end -->
<div class="w">
    <div id="o-slide">
        <div class="slide" id="slide">
            <script type="text/javascript">
                ;(function (cfg, doc) {
                    if (!cfg.DATA_MSlide) {
                        cfg.DATA_MSlide = [];
                    }

                   var data=${indexAd1};

                    cfg.DATA_MSlide = data;
                    // 初始化一个广告信息
                    if (cfg.DATA_MSlide.length > 5) {
                        var first = pageConfig.FN_GetCompatibleData(cfg.DATA_MSlide[0]);
                        var TPL = ''
                            + '<ul class="slide-items">'
                            + '<li clstag="homepage|keycount|home2013|09a1">'
                            + '<a href="' + first.href + '" title="' + first.alt + '">'
                            + '<img src="' + first.src + '" width="' + first.width + '" height="' + first.height + '" >'
                            + '</a>'
                            + '</li>'
                            + '</ul><div class="slide-controls"><span class="curr">1</span></div>';
                        doc.write(TPL);
                    }
                })(pageConfig, document);
                ;</script>
        </div><!--slide end-->
        <div class="jscroll" id="mscroll">
            <div class="ctrl" id="mscroll-ctrl-prev"><b></b></div>
            <div class="ctrl" id="mscroll-ctrl-next"><b></b></div>
            <div class="o-list">
                <div class="list" id="mscroll-list"></div>
            </div>
        </div><!--mscroll end-->
        <script type="text/javascript">
            pageConfig.DATA_MScroll = [
                {
                    "alt": "",
                    "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312&advid=107474&guv=&url=http://sale.jd.com/act/hG3N4B2nt6XUCA.html",
                    "index": 0,
                    "src": "http://img11.360buyimg.com/da/jfs/t382/296/691255709/13325/afe321fd/542907d2Nedd5849c.jpg",
                    "ext": ""
                },
                {
                    "alt": "",
                    "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4147&unit=36313&advid=109021&guv=&url=http://sale.jd.com/act/CMNjF21UasZ5ouD.html",
                    "index": 1,
                    "src": "http://img13.360buyimg.com/da/jfs/t283/161/1609640628/12590/ecd295d3/543f2a46N876265d2.jpg",
                    "ext": ""
                },
                {
                    "alt": "",
                    "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4117&unit=36438&advid=108949&guv=&url=http://sale.jd.com/act/u7nZbvw8pcX.html",
                    "index": 2,
                    "src": "http://img14.360buyimg.com/da/jfs/t343/107/1701086212/14927/28286262/54407540N2aace14b.jpg",
                    "ext": ""
                },
                {
                    "alt": "",
                    "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4186&unit=36439&advid=109213&guv=&url=http://jmall.jd.com/shop/fotile/index.html",
                    "index": 3,
                    "src": "http://img10.360buyimg.com/da/jfs/t310/224/1720371440/7690/f8d25a3d/5440a78bN79d1c1c0.jpg",
                    "ext": ""
                },
                {
                    "alt": "",
                    "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4195&unit=36440&advid=109092&guv=&url=http://jmall.jd.com/p203378.html",
                    "index": 4,
                    "src": "http://img11.360buyimg.com/da/jfs/t325/95/1665113883/11374/acc43523/543f97a1N5caa7267.jpg",
                    "ext": ""
                },
                {
                    "alt": "",
                    "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4205&unit=36441&advid=109294&guv=&url=http://sale.jd.com/act/B1wcFZqvaeg.html",
                    "index": 5,
                    "src": "http://img13.360buyimg.com/da/jfs/t304/3/1721407024/12521/225e8303/5440ebe8N8e04f88d.jpg",
                    "ext": ""
                },
                {
                    "alt": "",
                    "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4071&unit=36632&advid=109125&guv=&url=http://sale.jd.com/act/azgJFt1nOK.html",
                    "index": 6,
                    "src": "http://img13.360buyimg.com/da/jfs/t349/295/1695471355/9379/2325a0a1/54407591Nb4735d70.jpg",
                    "ext": "1"
                },
                {
                    "alt": "",
                    "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4210&unit=36443&advid=109215&guv=&url=http://sale.jd.com/act/HJyfM0nqQOz.html",
                    "index": 7,
                    "src": "http://img13.360buyimg.com/da/jfs/t316/141/1726543031/5543/46404f6f/5440aa17N1b411341.jpg",
                    "ext": ""
                },
                {
                    "alt": "",
                    "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=0&unit=36612&advid=108828&guv=&url=http://sale.jd.com/act/Crv8iTP0zjsaVYR.html",
                    "index": 8,
                    "src": "http://img11.360buyimg.com/da/jfs/t313/170/1681775134/10831/7f4b7161/5440715aN1f03f497.jpg",
                    "ext": ""
                }
            ];
            (function (object, data) {
                var a = data, b = [], c = [], d, h;
                a.sort(function (a, b) {
                    return a.ext - b.ext
                });
                while (a.length > 0) {
                    d = a.shift();
                    if (d.ext) {
                        b.push(d)
                    } else {
                        c.push(d)
                    }
                }
                c.sort(function () {
                    return 0.5 - Math.random()
                });
                h = b.length;
                if (h >= 3) {
                    for (var i = 0; i < 3; i++) {
                        a.push(b.shift())
                    }
                } else {
                    for (var i = 0; i < h; i++) {
                        a.push(b.shift())
                    }
                }
                var f = a.length, g = c.length;
                for (var i = 0; i < 18 - f; i++) {
                    if (i > g - 1) {
                        continue
                    }
                    a.push(c.shift())
                }
                var e = [], x;
                e.push("<ul class=\"lh\">");
                for (var i = 0; i < 3; i++) {
                    x = pageConfig.FN_GetCompatibleData(a[i]);
                    e.push("<li class=\"item\"><a href=\"");
                    e.push(x.href);
                    e.push("\"><img src=\"${staticUrl }/images/blank.gif\" style=\"background:url(");
                    e.push(x.src);
                    e.push(") no-repeat #fff center 0;\" alt=\"");
                    e.push(x.alt);
                    e.push("\" width=\"");
                    e.push(x.width);
                    e.push("\" height=\"");
                    e.push(x.height);
                    e.push("\" /></a></li>")
                }
                e.push("</ul>");
                document.getElementById(object).innerHTML = e.join("");
                pageConfig.DATA_MScroll = a
            })("mscroll-list", pageConfig.DATA_MScroll);
        </script>
    </div>
    <div class="m fr da0x70" clstag="homepage|keycount|home2013|10a">
        <script>
            // 右上方广告位
            (function () {
                var data = ${indexAd2};
                var ad = pageConfig.FN_GetRandomData(data);
                ad = pageConfig.FN_GetCompatibleData(ad);
                document.write("<a href=\"" + ad.href + "\" target=\"_blank\"><img data-img=\"2\" src=\"" + ad.src + "\" width=\"" + ad.width + "\" height=\"" + ad.height + "\" alt=\"" + ad.alt + "\" /></a>");
            })();
        </script>
    </div><!--da end-->
    <div id="jdnews" class="m m1">
        <div class="mt">
            <h2>淘淘快报</h2>
            <div class="extra" clstag="homepage|keycount|home2013|11a"><a href="http://www.jd.com/moreSubject.aspx"
                                                                          target="_blank">更多快报&nbsp;&gt;</a></div>
        </div>
        <div class="mc">
            <ul>
                <li class="odd" clstag="homepage|keycount|home2013|11b1"><a href="http://club.jr.jd.com/girls/jingxuan"
                                                                            target="_blank" title="38女人节得3800理财金">38女人节得3800理财金</a>
                </li>
                <li clstag="homepage|keycount|home2013|11b1"><a href="http://sale.jd.com/act/U0jwsxIFrmO.html"
                                                                target="_blank" title="开学季音像299减99">开学季音像299减99</a></li>
                <li class="odd" clstag="homepage|keycount|home2013|11b1"><a
                        href="http://sale.jd.com/act/Kz4QnjJMuL.html" target="_blank" title="情定金生相约钻石婚">情定金生相约钻石婚</a>
                </li>
                <li clstag="homepage|keycount|home2013|11b1"><a href="http://sale.jd.com/act/Z5o4RNyF2Uv.html"
                                                                target="_blank" title="爆款造型品 扮靓美人计">爆款造型品 扮靓美人计</a></li>
            </ul>
        </div>
    </div>
    <!--新闻结束-->

    <div data-widget="tabs" class="m _520_a_lifeandjourney_1" id="virtuals-2014">
        <div class="mt">
            <ul class="fore1 lh">
                <li class="fore1 abtest_huafei" data-iframe="http://chongzhi.jd.com/index-newEntry.htm"
                    clstag="homepage|keycount|home2013|12a"><a target="_blank"
                                                               href="http://chongzhi.jd.com/"><s></s>话费<i></i></a></li>
                <li class="fore2 abtest_lvxing" data-iframe="http://trip.jd.com/html/newTravel.html"
                    clstag="homepage|keycount|home2013|12b"><a target="_blank"
                                                               href="http://trip.jd.com/"><s></s>旅行<i></i></a></li>
                <li class="fore3 abtest_caipiao" data-iframe="http://caipiao.jd.com/caipiao-jd.htm"
                    clstag="homepage|keycount|home2013|12c"><a target="_blank"
                                                               href="http://caipiao.jd.com/"><s></s>彩票<i></i></a></li>
                <li class="fore4 abtest_youxi" data-iframe="http://card.jd.com/html/card-jdIndex.html"
                    clstag="homepage|keycount|home2013|12d"><a target="_blank"
                                                               href="http://game.jd.com/"><s></s>游戏<i></i></a></li>
            </ul>
            <ul class="fore2 lh">
                <li class="fore5 abtest_jipiao" clstag="homepage|keycount|home2013|12e"><a target="_blank"
                                                                                           href="http://jipiao.jd.com/"><s></s>机票</a>
                </li>
                <li class="fore6 abtest_dianyingpiao" clstag="homepage|keycount|home2013|12f"><a target="_blank"
                                                                                                 href="http://movie.jd.com/"><s></s>电影票</a>
                </li>
                <li class="fore7 abtest_yanchupiao" clstag="homepage|keycount|home2013|12g"><a target="_blank"
                                                                                               href="http://licai.jd.com/"><s></s>理财</a>
                </li>
                <li class="fore8 abtest_shuidianmei" clstag="homepage|keycount|home2013|12h"><a target="_blank"
                                                                                                href="http://jiaofei.jd.com/"><s></s>水电煤</a>
                </li>
            </ul>
        </div>
        <div class="mc">
            <a href="#none" class="close">×</a>
            <div class="virtuals-iframes hide">
                <iframe width="100%" scrolling="no" height="139px" frameborder="0"></iframe>
            </div>
            <div class="virtuals-iframes hide">
                <iframe width="100%" scrolling="no" height="139px" frameborder="0"></iframe>
            </div>
            <div class="virtuals-iframes hide">
                <iframe width="100%" scrolling="no" height="139px" frameborder="0"></iframe>
            </div>
            <div class="virtuals-iframes hide">
                <iframe width="100%" scrolling="no" height="139px" frameborder="0"></iframe>
            </div>
        </div>
    </div>

    <!--virtuals end-->
    <span class="clr"></span>
</div>

<!-- footer start -->
<jsp:include page="../commons/footer.jsp"/>
<!-- footer end -->

<script type="text/javascript" src="${staticUrl }/js/home.js" charset="utf-8"></script>
</body>
</html>