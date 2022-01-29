package com.github.mduffin95.intellijplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vcs.annotate.FileAnnotation
import com.intellij.openapi.vcs.annotate.UpToDateLineNumberListener
import git4idea.annotate.GitFileAnnotation

class PopupDialogAction(name: String, private val annotation: FileAnnotation) : AnAction(name), UpToDateLineNumberListener {

    private var myLineNumber = -1

    override fun actionPerformed(e: AnActionEvent) {
        if (myLineNumber < 0) return

//        val project = annotation.project
//        val virtualFile = annotation.file ?: throw Exception("No file found")

//        val lineRevisionNumber = annotation.getLineRevisionNumber(myLineNumber)

        if (annotation !is GitFileAnnotation) return
        val currentFileRevisionProvider = annotation.currentFileRevisionProvider ?: throw Exception("No revision provider")

//        annotation.getLineRevisionNumber().
        val lineInfo = annotation.getLineInfo(myLineNumber)
//        lineInfo.subject
//        val revision = currentFileRevisionProvider.getRevision(myLineNumber)
//        val commitMessage = revision?.commitMessage

        println(lineInfo?.subject)
    }

    override fun consume(t: Int) {
        myLineNumber = t
    }
}
