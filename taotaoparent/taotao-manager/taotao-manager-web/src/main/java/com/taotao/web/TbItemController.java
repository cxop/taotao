package com.taotao.web;

import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.TbItemDescService;
import com.taotao.service.TbItemParamService;
import com.taotao.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("item")
@RestController
public class TbItemController {

    @Autowired
    private TbItemService tbItemService;
    @Autowired
    private TbItemDescService tbItemDescService;
//注入....
    @Autowired
    private TbItemParamService tbItemParamService;
    @RequestMapping("list")
    public EasyUIResult findItemList(@RequestParam("page") int page, @RequestParam("rows") int rows){
        EasyUIResult result = tbItemService.findItemList(page,rows);
        return result;
    }
    /*
       添加
    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult saveTbItem(TbItem tbItem, String desc) {
        TaotaoResult result = tbItemService.saveItem(tbItem, desc);
        return result;
    }*/
    @RequestMapping("update")
    public com.taotao.commons.TaotaoResult update(TbItem record, String desc){

       return tbItemService.updateByPrimaryKeySelective(record,desc);
    }
//商品描述
    @RequestMapping("desc")
    public TaotaoResult desc(@RequestParam("id") Long record) {
        TbItemDesc tbItemDesc = tbItemDescService.selectByPrimaryKey(record);
        TaotaoResult taotaoResult = new TaotaoResult(tbItemDesc);
        return taotaoResult;
    }
//下架
    @RequestMapping("instock")
    public TaotaoResult instock(@RequestParam("ids") Long record){
            TbItem tbItem = new TbItem();
            tbItem.setId(record);
            tbItem.setStatus((byte)2);
            TaotaoResult taotaoResult = tbItemService.updateByPrimaryKeySelective(tbItem,"");
            return taotaoResult;
    }
//上架
    @RequestMapping("reshelf")
    public TaotaoResult reshelf(@RequestParam("ids") Long record){
        TbItem tbItem = new TbItem();
        tbItem.setId(record);
        tbItem.setStatus((byte)1);
        TaotaoResult taotaoResult = tbItemService.updateByPrimaryKeySelective(tbItem,"");

        return taotaoResult;
    }
//
    //删除
@RequestMapping("delete")
public TaotaoResult delete(@RequestParam("ids") List record){

    int n = tbItemService.deleteByPrimaryKey(record);
    return new TaotaoResult(n);
}
/*    @RequestMapping("para")
    public TaotaoResult para(@PathVariable("page") Long record) {
        TbItemParamItem tbItemParamItem =  tbItemParamService.selectByPrimaryKey(record);
         TaotaoResult taotaoResult = new TaotaoResult(tbItemParamItem);
        return taotaoResult;
    }*/
    //保存
    @RequestMapping("save")
    public com.taotao.commons.TaotaoResult saveItem(TbItem record, String desc) {
        com.taotao.commons.TaotaoResult taotaoResult =  tbItemService.saveItem(record,desc);
        return taotaoResult;
    }
    //规格参数
    @RequestMapping("param/list")
    public EasyUIResult list1(@RequestParam("page") int page, @RequestParam("rows") int rows){
        EasyUIResult result = tbItemParamService.queryItemCat(page,rows);
        return result;
    }
    //查询是否添加规格
    @RequestMapping("param/query/itemcatid/{id}")
    public TaotaoResult itemcatid(@PathVariable("id") Long id){
    TbItemParam tbItemParam = tbItemParamService.selectByPrimaryKeyId(id);
    return new TaotaoResult(tbItemParam);

    }
    //添加商品规格
    @RequestMapping("param/save/{ItemParam}")
    public TaotaoResult ItemParam(@PathVariable("ItemParam") Long id, String paramData){
       TaotaoResult taotaoResult =  tbItemParamService.insertItem(id,paramData);
    return taotaoResult;
    }
}


