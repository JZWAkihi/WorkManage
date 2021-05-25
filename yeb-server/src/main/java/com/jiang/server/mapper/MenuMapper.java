package com.jiang.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.server.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuByAdminId(Integer id);

    List<Menu> getMenusByRoles();

    List<Menu> getAllMenus();
}
