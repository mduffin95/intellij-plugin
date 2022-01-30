package com.github.mduffin95.intellijplugin

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vcs.annotate.FileAnnotation
import com.intellij.openapi.vcs.annotate.UpToDateLineNumberListener
import git4idea.annotate.GitFileAnnotation
import icons.TasksCoreIcons
import java.util.function.Supplier

private val supplier = Supplier { "Open Jira" }
private val icon = TasksCoreIcons.Jira

class JiraOpenInBrowserAction(private val annotation: FileAnnotation):
    AnAction(supplier, icon),
    UpToDateLineNumberListener {

    private var myLineNumber = -1
    private val regex = Regex("^([A-Z]+-\\d+): .*\$")

    override fun actionPerformed(e: AnActionEvent) {
        if (myLineNumber < 0) return

        if (annotation !is GitFileAnnotation) return

        val lineInfo = annotation.getLineInfo(myLineNumber) ?: return
        val message = lineInfo.subject
        val matchResult = regex.find(message) ?: return
        if (matchResult.groups.size < 2) return
        val group = matchResult.groups[1] ?: return
        val id = group.value

        BrowserUtil.browse("https://opengamma.atlassian.net/browse/$id")
    }

    override fun consume(t: Int) {
        myLineNumber = t
    }
}
