package com.taotao.web;

import com.taotao.commons.TaotaoResult;
import com.taotao.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemcat")
public class ItemCatController {

    @Autowired
    private TbItemCatService tbItemCatService;

    @CrossOrigin
    @RequestMapping("/all")
    public Object getItemCat() {
        TaotaoResult taotaoResult = tbItemCatService.getItemCat();
        return taotaoResult;
    }
}