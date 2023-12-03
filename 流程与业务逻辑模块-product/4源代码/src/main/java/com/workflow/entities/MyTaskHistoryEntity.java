package com.workflow.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MyTaskHistoryEntity {
    String Id;
    Date startTime;
    Date endTime;
    String assignee;
    String description;
    String activityName;
}
