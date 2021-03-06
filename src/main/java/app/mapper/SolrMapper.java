package app.mapper;

import app.pojo.SolrItem;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface SolrMapper {

    @Select("SELECT a.id,a.title,a.price,a.sell_point,a.image,b.`name` category_name,c.item_desc " +
            "FROM tb_item a LEFT JOIN tb_item_cat b ON a.cid=b.id LEFT JOIN tb_item_desc c ON a.id=c.item_id " +
            "where a.`status`=1")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "price",column = "price"),
            @Result(property = "title",column = "title"),
            @Result(property = "sell_point",column = "sell_point"),
            @Result(property = "image",column = "image"),
            @Result(property = "category_name",column = "category_name"),
            @Result(property = "item_desc",column = "item_desc")
    })
    List<SolrItem> getSolrItemList();


    @Select("SELECT a.id,a.title,a.price,a.sell_point,a.image,b.`name` category_name,c.item_desc " +
            "FROM tb_item a LEFT JOIN tb_item_cat b ON a.cid=b.id LEFT JOIN tb_item_desc c ON a.id=c.item_id " +
            "where a.`status`=1 and a.id=#{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "price",column = "price"),
            @Result(property = "title",column = "title"),
            @Result(property = "sell_point",column = "sell_point"),
            @Result(property = "image",column = "image"),
            @Result(property = "category_name",column = "category_name"),
            @Result(property = "item_desc",column = "item_desc")
    })
    SolrItem getSolrItemById(long id);

}
