package com.taotao.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String toPage(){
        return "index";
    }
/*      解析浏览器请求, 跳转指定的页面
        以下为  result 传参通用风格
        目的:获取地址中页面的名字 item-edit(而且,只有风格一样, 可通用)
    jquery.min.js:4 GET http://localhost:8080/rest/page/item-edit?_=1571
    */
   @RequestMapping("rest/page/{page}")
    public String toPage4(@PathVariable("page") String page){
        return page;
    }

    @RequestMapping("page/{page}")
    public String toPage2(@PathVariable("page") String page){
        return page;
    }
    @RequestMapping("/{page}")
    public String toPage3(@PathVariable("page") String page){
        return page;
    }
    @RequestMapping("/rest/item/query/item/desc/{page}")
    public String toPage5(@PathVariable("page") String page){
        return "forward:/item/desc?id="+page;
    }
/*   @RequestMapping("/rest/item/query/item/{item}/{page}")
    public String toPage6(@PathVariable("page") String page,@PathVariable("item") String item){
        return "forward:/item/?"+item+"="+page;
    }*/
    @RequestMapping("rest/item/{page}")
    public String toPage7(@PathVariable("page") String page)
    {
        return "forward:/item/"+page;
    }

}
