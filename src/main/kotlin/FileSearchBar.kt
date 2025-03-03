package me.cdh

import java.awt.BorderLayout
import java.awt.Font
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.util.concurrent.Executors
import javax.swing.*
import kotlin.io.path.Path

object FileSearchBar {

    val fileList = JList<String>().apply {
        font = Font("Hack Nerd Font", Font.PLAIN, 20)
        setListData(arrayOf("cdh", "kksk"))
        addKeyListener(UserKeyListener)
        addMouseListener(UserMouseListener)
    }
    val winBar = JFrame().apply {
        layout = BorderLayout()
        isUndecorated = true
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        setSize(600, 60)
        setLocationRelativeTo(null)
    }
    val dialog = JDialog(winBar).apply {
        isUndecorated = true
        setSize(600, 380)
        setLocation(winBar.x, winBar.y + 60)
    }
    val textBar = JTextField().apply {
        font = Font("Hack Nerd Font", Font.PLAIN, 20)
        addKeyListener(UserKeyListener)
    }

    fun initWindow() {
        dialog.add(fileList)
        winBar.add(textBar, BorderLayout.CENTER)
        winBar.isVisible = true
    }

    fun searchFile(keyword: String) {
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).use {
            Files.walkFileTree(Path("C:\\"), object : SimpleFileVisitor<Path>() {
                override fun visitFile(file: Path?, attrs: BasicFileAttributes): FileVisitResult {
                    if (file?.fileName.toString().contains(keyword)) {
                        println("Found $file")
                    }
                    return FileVisitResult.CONTINUE
                }

                override fun visitFileFailed(file: Path?, exc: IOException): FileVisitResult {
                    return FileVisitResult.CONTINUE
                }
            })
        }
    }
}