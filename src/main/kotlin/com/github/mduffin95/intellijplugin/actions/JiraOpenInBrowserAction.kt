package com.github.mduffin95.intellijplugin.actions

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vcs.annotate.FileAnnotation
import com.intellij.openapi.vcs.annotate.UpToDateLineNumberListener
import git4idea.annotate.GitFileAnnotation

class JiraOpenInBrowserAction(name: String, private val annotation: FileAnnotation) : AnAction(name), UpToDateLineNumberListener {

    private var myLineNumber = -1

    override fun actionPerformed(e: AnActionEvent) {
        if (myLineNumber < 0) return

        if (annotation !is GitFileAnnotation) return

        val lineInfo = annotation.getLineInfo(myLineNumber)
        val message = lineInfo?.subject!!

        val regex = Regex("^([A-Z]+-\\d+): .*\$")

        val matchResult = regex.find(message)
        val (id) = matchResult!!.destructured
        BrowserUtil.browse("https://opengamma.atlassian.net/browse/$id")
        println(id)
    }

    override fun consume(t: Int) {
        myLineNumber = t
    }
}
