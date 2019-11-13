package com.qjq.dao;

import com.qjq.po.Remind;
import com.qjq.vo.RemindVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RemindMapper {
    int addRemind(Remind record);
    List<RemindVo> selectRemindVoByUserID(@Param("userId") String userId);
    void deleteRemindByNewsId(@Param("newsId")String newsId);
    List<RemindVo> selectRemindVoByTypeId(@Param("typeId")int typeId);
    Remind selectRemindById(@Param("id")String id);
    void updateRemind(@Param("remind")Remind remind);
}