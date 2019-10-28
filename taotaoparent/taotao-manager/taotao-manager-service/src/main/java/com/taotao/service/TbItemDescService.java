package com.taotao.service;

import com.taotao.pojo.TbItemDesc;
import org.springframework.stereotype.Component;

@Component
public interface TbItemDescService{
    //商品描述
    TbItemDesc selectByPrimaryKey(Long itemId);
    int insert(TbItemDesc record);

}
