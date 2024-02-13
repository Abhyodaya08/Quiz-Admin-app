package com.example.testadminapp

data class User(
    var id : String,
    var question : String,
    var option_a : String,
    var option_b : String,
    var option_c : String,
    var option_d : String,
    var correct_ans : String
){
    constructor(): this("",""," ",""," ","","")
}

