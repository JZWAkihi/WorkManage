package com.jiang.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.server.pojo.AdminRole;
import com.jiang.server.utils.RespBean;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
@Component
public interface AdminRoleMapper extends BaseMapper<AdminRole> {


    Integer addAdminRole(Integer adminId, Integer[] rids);

}
