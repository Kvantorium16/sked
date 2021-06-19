package com.example.sked

class Subject {

    var id : Int = 0
    var subject : String = ""
    var date : String = ""
    var time : String = ""
    var teacher : String = ""

    constructor(subject: String, date: String, time: String, teacher: String){
        this.subject = subject
        this.date = date
        this.time = time
        this.teacher = teacher
    }

    constructor(){}

}