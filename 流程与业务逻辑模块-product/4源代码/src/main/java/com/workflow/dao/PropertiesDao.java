package com.workflow.dao;

import com.workflow.model.Properties;
import com.workflow.model.PropertiesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PropertiesDao {
    long countByExample(PropertiesExample example);

    int deleteByExample(PropertiesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Properties record);

    int insertSelective(Properties record);

    List<Properties> selectByExample(PropertiesExample example);

    Properties selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Properties record, @Param("example") PropertiesExample example);

    int updateByExample(@Param("record") Properties record, @Param("example") PropertiesExample example);

    int updateByPrimaryKeySelective(Properties record);

    int updateByPrimaryKey(Properties record);
}