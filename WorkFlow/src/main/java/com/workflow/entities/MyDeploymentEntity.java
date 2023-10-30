package com.workflow.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class MyDeploymentEntity {
    String Id;
    String name;
    String description;
    Boolean suspended;
}
