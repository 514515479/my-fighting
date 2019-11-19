package com.kon.fighting.controller.blog;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.entity.BlogMsgBoard;
import com.kon.fighting.entity.BlogTag;
import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.service.BlogMsgBoardService;
import com.kon.fighting.utils.FightingUtils;
import com.kon.fighting.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description 留言板管理控制器
 * @Author LK
 * @Date 2019/11/19 9:32
 * @Version V1.0
 **/
@RestController
@RequestMapping("/blog/message")
public class BlogMessageController {

    @Autowired
    private BlogMsgBoardService blogMsgBoardService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping
    public Result list(@RequestParam Map<String, Object> map) {
        return Result.okData(blogMsgBoardService.findMsgPage(map));
    }

    /**
     * 新增
     *
     * @param entity 实体
     * @return
     */
    @PostMapping
    public Result save(HttpServletRequest request, @RequestBody BlogMsgBoard entity) {
        return Result.crud(blogMsgBoardService.leaveMsg(request, entity));
    }

    /**
     * 修改
     *
     * @param entity 实体
     * @return
     */
    @PutMapping
    public Result update(@RequestBody BlogMsgBoard entity) {
        return Result.crud(blogMsgBoardService.updateSelective(entity));
    }

    /**
     * 删除（伪删）
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Long> ids) {
        int deleteNum = blogMsgBoardService.deleteUseByIdList(ids);
        // 删除回复
        BlogMsgBoard deleteByParentId = new BlogMsgBoard();
        deleteByParentId.setDelFlag(true);
        Example example = new Example(BlogMsgBoard.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("parentId", ids);
        blogMsgBoardService.updateByExampleSelective(deleteByParentId, example);
        return Result.crud(deleteNum);
    }

}
