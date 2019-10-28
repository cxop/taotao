package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commons.EasyUIResult;
import com.taotao.commons.ExceptionUtil;
import com.taotao.commons.SystemConstants;
import com.taotao.commons.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public EasyUIResult findItemList(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<TbItem> itemList = tbItemMapper.selectByExample(null);
        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
        long total = pageInfo.getTotal();//总共的数据行数

        //封装结果集
        EasyUIResult result = new EasyUIResult(total,itemList);

        return result;
    }

    @Override
    public int deleteByPrimaryKey(List id) {
        return tbItemMapper.deleteByPrimaryKey(id);
    }
    //开启事务
    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED  //事物的传播
    )
    @Override
    public TaotaoResult updateByPrimaryKeySelective(TbItem record, String desc) {
        try {
            //更新商品基本信息和商品描述
         tbItemMapper.updateByPrimaryKeySelective(record);

         TbItemDesc tbItemDesc = new TbItemDesc();
         tbItemDesc.setItemId(record.getId());
         tbItemDesc.setItemDesc(desc);
         tbItemDescMapper.updateByPrimaryKey(tbItemDesc);
         return TaotaoResult.ok();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("商品更新失败");
            return TaotaoResult.build(SystemConstants.TAOTAO_RESULT_STATUS_ERROR, ExceptionUtil.getStackTrace(e));
        }


    }
    //开启事务
    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED  //事物的传播
    )
    @Override
    public TaotaoResult saveItem(TbItem tbItem, String desc) {
        //添加商品信息  两个表 事务
        try {
            /*Long itemId = IDUtils.genItemId();
            tbItem.setId(itemId);*/
            tbItem.setStatus((byte) 1);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());
            tbItemMapper.insert(tbItem);

            TbItemDesc tbItemDesc = new TbItemDesc();
            tbItemDesc.setItemDesc(desc);
            tbItemDesc.setCreated(new Date());
            tbItemDesc.setUpdated(new Date());
            tbItemDesc.setItemId(tbItem.getId());
            tbItemDescMapper.insert(tbItemDesc);
            return TaotaoResult.ok();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("商品保存失败");
            return TaotaoResult.build(SystemConstants.TAOTAO_RESULT_STATUS_ERROR, ExceptionUtil.getStackTrace(e));
        }
    }
}
