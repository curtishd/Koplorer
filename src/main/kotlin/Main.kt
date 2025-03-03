package me.cdh

import com.formdev.flatlaf.themes.FlatMacDarkLaf
import javax.swing.SwingUtilities

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        FlatMacDarkLaf.setup()
        SwingUtilities.invokeLater { FileSearchBar.initWindow() }
    }
}