package com.farzin.checklist.model.home

data class Task(
    val taskId:String = "",
    val userId:String = "",
    val title:String = "",
    val description:String = "",
    val priority:Int = -1,
    val dueDate:String = "",
    val dueTime:String = "",
    val subTask:List<Subtask>,
){
    // Add a constructor with no arguments
    constructor() : this("", "", "", "", 1, "", "", emptyList())
}

data class Subtask(
    val subtaskId:Int = 0,
    var title: String = "",
    val subtaskCompleted:Boolean = false,
)


