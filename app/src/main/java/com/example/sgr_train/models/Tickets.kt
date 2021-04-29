package com.example.sgr_train.models

class Tickets {
    var username: String = ""
    var source: String=""
    var destination: String=""
    var ticket_number:String=""
    constructor(username: String, source:String,ticket_number:String, destination:String){
        this.username = username
        this.source = source
        this.destination = destination
        this.ticket_number = ticket_number
    }
}