package com.kon.fighting.controller.blog;

import com.kon.fighting.common.dto.Page;
import com.kon.fighting.common.dto.Result;
import com.kon.fighting.entity.BlogTag;
import com.kon.fighting.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author LK
 * @Date 2019/11/8 14:49
 * @Version V1.0
 **/
@RestController
@RequestMapping("/blog/tag")
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping
    public Result list(@RequestParam Map<String, Object> map,
                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return Result.okData(blogTagService.findPageByMap(pageNum, pageSize, map));
    }

    /**
     * 新增
     *
     * @param entity 实体
     * @return
     */
    @PostMapping
    public Result save(@RequestBody BlogTag entity) {
        return Result.crud(blogTagService.saveSelective(entity));
    }

    /**
     * 修改
     *
     * @param entity 实体
     * @return
     */
    @PutMapping
    public Result update(@RequestBody BlogTag entity) {
        return Result.crud(blogTagService.updateSelective(entity));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Long> ids) {
        return Result.crud(blogTagService.deleteByIdList(ids));
    }

    /**
     * 获取所有标签
     *
     * @return
     */
    @GetMapping("/getAllTag")
    public Result getAllTag() {
        return Result.okData(blogTagService.getAll());
    }

}
