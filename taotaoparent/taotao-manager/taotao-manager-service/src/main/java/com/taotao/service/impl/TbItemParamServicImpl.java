package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbItemParamServicImpl implements TbItemParamService {
@Autowired
private TbItemParamItemMapper tbItemParamItemMapper;
@Autowired
private TbItemParamMapper tbItemParamMapper;

    @Override
    public TbItemParamItem selectByPrimaryKey(Long id) {
        return tbItemParamItemMapper.selectByPrimaryKey(id);
    }
    @Override
    public EasyUIResult queryItemCat(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<TbItemParam> list = tbItemParamMapper.queryItemCat();
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();//总共的数据行数

        //封装结果集
        EasyUIResult result = new EasyUIResult(total,list);
        return result;
    }

    @Override
    public TbItemParam selectByPrimaryKeyId(Long id) {
        return tbItemParamMapper.selectByPrimaryKey(id);
    }

    @Override
    public TaotaoResult insertItem(Long id, String paramData) {
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(id);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        int n = tbItemParamMapper.insert(tbItemParam);

        return new TaotaoResult(n);
    }
}
