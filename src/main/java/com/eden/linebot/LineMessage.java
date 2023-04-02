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

    private final String notMessageNotification = "The service only allowed text message not include these below \n\n" +
                                                  "【 Sticker,Image,File,Location,Audio】\n\n" +
                                                  "If you still don't how to use it well \n" +
                                                  "Please enter Help or help :)";
    private final String helpString = "Help , help ↓ \n" +
                                      "【display help information】 \n\n" +
                                      "Normal message ↓ \n" +
                                      "【conversation to ChatGPT with any languages】";
    private final String chatGptKey = "Bearer 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";
    private final String chatGptUrl = "https://api.openai.com/v1/completions";

    @Autowired
    HttpClient httpClient;

    @Autowired
    JsonOperation jsonOperation;

    @Autowired
    Log log;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        if(event.getMessage().getText().equals("Help") || event.getMessage().getText().equals("help")){
            log.writeLog("Help" , "User called help");
            return new TextMessage(helpString);
        }else {
            try {
                String jsonStr = httpClient.doChatGptPost(event.getMessage().getText() , chatGptUrl , chatGptKey);
                String result = jsonOperation.parseGptJson(jsonStr);
                log.writeLog("User" , event.getMessage().getText());
                log.writeLog("ChatGPT" , result);
                return new TextMessage(result);
            }catch (Exception e){
                log.writeLog("Error" , "ChatGPT token is not enough");
                return new TextMessage("ChatGPT token is not enough");
            }
        }
    }

    @EventMapping
    public TextMessage handleStickerMessageEvent(MessageEvent<StickerMessageContent> event){
        log.writeLog("Error" , "User send sticker");
        return new TextMessage(notMessageNotification);
    }

    @EventMapping
    public TextMessage handleImageMessageEvent(MessageEvent<ImageMessageContent> event){
        log.writeLog("Error" , "User send image");
        return new TextMessage(notMessageNotification);
    }

    @EventMapping
    public TextMessage handleVideoMessageEvent(MessageEvent<VideoMessageContent> event){
        log.writeLog("Error" , "User send video");
        return new TextMessage(notMessageNotification);
    }

    @EventMapping
    public TextMessage handleLocationMessageEvent(MessageEvent<LocationMessageContent> event){
        log.writeLog("Error" , "User send location");
        return new TextMessage(notMessageNotification);
    }

    @EventMapping
    public TextMessage handleAudioMessageEvent(MessageEvent<AudioMessageContent> event){
        log.writeLog("Error" , "User send audio");
        return new TextMessage(notMessageNotification);
    }

    @EventMapping
    public TextMessage handleFileMessageEvent(MessageEvent<FileMessageContent> event){
        log.writeLog("Error" , "User send file");
        return new TextMessage(notMessageNotification);
    }




}
