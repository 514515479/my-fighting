package com.kon.fighting.controller.open;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.service.BlogArticleService;
import com.kon.fighting.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description TODO
 * @Author LK
 * @Date 2019/11/10 13:17
 * @Version V1.0
 **/
@RestController
@RequestMapping("/open")
public class OpenController {

    @Autowired
    private BlogArticleService blogArticleService;

    @Autowired
    private BlogTagService blogTagService;

    /**
     * 获取发布的文章分页列表
     *
     * @param map
     * @return
     */
    @GetMapping("/getArticleList")
    public Result getArticleList(@RequestParam Map<String, Object> map) {
        return Result.okData(blogArticleService.findBlogArticlePageByMapOpen(map));
    }

    /**
     * 获取发布的文章
     *
     * @param id
     * @return
     */
    @GetMapping("/getArticle")
    public Result getArticle(Long id) {
        return Result.okData(blogArticleService.findBlogArticleById(id));
    }

    /**
     * 查看文章
     * @param id
     * @return
     */
    @GetMapping("/viewArticle")
    public Result viewArticle(Long id) {
        blogArticleService.viewArticleAddOne(id);
        return Result.okData(null);
    }

    /**
     * 获取所有标签
     *
     * @return
     */
    @GetMapping("/getAllTag")
    public Result getAllTag() {
        return Result.okData(blogTagService.findAllTag());
    }

}
