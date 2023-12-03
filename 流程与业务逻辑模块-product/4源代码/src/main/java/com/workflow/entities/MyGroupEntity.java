package com.workflow.entities;

import org.activiti.engine.identity.Group;

public class MyGroupEntity implements Group {
    public String Id;

    public MyGroupEntity(String Id){
        this.Id=Id;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String s) {
        this.Id=s;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void setType(String s) {

    }
}
