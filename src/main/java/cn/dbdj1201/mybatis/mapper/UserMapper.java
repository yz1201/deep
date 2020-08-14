package cn.dbdj1201.mybatis.mapper;

import cn.dbdj1201.mybatis.entity.User;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * @Author: dbdj1201
 * @Date: 2020-08-14 19:22
 */
public interface UserMapper {

//    @Insert("select * from tb_user")
    List<User> findAll();
}
