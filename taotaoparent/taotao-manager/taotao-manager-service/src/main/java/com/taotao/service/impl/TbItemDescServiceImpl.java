package com.taotao.service.impl;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbItemDescServiceImpl implements TbItemDescService {
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Override
    public TbItemDesc selectByPrimaryKey(Long itemId) {
        return tbItemDescMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public int insert(TbItemDesc record) {
        return tbItemDescMapper.insert(record);
    }
}
