package me.cdh

import me.cdh.FileSearchBar.dialog
import me.cdh.FileSearchBar.fileList
import me.cdh.FileSearchBar.textBar
import me.cdh.FileSearchBar.winBar
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

object UserKeyListener : KeyAdapter() {
    override fun keyPressed(e: KeyEvent?) {
        when (e?.keyCode) {
            KeyEvent.VK_ESCAPE -> winBar.dispose()
            KeyEvent.VK_UP -> {
                fileList.requestFocus()
            }

            KeyEvent.VK_DOWN -> {
                fileList.requestFocus()
            }

            KeyEvent.VK_ENTER -> {
                dialog.isVisible = textBar.text.isNotBlank()
                if (dialog.isVisible && fileList.selectedValue != null) {
                    println(fileList.selectedValue)
                }
            }
        }
    }

    override fun keyTyped(e: KeyEvent?) {
        textBar.requestFocus()
    }
}