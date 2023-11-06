package com.workflow.entities;

import org.activiti.engine.identity.User;

public class MyUserEntity implements User {
    String id;
    String firstName;
    String lastName;

    public MyUserEntity(String id){
        this.id=id;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String s) {
        this.id=s;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String s) {
        this.firstName=s;
    }

    @Override
    public void setLastName(String s) {
        this.lastName=s;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setEmail(String s) {
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setPassword(String s) {

    }

    @Override
    public boolean isPictureSet() {
        return false;
    }
}
