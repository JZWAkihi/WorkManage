package com.jiang.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.server.pojo.Admin;
import com.jiang.server.pojo.Menu;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    /***
     * 通过用户Id查询菜单列表
     * @param id
     * @return
     */
    List<Menu> getMenuByAdminId(Integer id);

    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);

}
