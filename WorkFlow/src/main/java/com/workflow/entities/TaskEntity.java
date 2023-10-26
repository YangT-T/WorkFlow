package com.workflow.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TaskEntity {
    String Id;
    String name;
    String owner;
    String assignee;
    Date createTime;
}
