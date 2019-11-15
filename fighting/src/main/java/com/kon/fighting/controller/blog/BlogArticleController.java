package com.kon.fighting.controller.blog;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.entity.BlogArticle;
import com.kon.fighting.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author LK
 * @Date 2019/11/8 14:49
 * @Version V1.0
 **/
@RestController
@RequestMapping("/blog/article")
public class BlogArticleController {

    @Autowired
    private BlogArticleService blogArticleService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping
    public Result list(@RequestParam Map<String, Object> map) {
        return Result.okData(blogArticleService.findBlogArticlePageByMap(map));
    }

    /**
     * 新增
     *
     * @param entity 实体
     * @return
     */
    @PostMapping
    public Result save(@RequestBody BlogArticle entity) {
        return Result.crud(blogArticleService.saveBlogArticleAndBlogArticleTag(entity));
    }

    /**
     * 修改
     *
     * @param entity 实体
     * @return
     */
    @PutMapping
    public Result update(@RequestBody BlogArticle entity) {
        return Result.crud(blogArticleService.updateBlogArticleAndBlogArticleTag(entity));
    }

    /**
     * 删除文章，因为是伪删，所以没有删除文章与文章标签关系
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Long> ids) {
        return Result.crud(blogArticleService.deleteUseByIdList(ids));
    }

}
