package com.jiang.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.server.pojo.Admin;
import com.jiang.server.pojo.Menu;
import com.jiang.server.pojo.Role;
import com.jiang.server.utils.RespBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
public interface IAdminService extends IService<Admin> {

    /***
     * 登录
     * @param username
     * @param password
     * @return
     */
    RespBean login(String username, String password,String code,HttpServletRequest request);

    /**
     * 根据用户获取用户
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);


    /***
     * 根据用户查询角色列表
     * @param adminId
     * @return
     */
    public List<Role> getRoles(Integer adminId);


    List<Admin> getAllAdmins(String keywords);

    RespBean updateAdminRole(Integer adminId, Integer[] rids);

}
