# JavaLineBot Deployment

## Introduction
Here I'll show you how to build your own Line Bot(connect to chatGPT)  
with java spring boot just follow the steps below

## Requirements

Usage |  Version
---- | ----
Spring boot | 2.6.4      
JDK    |   1.8
ngrok  |  xxx
MySQL  |  xxx

***
  
### Clone source code

clone my repository to your local with git :)  
https://github.com/macconnn/javaLineBot.git


### First create your Line Developer account

## Create project
![GITHUB](https://github.com/macconnn/javaLineBot/blob/main/image/create_project.png)
## Set a provider name
![GITHUB](https://github.com/macconnn/javaLineBot/blob/main/image/setName.png)
## Select Messaging API channel
![GITHUB](https://github.com/macconnn/javaLineBot/blob/main/image/selectMsgAPI.png)
## Enter some necessary basic information then create it
![GITHUB](https://github.com/macconnn/javaLineBot/blob/main/image/created.png)
## Create channel successful
![GITHUB](https://github.com/macconnn/javaLineBot/blob/main/image/1.png)
### Here's some important information to jot down  
***
> Basic Setting
> > Channel secret  
> > Your user ID  


> Masseging API  
> > Bot basic ID  
> > Channel access token (press issue button)  


### instll ngrok
***
Use ngrok can let LineBot connect to your own server  
listening to 8080 port (server default port)
```  
ngrok http 8080  
```  
copy the Forwarding and paste to here  
![GITHUB](https://github.com/macconnn/javaLineBot/blob/main/image/2.png)









