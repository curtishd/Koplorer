package me.cdh

import me.cdh.FileSearchBar.fileList
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

object UserMouseListener : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent?) {
        val index = fileList.locationToIndex(e?.point)
        if (index != -1) {
            val selectedValue = fileList.model.getElementAt(index)
            println(selectedValue)
        }
    }
}