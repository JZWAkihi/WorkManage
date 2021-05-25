package com.jiang.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.server.mapper.MenuRoleMapper;
import com.jiang.server.pojo.MenuRole;
import com.jiang.server.service.IMenuRoleService;
import com.jiang.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;


    /***
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateMenusRoles(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if(null == mids || 0 == mids.length){
            return RespBean.Success("更新成功");
        }

        Integer result = menuRoleMapper.insertRecord(rid,mids);

        if(result == mids.length){
            return RespBean.Success("更新成功");
        }

        return RespBean.error("更新失败");
    }
}















