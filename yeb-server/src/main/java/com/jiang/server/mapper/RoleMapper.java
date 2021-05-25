package com.jiang.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.server.pojo.Role;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
@Component
public interface RoleMapper extends BaseMapper<Role> {

    /**
     *
     * 根据用户ID查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
