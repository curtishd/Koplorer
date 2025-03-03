package me.cdh

import com.formdev.flatlaf.FlatDarkLaf
import javax.swing.SwingUtilities

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        FlatDarkLaf.setup()
        SwingUtilities.invokeLater { FileSearchBar.initWindow() }
    }
}