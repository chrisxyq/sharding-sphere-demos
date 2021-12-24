package com.example.springbootshardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootshardingsphere.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
