package dev.mfazio.aoc.twentytwo.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("/day-seven.txt")

    println(funPartOne(input))
}

interface SizedItem {
    fun getSize(): Int
}

data class File(
    val fileSize: Int,
    val name: String,
): SizedItem {
    override fun getSize(): Int = fileSize
}

data class Folder(
    val name: String,
    val files: MutableList<File> = mutableListOf(),
    val folders: MutableList<Folder> = mutableListOf(),
    val parent: Folder? = null,
): SizedItem {
    override fun getSize(): Int = files.sumOf { it.getSize() } + folders.sumOf { it.getSize() }
    override fun toString(): String {
        return "$name (parent=${parent?.name}), size=${getSize()}, Files=$files, Folders=$folders"
    }
}

fun getFileStructure(input: List<String>): Folder? {

    var structure: Folder? = null

    var currentFolder: Folder? = null

    input.forEach { line ->
        println("Current directory = ${currentFolder?.name}")
        when(line.first()) {
            '$' -> {
                if (line.contains("cd")) {
                    if (line.contains("..")) {
                        currentFolder = currentFolder?.parent
                    } else {
                        val newFolderName = line.replace("$ cd ", "")


                        val folder = currentFolder?.folders?.find { it.name == newFolderName }
                        if (folder == null) {
                            currentFolder?.folders?.add(Folder(newFolderName, parent = currentFolder))
                        }

                        val oldFolder = currentFolder
                        currentFolder = if (currentFolder == null) {
                            Folder(newFolderName)
                        } else {
                            currentFolder?.folders?.find { it.name == newFolderName }
                        }
                    }
                }
            }
            'd' -> {
                val folderName = line.replace("dir ", "")
                if (currentFolder?.folders?.any { it.name == folderName } != true) {
                    currentFolder?.folders?.add(Folder(folderName, parent = currentFolder))
                }
            }
            else -> {
                val (size, fileName) = line.split(" ")

                if (currentFolder?.files?.any { it.name == fileName } != true) {
                    currentFolder?.files?.add(
                        File(
                            fileSize = size.toInt(),
                            name = fileName
                        )
                    )
                }
            }
        }

        if (structure == null && currentFolder != null) {
            structure = currentFolder
        }
    }

    return structure
}

fun funPartOne(input: List<String>): Int {

    val structure = getFileStructure(input)

    structure?.let {
        printFolder(it)
        println("Folders: ${getFolders(it).map { "${it.name} (${it.getSize()})" }}")
    }

    return structure?.let { top ->
        getFolders(top).filter { it.getSize() <= 100_000 }.sumOf { it.getSize() }
    } ?: -1
}

fun getFolders(folder: Folder): List<Folder> = listOf(folder) + folder.folders.flatMap { getFolders(it) }

fun printFolder(folder: Folder, tabs: Int = 0) {
    val tabsText = (0..tabs).joinToString("") { "\t" }
    println("$tabsText${folder.name} (dir)")
    folder.folders.forEach { printFolder(it, tabs+1) }
    folder.files.forEach { println("$tabsText\t${it.name}(file, size=${it.fileSize})}") }
}