package com.qjq.service;

import com.qjq.po.Remind;
import com.qjq.vo.RemindVo;

import java.util.List;

public interface RemindService {
    void addRemind(Remind remind);
    List<RemindVo> selectRemindVoByUserID(String userId);
    List<RemindVo> selectRemindVoByNewsID(List<RemindVo> remindVos,String newsID);
    List<RemindVo> selectRemindVoByTypeId(Integer typeId);
    void changeRemindStatusById(String id);
}
