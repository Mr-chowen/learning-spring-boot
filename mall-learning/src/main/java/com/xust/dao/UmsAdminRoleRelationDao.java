package com.xust.dao;

import com.xust.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UmsAdminRoleRelationDao {
     List<UmsPermission> getPermissionList(Long adminId);
}
