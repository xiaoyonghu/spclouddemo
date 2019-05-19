package net.gaotao.productservice.Dao;

import net.gaotao.productservice.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
//@Component
@Repository
public interface ProductDao {



    @Select("SELECT * FROM product")
//    @Results({
//       @Result(column = "create_time",property = "createTime") })
     List<Product> listAll();


   @Select("SELECT * FROM product WHERE id = #{id}")
//    @Results({
//  	    @Result(column = "create_time",property = "createTime") })
   Product findById(int id);
}

//@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
//推荐使用#{}取值，不要用${},因为存在注入的风险
//@Insert("INSERT INTO user(name,phone,create_time,age) VALUES(#{name}, #{phone}, #{createTime},#{age})")
//@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
//int insert(User user);

