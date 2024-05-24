package fr.lmo.aoc2023

import java.io.File


const val fileName = "src/main/resources/2023/d08/input.txt"
const val fileNameTest = "src/main/resources/2023/d08/input-test.txt"

/*
LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)
23147
22289513667691
*/
const val firstNode = "AAA"
const val goal = "ZZZ"

fun main() {
    val testInput = readFile(fileNameTest)
//    println(")) Test Solution: '${solution(testInput)}'")
    val input = readFile(fileName)
 //   println(")) Solution: '${solution(input)}'")

    println("# Test Solution2: ${solution2(testInput)}")
    println("# Solution2: '${solution2(input)}'")
}

fun solution(input: List<String>): String {
    // LLRLRLRLRLRRL
    val directions = buildDirections(input)
    // AAA = (BBB, BBB)
    val network = buildNetwork(input)
    println("directions -> " + directions)
    println("network -> " + network)

    var currentNode: String = firstNode
    var nbSteps: Int = 0
    while (currentNode != goal) {
        val direction = directions[nbSteps % directions.length]
        println("Processing node, direction $currentNode $direction")
        currentNode = findNextNode(currentNode, direction, network)
        println("next is = $currentNode")
        nbSteps++
    }

    return nbSteps.toString()
}

// This part 2 solution is shit, takes too much time and is not optimized. Didn't achieve to find the correct answer
// by processing for a whole night :')
// TODO optimize ;o)
fun solution2(input: List<String>): String {
    // LLRLRLRLRLRRL
    val directions = buildDirections(input)
    // AAA = (BBB, BBB)
    val network = buildNetwork(input)
    println("directions -> " + directions)
    println("network -> " + network)

    var currentNodes: Set<String> = findStartingNodes(network)
    println("Starting nodes $currentNodes")
    var nbSteps: Long= 0
    while (!currentNodes.all { it.endsWith("Z") }) {
        val index = (nbSteps % directions.length).toInt()
        if (index < 0) println("NB STEPS BUG INDEX:$nbSteps")
        val direction = directions[index] //Exception in thread "main" java.lang.StringIndexOutOfBoundsException: Index -213 out of bounds for length 293
           println("Processing $nbSteps -> $direction, $currentNodes")
        currentNodes = currentNodes.asSequence().map { node -> findNextNode(node, direction, network) }.toSet()
       // println("New nodes $currentNodes")
        nbSteps++
    }
    return nbSteps.toString()
}

fun findStartingNodes(network: Map<String, Pair<String, String>>): Set<String> =
    network.keys.filter { it.endsWith("A") }.toSet()

private fun buildDirections(input: List<String>) = input.take(1).first()

private fun buildNetwork(input: List<String>) = input.drop(2)
    .associate { networkLines ->
        val (from, dests) = networkLines.split(" = ")
        val (left, right) = dests.trimStart('(').trimEnd(')').split(", ")
        from to (left to right)
    }

fun findNextNode(currentNode: String, direction: Char, network: Map<String, Pair<String, String>>): String {
    return when (direction) {
        'L' -> network[currentNode]!!.first
        'R' -> network[currentNode]!!.second
        else -> error("fuck")
    }
}

fun readFileResource(fileName: String): List<String> = object {}.javaClass.getResource(fileName).readText(
    Charsets.UTF_8
).lines()

fun readFile(fileName: String): List<String> = File(fileName).readLines()
