package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    //    @Select("select * from emp")
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);


    void delete(List<Integer> ids);

    void insert(Emp emp);

    Emp selectById(Integer id);

    void update(Emp emp);

    Emp getByUsernameAndPassword(Emp emp);
}
