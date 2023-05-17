package com.eden.linebot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineBotService {
    @Autowired
    LineBotRepository lineBotRepository;
    public void updateNewUser(model model){
        lineBotRepository.insertNewUser(model);
    }

    public void updateHistoryMsg(String user_id , String newMsg){
        lineBotRepository.updateHistoryMsg(user_id , newMsg);
    }

    public String selectUserMsg(model model){
        return lineBotRepository.selectUserId(model);
    }

    public String selectHistoryMsg(model model){
        return lineBotRepository.selectHistoryMsg(model);
    }

    public void clearAllUserHistoryMsg(){
        lineBotRepository.clearHistoryMsg();
    }




}
