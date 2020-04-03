package com.xust.mall.mapper;

import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.MallUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MallUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(MallUser mallUser);

    int insertSelective(MallUser mallUser);

    MallUser selectByPrimaryKey(Long userId);

    MallUser selectByLoginName(String loginName);

    MallUser selectByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password") String password);

    int updateByPrimarykey(MallUser mallUser);

    int updateByPrimaryKeySelective(MallUser mallUser);

    List<MallUser> selectMallUsers(PageUtil pageUtil);

    int getTotalMallUsers(PageUtil pageUtil);

    int lockUserBatch(@Param("ids") Integer[] ids,@Param("lockStatus") int lockStatus);
}
