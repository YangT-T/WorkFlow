package com.workflow.service;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;

import java.sql.SQLIntegrityConstraintViolationException;
//import org.activiti5.engine.impl.persistence.entity.GroupEntity;
//import org.activiti5.engine.impl.persistence.entity.UserEntity;

public class MyIdentityService {
    public void createRelationship(String userId,String groupId){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        IdentityService identityService = defaultProcessEngine.getIdentityService();
        UserQuery userQuery = identityService.createUserQuery().userId(userId);
        GroupQuery groupQuery = identityService.createGroupQuery().groupId(groupId);

        // 若user不存在新建用户
        if(userQuery.count()==0){
            UserEntityImpl user = new UserEntityImpl();
            user.setId(userId);
            user.setRevision(0);
            identityService.saveUser(user);
        }

        // 若Group不存在新建组
        if(groupQuery.count()==0){
            GroupEntityImpl group=new GroupEntityImpl();
            group.setId(groupId);
            group.setRevision(0);
            identityService.saveGroup(group);
        }

        // 创建关系
        identityService.deleteMembership(userId,groupId);
        identityService.createMembership(userId,groupId);
        System.out.println("successfully create relationship "+userId+"---"+groupId);
    }
}
