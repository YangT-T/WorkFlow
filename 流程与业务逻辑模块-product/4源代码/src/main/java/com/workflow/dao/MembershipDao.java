package com.workflow.dao;

import com.workflow.model.MembershipExample;
import com.workflow.model.MembershipKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MembershipDao {
    long countByExample(MembershipExample example);

    int deleteByExample(MembershipExample example);

    int deleteByPrimaryKey(MembershipKey key);

    int insert(MembershipKey record);

    int insertSelective(MembershipKey record);

    List<MembershipKey> selectByExample(MembershipExample example);

    int updateByExampleSelective(@Param("record") MembershipKey record, @Param("example") MembershipExample example);

    int updateByExample(@Param("record") MembershipKey record, @Param("example") MembershipExample example);
}