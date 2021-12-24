package com.example.springbootshardingsphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootshardingsphere.entity.Course;
import com.example.springbootshardingsphere.entity.Udict;
import com.example.springbootshardingsphere.entity.User;
import com.example.springbootshardingsphere.mapper.CourseMapper;
import com.example.springbootshardingsphere.mapper.UdictMapper;
import com.example.springbootshardingsphere.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShardingSphereApplicationTests {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper  userMapper;
    @Autowired
    private UdictMapper udictMapper;
    @Test
    void contextLoads() {
    }

    /**
     * 测试水平分表：批量插入数据
     */
    @Test
    void test1() {
        for (int i = 0; i < 10; i++) {
            final Course course = new Course();
            course.setCname("java"+i);
            course.setUserId(100L);
            course.setCstatus("Normal"+i);
            courseMapper.insert(course);
        }
    }
    /**
     * 测试水平分表：查找某数据
     */
    @Test
    void test2() {
        final QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid",657220793951846400L);
        final Course course = courseMapper.selectOne(queryWrapper);
        System.out.println(course);
    }
    /**
     * 测试水平分库：插入某数据
     */
    @Test
    void test3() {
        final Course course = new Course();
        course.setCname("javademo");
        //插入edu_db_1库
        course.setUserId(100L);
        course.setCstatus("Normal");
        courseMapper.insert(course);
        final Course course1 = new Course();
        course1.setCname("javademo1");
        //插入edu_db_2库
        course1.setUserId(111L);
        course1.setCstatus("Normal1");
        courseMapper.insert(course1);
    }
    /**
     * 测试水平分库：查询数据
     */
    @Test
    void test4() {
        final QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid",657231018981326849L);
        queryWrapper.eq("user_id",100L);
        final Course course = courseMapper.selectOne(queryWrapper);
        System.out.println(course);
    }
    /**
     * 测试垂直分库:添加
     */
    @Test
    void test5() {
        final User user = new User();
        user.setUsername("lucy");
        user.setUstatus("a");
        userMapper.insert(user);
    }
    /**
     * 测试垂直分库:查询
     */
    @Test
    void test6() {
        final QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",657239864349032449L);
        final Course course = courseMapper.selectOne(queryWrapper);
        System.out.println(course);
    }
    /**
     * 测试公共表:添加
     * 2021-10-19 15:42:34.910  INFO 26352 --- [           main] ShardingSphere-SQL                       : Actual SQL: m1 ::: INSERT INTO t_udict   (ustatus, uvalue, dictid) VALUES (?, ?, ?) ::: [a, lucy, 657246408708980737]
     * 2021-10-19 15:42:34.910  INFO 26352 --- [           main] ShardingSphere-SQL                       : Actual SQL: m2 ::: INSERT INTO t_udict   (ustatus, uvalue, dictid) VALUES (?, ?, ?) ::: [a, lucy, 657246408708980737]
     * 2021-10-19 15:42:34.910  INFO 26352 --- [           main] ShardingSphere-SQL                       : Actual SQL: m0 ::: INSERT INTO t_udict   (ustatus, uvalue, dictid) VALUES (?, ?, ?) ::: [a, lucy, 657246408708980737]
     */
    @Test
    void test7() {
        final Udict udict = new Udict();
        udict.setUstatus("a");
        udict.setUvalue("lucy");
        udictMapper.insert(udict);
    }
    /**
     * 测试公共表:查询
     */
    @Test
    void test8() {
        final QueryWrapper<Udict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dictid",657239864349032449L);
        final Udict udict = udictMapper.selectOne(queryWrapper);
        System.out.println(udict);
    }
}
