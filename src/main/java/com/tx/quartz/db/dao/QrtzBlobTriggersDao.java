package com.tx.quartz.db.dao;


import java.util.List;

import com.tx.quartz.domain.QrtzBlobTriggersEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QrtzBlobTriggersDao {
    int deleteByPrimaryKey(@Param("schedName") String schedName, @Param("triggerName") String triggerName, @Param("triggerGroup") String triggerGroup);

    int insert(QrtzBlobTriggersEntity record);

    List<QrtzBlobTriggersEntity> selectAll();

    int updateByPrimaryKey(QrtzBlobTriggersEntity record);
}