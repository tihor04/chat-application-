package com.example.chatapplication

class user {
    var name : String?=null
    var email: String?=null
    var UID: String?=null

    constructor(){}

    constructor(name:String?,email:String?,UID: String?){
        this.name=name
        this.email=email
        this.UID=UID

    }
}