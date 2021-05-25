package com.jiang.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.server.pojo.MenuRole;
import com.jiang.server.utils.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
public interface IMenuRoleService extends IService<MenuRole> {

    RespBean updateMenusRoles(Integer rid, Integer[] mids);
}
