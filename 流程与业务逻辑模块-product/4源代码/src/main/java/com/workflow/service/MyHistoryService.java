package com.workflow.service;

import com.workflow.entities.MyTaskHistoryEntity;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyHistoryService {
    HistoryService historyService;
    public List<MyTaskHistoryEntity> getHistory(String userId){
        historyService=ProcessEngines.getDefaultProcessEngine().getHistoryService();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().taskAssignee(userId).list();
        List<MyTaskHistoryEntity> historyList=new ArrayList<>();
        list.forEach(e->{
            String description=e.getProcessDefinitionId().substring(0,e.getProcessDefinitionId().indexOf(":"))+": "+e.getActivityId();
            historyList.add(new MyTaskHistoryEntity(e.getId(),e.getStartTime(),e.getEndTime(),e.getAssignee(),description,e.getActivityName()));
        });
        return historyList;
    }
}
