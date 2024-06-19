package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    List<Dept> selectAll();

    @Delete("delete from dept where id = #{id} ")
    void deleteById(Integer id);

    void insert(Dept dept);

    List<Dept> selectById(Integer id);

    void update(Dept dept);
}
