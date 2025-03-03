package me.cdh

import me.cdh.FileSearchBar.fileList
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import kotlin.system.exitProcess

object UserMouseListener : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent?) {
        val index = fileList.locationToIndex(e?.point)
        if (index != -1) {
//            val keyword = fileList.model.getElementAt(index)
//            FileSearchBar.searchFile(keyword)
            exitProcess(0)
        }
    }
}