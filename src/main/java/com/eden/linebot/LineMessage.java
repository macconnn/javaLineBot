package com.eden.linebot;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@LineMessageHandler
@RestController
public class LineMessage {

    @Autowired
    HttpClient httpClient;

    @Autowired
    JsonOperation jsonOperation;

    @Autowired
    FileOperation fileOperation;

    @Autowired
    model model;

    @Autowired
    LineBotService lineBotService;

    private final String notMessageNotification = "The service only allowed text message not include these below \n\n" +
            "【 Sticker,Image,File,Location,Audio】\n\n" +
            "If you still don't how to use it well \n" +
            "Please enter Help or help :)";
    private final String helpString = "Help , help ↓ \n" +
            "【display help information】 \n\n" +
            "Normal message ↓ \n" +
            "【conversation to ChatGPT with any languages】";

    private final String chatGptUrl = "https://api.openai.com/v1/completions";


    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String chatGptKey = fileOperation.getIniInfo("chatGptKey");
        if (event.getMessage().getText().equals("Help") || event.getMessage().getText().equals("help")) {
            fileOperation.writeLog("Help", "User called help");
            return new TextMessage(helpString);
        }else if(event.getMessage().getText().equals("-admin cmd clear")){
            lineBotService.clearAllUserHistoryMsg();
            return new TextMessage("The all user message history has been clear :)");
        }else {
            model.setUser_id(event.getSource().getUserId());
            model.setMsg_history(event.getMessage().getText());
            String select = null;
            try {
                //check if user_id doesn't exist
                select = lineBotService.selectUserMsg(model);
                String historyStr = lineBotService.selectHistoryMsg(model);
                String newMsg = historyStr + event.getMessage().getText();

                String jsonStr = httpClient.doChatGptPost(newMsg, chatGptUrl, chatGptKey);
                String result = jsonOperation.parseGptJson(jsonStr);
                newMsg = newMsg + "\n" + result + "\n";
                lineBotService.updateHistoryMsg(select , newMsg);
                fileOperation.writeLog("User", event.getMessage().getText());
                fileOperation.writeLog("ChatGPT", result);
                return new TextMessage(result);
            }catch (Exception e){
                try {
                    //User first user, update user info to database
                    lineBotService.updateNewUser(model);
                    String jsonStr = httpClient.doChatGptPost(event.getMessage().getText(), chatGptUrl, chatGptKey);
                    String result = jsonOperation.parseGptJson(jsonStr);
                    String newMsg = event.getMessage().getText() + "\n" + result + "\n";
                    lineBotService.updateHistoryMsg(event.getSource().getUserId() , newMsg);
                    fileOperation.writeLog("User", event.getMessage().getText());
                    fileOperation.writeLog("ChatGPT", result);
                    return new TextMessage(result);
                }catch (Exception e1){
                    fileOperation.writeLog("Error", "ChatGPT token is not enough");
                    fileOperation.writeLog("Error", e.getMessage());
                    e.printStackTrace();
                    return new TextMessage("ChatGPT token is not enough");
                }
            }
        }


    }

    @EventMapping
    public TextMessage handleStickerMessageEvent(MessageEvent<StickerMessageContent> event){
        fileOperation.writeLog("Error" , "User send sticker");
        return new TextMessage(notMessageNotification);
    }

    @EventMapping
    public TextMessage handleImageMessageEvent(MessageEvent<ImageMessageContent> event){
        fileOperation.writeLog("Error" , "User send image");
        return new TextMessage(notMessageNotification);
    }

    @EventMapping
    public TextMessage handleVideoMessageEvent(MessageEvent<VideoMessageContent> event){
        fileOperation.writeLog("Error" , "User send video");
        return new TextMessage(notMessageNotification);
    }

    @EventMapping
    public TextMessage handleLocationMessageEvent(MessageEvent<LocationMessageContent> event){
        fileOperation.writeLog("Error" , "User send location");
        return new TextMessage(notMessageNotification);
    }

    @EventMapping
    public TextMessage handleAudioMessageEvent(MessageEvent<AudioMessageContent> event){
        fileOperation.writeLog("Error" , "User send audio");
        return new TextMessage(notMessageNotification);
    }

    @EventMapping
    public TextMessage handleFileMessageEvent(MessageEvent<FileMessageContent> event){
        fileOperation.writeLog("Error" , "User send file");
        return new TextMessage(notMessageNotification);
    }




}
