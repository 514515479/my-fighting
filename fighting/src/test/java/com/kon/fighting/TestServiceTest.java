package com.kon.fighting;

import com.kon.fighting.common.dto.Page;
import com.kon.fighting.service.TestService;
import org.assertj.core.util.Lists;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description 测试接口测试
 * @Author LK
 * @Date 2019/11/1 10:26
 * @Version V1.0
 **/
@Ignore
public class TestServiceTest extends FightingApplicationTest {

    @Autowired
    private TestService testService;

    @Test
    public void hello() {
        // equals方法的测试
        // 如果@EqualsAndHashCode(callSuper=false),则以下结果为true,出大问题
        // 如果@EqualsAndHashCode(callSuper=true),则以下结果为false,没问题
        com.kon.fighting.entity.Test test1 = new com.kon.fighting.entity.Test();
        test1.setId(1L);
        test1.setName("嘿嘿");
        test1.setBigint(4L);

        com.kon.fighting.entity.Test test2 = new com.kon.fighting.entity.Test();
        test2.setId(2L);
        test2.setName("嘿嘿");
        test2.setBigint(4L);

        System.out.println(test1.equals(test2));
    }

    @Test
    public void save() {
        com.kon.fighting.entity.Test test = new com.kon.fighting.entity.Test();
        test.setName("嘻嘻");
        test.setBigint(2L);
        test.setDate(new Date());
        test.setDatetime(new Date());
        test.setCreateTime(LocalDateTime.now());
        test.setCreateUser(0L);
        int num = testService.save(test);
        System.out.println(num);
    }

    @Test
    public void saveSelective() {
        com.kon.fighting.entity.Test test = new com.kon.fighting.entity.Test();
        test.setName("嘻嘻");
        test.setBigint(2L);
        test.setDate(new Date());
        test.setDatetime(new Date());
        test.setCreateTime(LocalDateTime.now());
        test.setCreateUser(0L);
        int num = testService.saveSelective(test);
        System.out.println(num);
    }

    @Test
    public void saveList() {
        List<com.kon.fighting.entity.Test> testList = new ArrayList<>();

        com.kon.fighting.entity.Test test1 = new com.kon.fighting.entity.Test();
        test1.setName("呵呵");
        test1.setBigint(3L);
        test1.setDate(new Date());
        test1.setDatetime(new Date());
        test1.setCreateTime(LocalDateTime.now());
        test1.setCreateUser(0L);
        testList.add(test1);

        com.kon.fighting.entity.Test test2 = new com.kon.fighting.entity.Test();
        test2.setName("嘿嘿");
        test2.setBigint(4L);
        test2.setDate(new Date());
        test2.setDatetime(new Date());
        test2.setCreateTime(LocalDateTime.now());
        test2.setCreateUser(0L);
        testList.add(test2);

        int num = testService.saveList(testList);
        System.out.println(num);
    }

    @Test
    public void delete() {
        com.kon.fighting.entity.Test test = new com.kon.fighting.entity.Test();
        test.setName("嘿嘿");
        int num = testService.delete(test);
        System.out.println(num);
    }

    @Test
    public void deleteById() {
        int num = testService.deleteById(2L);
        System.out.println(num);
    }

    @Test
    public void deleteByIdList() {
        List<Long> idList = Lists.list(5L, 6L);
        int num = testService.deleteByIdList(idList);
        System.out.println(num);
    }

    @Test
    public void deleteByExample() {
        Example example = new Example(com.kon.fighting.entity.Test.class);
        Example.Criteria criteria = example.createCriteria();
        // 下面两种都可以二选一
            // 这种用的是实体字段名
        criteria.andEqualTo("name", "呵呵");
            // 这种用的是数据库字段名
//        criteria.andCondition("name=", "呵呵");
        int num = testService.deleteByExample(example);
        System.out.println(num);
    }

    @Test
    public void update() {
        com.kon.fighting.entity.Test test = new com.kon.fighting.entity.Test();
        test.setId(7L);
        test.setName("嘻嘻");
        test.setBigint(2L);
        test.setDate(new Date());
        test.setDatetime(new Date());
        test.setCreateTime(LocalDateTime.now());
        test.setCreateUser(0L);
        int num = testService.update(test);
        System.out.println(num);
    }

    @Test
    public void updateSelective() {
        com.kon.fighting.entity.Test test = new com.kon.fighting.entity.Test();
        test.setId(7L);
        test.setName("嘿嘿");
        test.setUpdateTime(LocalDateTime.now());
        test.setUpdateUser(0L);
        int num = testService.updateSelective(test);
        System.out.println(num);
    }

    @Test
    public void updateByExampleSelective() {
        com.kon.fighting.entity.Test test = new com.kon.fighting.entity.Test();
        test.setName("叉叉");
        Example example = new Example(com.kon.fighting.entity.Test.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "嘿嘿");
        int num = testService.updateByExampleSelective(test, example);
        System.out.println(num);
    }

    @Test
    public void getById() {
        com.kon.fighting.entity.Test test = testService.getById(7L);
        System.out.println(test.toString());
    }

    @Test
    public void getAll() {
        List<com.kon.fighting.entity.Test> testList = testService.getAll();
        System.out.println(testList.toString());
    }

    @Test
    public void searchByEntity() {
        com.kon.fighting.entity.Test test = new com.kon.fighting.entity.Test();
        test.setName("嘿嘿");
        List<com.kon.fighting.entity.Test> testList = testService.getByEntity(test);
        System.out.println(testList.toString());
    }

    @Test
    public void getByExample() {
        Example example = new Example(com.kon.fighting.entity.Test.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "嘿嘿");
        List<com.kon.fighting.entity.Test> testList = testService.getByExample(example);
        System.out.println(testList.toString());
    }

    @Test
    public void getPage() {
        com.kon.fighting.entity.Test test = new com.kon.fighting.entity.Test();
        test.setName("嘿嘿");
        Page<com.kon.fighting.entity.Test> testPage = testService.getPage(2, 3, test);
        System.out.println(testPage);
    }

    @Test
    public void getPageByExample() {
        Example example = new Example(com.kon.fighting.entity.Test.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "嘿嘿");
        Page<com.kon.fighting.entity.Test> testPage = testService.getPageByExample(2, 3, example);
        System.out.println(testPage);
    }

    @Test
    public void customSql() {
        com.kon.fighting.entity.Test test = testService.customSql(12L);
        System.out.println(test);
    }


    // -------------------------------------------伪删除测试-------------------------------------------------------------

    @Test
    public void deleteUse() {
        com.kon.fighting.entity.Test test = testService.customSql(12L);
        int num = testService.deleteUse(test);
        System.out.println(num);
    }

    @Test
    public void deleteUseById() {
        int num = testService.deleteUseById(11L);
        System.out.println(num);
    }

    @Test
    public void deleteUseByIdList() {
        List<Long> ids = Arrays.asList(7L, 8L, 9L);
        int num = testService.deleteUseByIdList(ids);
        System.out.println(num);
    }

    @Test
    public void deleteUseByExample() {
        Example example = new Example(com.kon.fighting.entity.Test.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "叉叉");
        int num = testService.deleteUseByExample(example);
        System.out.println(num);
    }

}
