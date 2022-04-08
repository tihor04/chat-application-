package com.example.chatapplication

class Message {

    var message:String?=null
    var senderId:String?=null

    //empty constructor for a message
    constructor(){}

    //when we actually pass in a messege
    constructor(message:String?,senderId:String?){
        this.message=message
        this.senderId=senderId
    }
}