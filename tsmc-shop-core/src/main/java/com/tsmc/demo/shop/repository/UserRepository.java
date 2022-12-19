package com.tsmc.demo.shop.repository;

import com.tsmc.demo.shop.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author shihpeng
 * @date 2019/12/7
 */
@Mapper
public interface UserRepository {

    @Insert("INSERT INTO user(id, creation_time, name) VALUES (null, CURRENT_TIMESTAMP(), #{name})")
    @Options(useGeneratedKeys=true)
    int insert(User user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(long id);

    @Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(String name);
}

