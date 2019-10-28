package com.taotao.web;

import com.taotao.commons.EUTreeNode;
import com.taotao.commons.EasyUIResult;
import com.taotao.service.TbItemCatService;
import com.taotao.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("item/cat")
@RestController
public class TbItemCatController {
    @Autowired
    private TbItemCatService tbItemCatService;
    @Autowired
    private TbItemParamService tbItemParamService;

    @RequestMapping("list")
    public List<EUTreeNode> itemCatList(@RequestParam(value = "id",defaultValue = "0")long parentId){
        return tbItemCatService.itemCatList(parentId);
    }
    //规格参数
    @RequestMapping("/param/list2")
    public EasyUIResult list2 (@RequestParam("page") int page, @RequestParam("rows") int rows){
        EasyUIResult result = tbItemParamService.queryItemCat(page,rows);
        return result;
    }
}
