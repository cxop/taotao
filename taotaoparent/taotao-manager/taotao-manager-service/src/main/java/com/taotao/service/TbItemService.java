package com.taotao.service;

import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItem;

import java.util.List;

public interface TbItemService {
    //按照分页查找所有商品信息
    public EasyUIResult findItemList(int page, int rows);
    //添加商品
    TaotaoResult saveItem(TbItem tbItem, String desc);
    //删除
    int deleteByPrimaryKey(List id);

    TaotaoResult updateByPrimaryKeySelective(TbItem record, String desc);
}
