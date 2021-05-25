package com.jiang.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.server.pojo.MenuRole;
import org.springframework.data.repository.query.Param;
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
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /***
     * 批量更新
     * @param rid
     * @param mids
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
