package com.github.mduffin95.intellijplugin.services

import com.intellij.openapi.project.Project
import com.github.mduffin95.intellijplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
