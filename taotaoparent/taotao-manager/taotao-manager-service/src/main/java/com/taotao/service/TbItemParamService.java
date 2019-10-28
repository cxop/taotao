package com.taotao.service;

import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;

public interface TbItemParamService {
    //查询商品规格
    TbItemParamItem selectByPrimaryKey(Long id);
    //商品规格参数
    public EasyUIResult queryItemCat(int page, int rows);
    TbItemParam selectByPrimaryKeyId(Long id);
    //添加
    TaotaoResult insertItem(Long id, String paramData);
}
