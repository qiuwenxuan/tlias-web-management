package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 **/
@Slf4j // lombok工具包内的生成日志对象标签 相当于代码 private static Logger log = LoggerFactory.getLogger(DeptController.class);
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    //    查询全部部门接口
    //    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping // @RequestMapping的衍生注解，相当于get方式的RequestMapping
    public Result selectAll() {
        log.info("==================== 查询全部部门数据 ====================");
        List<Dept> deptList = deptService.selectAll();
        return Result.success(deptList);
    }

    //    删除部门接口
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("==================== 根据ID删除部门数据id:{}====================", id);
        deptService.deleteById(id);
        return Result.success();
    }

    //    添加部门
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("==================== 新增部门 ====================");
        deptService.add(dept);
        return Result.success();
    }

    // 根据ID查询
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("==================== 根据ID查询部门数据id:{}====================", id);
        List<Dept> dept = deptService.selectById(id);
        return Result.success(dept);
    }

    // 修改部门数据
    // TODO:修改部门数据，报错JSON parse error: Cannot deserialize value of type `com.itheima.pojo.Dept` from Array value
    @PutMapping
    public Result update(@RequestBody List<Dept> depts) {
        log.info("==================== 修改部门数据 ====================", depts.get(0).getId());
//         deptService.update(depts.get(0));
        return Result.success();
    }
}

