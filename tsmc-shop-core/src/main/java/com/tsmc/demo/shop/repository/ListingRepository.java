package com.tsmc.demo.shop.repository;

import java.util.List;

import com.tsmc.demo.shop.entity.Listing;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author shihpeng
 * @date 2019/12/7
 */
@Mapper
public interface ListingRepository {

    @Insert({
        "INSERT INTO listing (id, creation_time, title, description, price, category, username)",
        "VALUES (null, CURRENT_TIMESTAMP(), #{title}, #{description}, #{price}, #{category}, #{username})"
    })
    @Options(useGeneratedKeys=true)
    int insert(Listing listing);

    @Select("SELECT * FROM listing WHERE id = #{id} AND is_delete=0")
    Listing findById(long id);

    @Select({
        "SELECT * FROM listing",
        "WHERE category = #{category} AND is_delete=0",
        "ORDER BY ${sortBy} ${orderBy}"
    })
    List<Listing> listByCategory(@Param("category") String category,
                                 @Param("sortBy") String sortBy,
                                 @Param("orderBy") String orderBy);

    @Select({
        "SELECT category FROM listing WHERE is_delete=0",
        "GROUP BY category",
        "ORDER BY COUNT(category) DESC LIMIT 1"
    })
    String getCategoryWithMaxCount();

    @Update({
        "UPDATE listing",
        "SET is_delete=1",
        "WHERE id = #{id}"
    })
    int deleteById(long id);
}

