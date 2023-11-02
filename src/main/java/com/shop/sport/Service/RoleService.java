package com.shop.sport.Service;

import com.shop.sport.Entity.Role;
import com.shop.sport.Repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository iRoleRepository;

    public Boolean isAdmin(Role role) {
       if(role.getName().equalsIgnoreCase("ADMIN"))
           return true;
       return false;
    }

    public Role getRoleById(long id) {
        try {
            return iRoleRepository.findById(id).get();


        }catch (Exception e){
            return null;
        }
    }
}
