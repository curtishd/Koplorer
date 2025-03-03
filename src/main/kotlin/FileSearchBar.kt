package me.cdh

import java.awt.BorderLayout
import java.awt.Font
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.util.concurrent.Executors
import javax.swing.*
import kotlin.io.path.Path
import kotlin.system.exitProcess

object FileSearchBar {

    val fileList = JList<String>().apply {
        font = Font("Hack Nerd Font", Font.PLAIN, 10)
        addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent?) {
                when (e?.keyCode) {
                    KeyEvent.VK_ESCAPE -> winBar.dispose()
                    KeyEvent.VK_ENTER -> {
                        ProcessBuilder("powershell", selectedValue ?: "").start()
                        exitProcess(0)
                    }
                }
            }

            override fun keyTyped(e: KeyEvent?) {
                textBar.requestFocus()
            }
        })
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
    val textBar: JTextField = JTextField().apply {
        font = Font("Hack Nerd Font", Font.PLAIN, 20)
        addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent?) {
                when (e?.keyCode) {
                    KeyEvent.VK_ESCAPE -> winBar.dispose()
                    KeyEvent.VK_UP -> fileList.requestFocus()
                    KeyEvent.VK_DOWN -> fileList.requestFocus()
                    KeyEvent.VK_ENTER -> {
                        Thread.ofVirtual().start {
                            dialog.isVisible = text.isNotBlank()
                            searchFile(text)
                        }
                    }
                }
            }
        })
    }

    fun initWindow() {
        dialog.add(fileList)
        winBar.add(textBar, BorderLayout.CENTER)
        winBar.isVisible = true
    }

    fun searchFile(keyword: String) {
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).use {
            val resultList = mutableListOf<String>()
            Files.walkFileTree(Path("C:\\"), object : SimpleFileVisitor<Path>() {
                override fun visitFile(file: Path?, attrs: BasicFileAttributes): FileVisitResult {
                    if (file?.fileName.toString().contains(keyword)) {
                        resultList.add(file.toString())
                    }
                    return FileVisitResult.CONTINUE
                }

                override fun visitFileFailed(file: Path?, exc: IOException): FileVisitResult {
                    return FileVisitResult.CONTINUE
                }
            })
            fileList.setListData(resultList.take(14).toTypedArray())
        }
    }
}