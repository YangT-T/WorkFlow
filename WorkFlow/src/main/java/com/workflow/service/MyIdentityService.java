package com.workflow.service;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
//import org.activiti5.engine.impl.persistence.entity.GroupEntity;
//import org.activiti5.engine.impl.persistence.entity.UserEntity;

public class MyIdentityService {
    public void createRelationship(String userId,String groupId){
//        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        IdentityService identityService = defaultProcessEngine.getIdentityService();
//        UserQuery userQuery = identityService.createUserQuery().userId(userId);
//        System.out.println(userQuery.count());
//        GroupQuery groupQuery = identityService.createGroupQuery().groupId(groupId);
//        System.out.println(groupQuery.count());
//
//        identityService.saveUser((User) new UserEntity(userId));
//        identityService.saveGroup((Group) new GroupEntity(groupId));
//        identityService.createMembership(userId,groupId);
//
//        userQuery = identityService.createUserQuery().userId(userId);
//        System.out.println(userQuery.count());
//        groupQuery = identityService.createGroupQuery().groupId(groupId);
//        System.out.println(groupQuery.count());
    }
}
