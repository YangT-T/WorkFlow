package com.workflow.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MyTaskEntity {
    String Id;
    String name;
    String owner;
    String assignee;
    String description;
    Date createTime;
    Date dueDate;
}
