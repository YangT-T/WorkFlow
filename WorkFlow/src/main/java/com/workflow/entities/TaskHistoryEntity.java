package com.workflow.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class TaskHistoryEntity {
    String Id;
    Date startTime;
    Date endTime;
    String assignee;
    String activityName ;
}
