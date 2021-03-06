package app.manager.service;

import app.mapper.ItemParamMapper;
import app.model.DataGridResult;
import app.model.TaoTaoResult;
import app.pojo.ItemParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class ItemParamService {

    @Autowired
    private ItemParamMapper itemParamMapper;

    @Cacheable(value = "itemParamCache",keyGenerator = "wiselyKeyGenerator")
    public DataGridResult getItemParamListByPage(int page, int rows){
        PageHelper.startPage(page,rows);
        List<ItemParam> items=itemParamMapper.getAll();
        PageInfo<ItemParam> pageInfo=new PageInfo<ItemParam>(items);
        DataGridResult dataGridResult=new DataGridResult();
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setRows(items);
        return dataGridResult;
    }

    public TaoTaoResult checkParamExsitence(long cid){

        TaoTaoResult result=new TaoTaoResult();
        result.setStatus(200);
        result.setMessage("ok");

        List<ItemParam> itemParams=itemParamMapper.getItemParamsByCid(cid);
        if (itemParams!=null&&itemParams.size()>0){
            result.setData(itemParams.get(0));
            return result;
        }else {
            result.setData(null);
            return result;
        }

    }

    @Transactional
    @CacheEvict(value = "itemParamCache",keyGenerator = "wiselyKeyGenerator")
    public TaoTaoResult saveItemParam(Long cid,String paramData){
        ItemParam itemParam=new ItemParam();
        itemParam.setItem_cat_id(cid);
        itemParam.setParam_data(paramData);
        Date date=new Date();
        itemParam.setCreated(date);
        itemParam.setUpdated(date);
        itemParamMapper.insert(itemParam);
        TaoTaoResult taoTaoResult=new TaoTaoResult(200,"ok",null);
        return taoTaoResult;
    }


    @Transactional
    @CacheEvict(value = "itemParamCache",keyGenerator = "wiselyKeyGenerator")
    public TaoTaoResult deleteItemParams(List<Long> ids){
        for (Long id:ids){
            itemParamMapper.delete(id);
        }
        return new TaoTaoResult(200,"ok",null);
    }

    @Cacheable(value = "portalItemParamCache",keyGenerator = "wiselyKeyGenerator")
    public ItemParam getItemParamById(Long itemId) {
        return itemParamMapper.getOne(itemId);
    }
}
