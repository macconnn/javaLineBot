package com.eden.linebot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LineBotRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertNewUser(model model){
        System.out.println("EXCUTE INSERT MEMBER");
        String sql = "INSERT INTO usermsg(user_id, msg_history) " + "VALUES (? , ?)";
        jdbcTemplate.update(sql , model.getUser_id() , model.getMsg_history());
    }

    public void updateHistoryMsg(String  user_id , String newMsg){
        String sql = "update usermsg set msg_history=? where user_id=?";
        jdbcTemplate.update(sql , newMsg  , user_id);
    }

    public void clearHistoryMsg(){
        String sql = "update usermsg set msg_history= ?";
        jdbcTemplate.update(sql , "");
    }

    public String selectUserId(model model){
        String sql = "select user_id from usermsg where user_id = ?";
        return jdbcTemplate.queryForObject(sql , new Object[]{model.getUser_id()} , String.class);
    }

    public String selectHistoryMsg(model model){
        String sql = "select msg_history from usermsg where user_id = ?";
        return jdbcTemplate.queryForObject(sql , new Object[]{model.getUser_id()} , String.class);
    }






}

