package com.taotao.service.impl;

import com.taotao.commons.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbItemCatImpl implements TbItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EUTreeNode> itemCatList(long parentId) {
        TbItemCatExample itemCat = new TbItemCatExample();
        itemCat.createCriteria().andParentIdEqualTo(parentId);
        //itemCat.createCriteria()创建查询对象
        //andParentIdEqualTo（） in   between and

        //查找所有的分类
        List<TbItemCat> itemCatList = tbItemCatMapper.selectByExample(itemCat);

        List<EUTreeNode> treeNodeList = new ArrayList<>();
        for (TbItemCat cat : itemCatList){
            EUTreeNode node = new EUTreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent() ? "closed" : "open");
            treeNodeList.add(node);
        }
        return treeNodeList;
    }



}
