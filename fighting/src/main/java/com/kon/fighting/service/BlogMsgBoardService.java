package com.kon.fighting.service;

import com.kon.fighting.common.constant.Constant;
import com.kon.fighting.common.dto.Page;
import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.common.persistence.BaseServiceImpl;
import com.kon.fighting.entity.BlogMsgBoard;
import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.mapper.BlogMsgBoardMapper;
import com.kon.fighting.utils.FightingUtils;
import com.kon.fighting.utils.IpUtils;
import com.kon.fighting.utils.RedisUtils;
import com.kon.fighting.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description 留言板接口
 * @Author LK
 * @Date 2019/11/18 8:55
 * @Version V1.0
 **/
@Service
public class BlogMsgBoardService extends BaseServiceImpl<BlogMsgBoard, Long> {

    @Autowired
    private BlogMsgBoardMapper blogMsgBoardMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected BaseMapper<BlogMsgBoard> getMapper() {
        return this.blogMsgBoardMapper;
    }

    /**
     * 留言
     *
     * @return
     */
    public boolean leaveMsg(HttpServletRequest request, @RequestBody BlogMsgBoard blogMsgBoard) {
        SuperUser loginUser = SecurityUtils.getUser();
        if (loginUser == null) {
            // 游客留言
            String ipAddress = IpUtils.getIpAddress(request);
            blogMsgBoard.setName(ipAddress);
        } else {
            // 用户留言
            blogMsgBoard.setUserId(loginUser.getId());
        }
        // 备注作为楼层号
        blogMsgBoard.setRemark(getFloorIndex());
        if (StringUtils.isEmpty(blogMsgBoard.getColor())) {
            // 随机节点颜色
            blogMsgBoard.setColor(FightingUtils.getRandColorCode());
        }
        // 设置父节点Id
        blogMsgBoard.setParentId(0L);
        return this.saveSelective(blogMsgBoard) > 0;
    }

    /**
     * 获取下一个楼层号
     *
     * @return
     */
    private String getFloorIndex() {
        long incr = 1;
        Object o = redisUtils.get(Constant.BLOG_MSG_BOARD_INDEX);
        if (o == null) {
            Integer maxFloorIndex = blogMsgBoardMapper.getMaxFloorIndex();
            if (maxFloorIndex != null) {
                incr = redisUtils.incr(Constant.BLOG_MSG_BOARD_INDEX, maxFloorIndex + 1);
            } else {
                incr = redisUtils.incr(Constant.BLOG_MSG_BOARD_INDEX, 1L);
            }
        } else {
            incr = redisUtils.incr(Constant.BLOG_MSG_BOARD_INDEX, 1L);
        }
        return String.valueOf(incr);
    }

    /**
     * 获取留言分页数据
     *
     * @return
     */
    public Page<BlogMsgBoard> findMsgPage(Map<String, Object> map) {
        List<BlogMsgBoard> msgList = blogMsgBoardMapper.selectBlogMsgBoardPageByMap(map);
        return Page.pageData(msgList);
    }

    /**
     * 回复
     *
     * @return
     */
    public boolean replyMsg(@RequestBody BlogMsgBoard blogMsgBoard) {
        SuperUser loginUser = SecurityUtils.getUser();
        blogMsgBoard.setUserId(loginUser.getId());
        return this.saveSelective(blogMsgBoard) > 0;
    }

}
