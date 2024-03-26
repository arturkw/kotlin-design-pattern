package org.ahk.kotlindesignpatterns.behavioral

sealed class Command(protected val fileSystem: FileSystem) {
    abstract fun execute()
}

class CreateTextFile(private val fileName: String, private val content: String) : Command(fs) {
    override fun execute() {
        fileSystem.createFile(fileName, content)
    }
}

class DeleteFile(private val fileName: String) : Command(fs) {
    override fun execute() {
        fileSystem.deleteFile(fileName)
    }
}

class CopyFile(private val sourceFileName: String, private val targetFileName: String) : Command(fs) {
    override fun execute() {
        fileSystem.copyFile(sourceFileName, targetFileName)
    }
}

class ListFiles : Command(fs) {
    override fun execute() {
        fileSystem.listFiles()
    }
}

class ShowFileContent(private val fileName: String) : Command(fs) {
    override fun execute() {
        fileSystem.showFileContent(fileName)
    }
}

interface FileSystem {
    fun createFile(fileName: String, content: String)
    fun deleteFile(fileName: String)
    fun copyFile(sourceFileName: String, targetFileName: String)
    fun listFiles()
    fun showFileContent(fileName: String)
}

fun main(args: Array<String>) {

    fun executeCommand(command: Command) {
        println("Executing command: ${command.javaClass.simpleName}")
        command.execute()
    }

    executeCommand(CreateTextFile("1.txt", "String from 1.txt"))
    executeCommand(CreateTextFile("2.txt", "String from 2.txt"))
    executeCommand(CreateTextFile("3.txt", "String from 3.txt"))
    executeCommand(ListFiles())
    executeCommand(CopyFile("1.txt", "4.txt"))
    executeCommand(DeleteFile("3.txt"))
    executeCommand(ShowFileContent("111.txt"))
}

val fs = object : FileSystem {
    val fsContent = mutableMapOf<String, String>()
    override fun createFile(fileName: String, content: String) {
        fsContent.merge(fileName, content) { _, newContent -> newContent }
    }

    override fun deleteFile(fileName: String) {
        fsContent.remove(fileName)
    }

    override fun copyFile(sourceFileName: String, targetFileName: String) {
        val content = fsContent[sourceFileName]
        if (content != null) {
            fsContent[targetFileName] = content
        }
    }

    override fun listFiles() {
        if (fsContent.isEmpty()) {
            println("   Directory is empty")
        } else {
            println("   List of files:")
            fsContent.forEach { e ->
                println("   ${e.key}")
            }
        }
    }

    override fun showFileContent(fileName: String) {
        val content: String? = fsContent[fileName]
        if (content != null) {
            println("   File $fileName content:")
            println("   $content")
        } else {
            println("   File $fileName doesn't exist.")
        }
    }
}