package com.xust.service;

import com.xust.mapper.HrMapper;
import com.xust.mapper.HrRoleMapper;
import com.xust.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HrService {
    @Autowired
    HrMapper hrMapper;

    @Autowired
    HrRoleMapper hrRoleMapper;

    public List<Hr> getAllHrs(String keywords){
        return hrMapper.getAllHrs(new Hr().getId(),keywords);
    }


    public Hr loadUserByUsername(String username) throws Exception {
        Hr hr = hrMapper.loadUserByUsername(username);
        if(hr == null){
            throw new Exception("用户名不存在！");
        }
        hr.setRoles(hrMapper.getHrRoleById(hr.getId()));
        return hr;
    }

    public Integer updateHr(Hr hr){
        return  hrMapper.updateByIdSelective(hr);
    }

    @Transactional
    public boolean updateHrRole(Integer hrid,Integer[] rids){
        hrRoleMapper.deleteByHrid(hrid);
        return hrRoleMapper.addRole(hrid,rids) == rids.length;
    }

    public Integer deleteHrById(Integer id){
        return hrMapper.deleteById(id);
    }

    public List<Hr> getAllHrsExceptCurrentHr(){
        return hrMapper.getAllHrsExceptCurrentHr(new Hr().getId());
    }

    public boolean updateHrPassword(String oldPassword, String newPassword,Integer hrid){
        Hr hr = hrMapper.selectById(hrid);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if(encoder.matches(oldPassword,hr.getPassword())){
//            String encoderPassword = encoder.encode(newPassword);
//            Integer result = hrMapper.updatePassword(hrid,encoderPassword);
//            if(result == 1){
//                return true;
//            }
//        }
        return false;
    }

    public Integer updateUserFace(String uri,Integer id){
        return hrMapper.updateUserface(uri,id);
    }

}
