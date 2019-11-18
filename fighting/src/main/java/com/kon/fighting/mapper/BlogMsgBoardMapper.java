package com.kon.fighting.mapper;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.entity.BlogMsgBoard;

import java.util.List;
import java.util.Map;

/**
 * @Description 留言板Mapper
 * @Author LK
 * @Date 2019/11/18 8:55
 * @Version V1.0
 **/
public interface BlogMsgBoardMapper extends BaseMapper<BlogMsgBoard> {

    List<BlogMsgBoard> selectBlogMsgBoardPageByMap(Map<String, Object> map);

    List<BlogMsgBoard> selectReplyMsgByParentId(Long parentId);

}