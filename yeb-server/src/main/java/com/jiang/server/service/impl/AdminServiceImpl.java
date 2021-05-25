package com.jiang.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.server.mapper.AdminMapper;
import com.jiang.server.mapper.AdminRoleMapper;
import com.jiang.server.mapper.RoleMapper;
import com.jiang.server.pojo.Admin;
import com.jiang.server.pojo.AdminRole;
import com.jiang.server.pojo.Menu;
import com.jiang.server.pojo.Role;
import com.jiang.server.service.IAdminService;
import com.jiang.server.utils.AdminUtils;
import com.jiang.server.utils.JWTUtils;
import com.jiang.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;



    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private RoleMapper roleMapper;


    //Spring Security 推荐的默认密码编码器
    @Autowired
    private PasswordEncoder passwordEncoder;

    //jwt工具类
    @Autowired
    private JWTUtils jwtUtils;


    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {

        String captcha = (String)request.getSession().getAttribute("captcha");
        if(StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码错误，请重新输入");
        }

        //得到用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //用户信息为空，且密码不正确  返回错误
        // passwordEncoder 默认的密码校验器  passwordEncoder.matches() 比较密码
        if(null == userDetails || !passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码错误");
        }

        //用户是否被禁用
        if(!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员");
        }

        //更新security登录用户对象


        /***
         * 以UsernamePasswordAuthenticationToken实现的带用户名和密码以及权限的Authentication
         */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        //使用JWT工具类  将用户信息作为参数  生成token
        String token = jwtUtils.generateToken(userDetails);

        //将token和tokenHead传入前端
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenHead);

        return RespBean.Success("登录成功",map);
    }


    /**
     * getAdminByUserName
     * 根据用户名 得到用户信息
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {

        return adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("username",username)
                .eq("enabled",true));
    }


    /***
     * 根据用户查询角色列表
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }


    /**
     * 获取全部操作员
     * @param keywords
     * @return
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(),keywords);
    }


    /***
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {

        //删除全部
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        //
        Integer result = adminRoleMapper.addAdminRole(adminId,rids);

        if(result == rids.length){
            return RespBean.Success("更新成功");
        }

        return RespBean.error("更新失败");

    }
}

