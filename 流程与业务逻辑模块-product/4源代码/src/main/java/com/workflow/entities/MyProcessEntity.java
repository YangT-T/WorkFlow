package com.workflow.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MyProcessEntity {
    String processId;
    String starter;
    String currentAssignee;
    String state;
    Date startTime;
}
