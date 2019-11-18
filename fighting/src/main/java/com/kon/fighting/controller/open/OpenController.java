package com.kon.fighting.controller.open;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.entity.BlogMsgBoard;
import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.service.BlogArticleService;
import com.kon.fighting.service.BlogMsgBoardService;
import com.kon.fighting.service.BlogTagService;
import com.kon.fighting.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private BlogMsgBoardService blogMsgBoardService;

    // ------------------------------------文章Start----------------------------------

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
     *
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

    // ------------------------------------文章End------------------------------------

    // ----------------------------------留言板Start----------------------------------

    /**
     * 留言
     *
     * @return
     */
    @PostMapping("/leaveMsg")
    public Result leaveMsg(HttpServletRequest request, @RequestBody BlogMsgBoard blogMsgBoard) {
        return Result.crud(blogMsgBoardService.leaveMsg(request, blogMsgBoard));
    }

    /**
     * 获取留言分页数据
     *
     * @return
     */
    @GetMapping("/msgPage")
    public Result msgPage(@RequestParam Map<String, Object> map) {
        return Result.okData(blogMsgBoardService.findMsgPage(map));
    }

    /**
     * 回复
     *
     * @return
     */
    @PostMapping("/replyMsg")
    public Result replyMsg(@RequestBody BlogMsgBoard blogMsgBoard) {
        return Result.crud(blogMsgBoardService.replyMsg(blogMsgBoard));
    }

    // ----------------------------------留言板End------------------------------------

}
