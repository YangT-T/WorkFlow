package com.workflow.dao;

import com.workflow.model.Processinfo;
import com.workflow.model.ProcessinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessinfoDao {
    long countByExample(ProcessinfoExample example);

    int deleteByExample(ProcessinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Processinfo record);

    int insertSelective(Processinfo record);

    List<Processinfo> selectByExample(ProcessinfoExample example);

    Processinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Processinfo record, @Param("example") ProcessinfoExample example);

    int updateByExample(@Param("record") Processinfo record, @Param("example") ProcessinfoExample example);

    int updateByPrimaryKeySelective(Processinfo record);

    int updateByPrimaryKey(Processinfo record);
}