package com.taotao.web;

import com.taotao.pojo.TbContent;
import com.taotao.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tbContent")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @RequestMapping("listAll")
    public List<TbContent> listAll(){
        return tbContentService.listAll();
    }
}
