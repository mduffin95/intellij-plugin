package com.github.mduffin95.intellijplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.vcs.annotate.AnnotationGutterActionProvider
import com.intellij.openapi.vcs.annotate.FileAnnotation

class JiraGutterActionProvider : AnnotationGutterActionProvider {
    override fun createAction(annotation: FileAnnotation): AnAction {
        return JiraOpenInBrowserAction(annotation)
    }
}
