abstract class Problem<T>(private val fileName:String) {
    abstract fun validatePart1TestInput(result:T)
    abstract fun validatePart2TestInput(result:T)
    abstract fun part1(input:List<String>):T
    abstract fun part2(input:List<String>):T

    fun solve() {
        validatePart1TestInput(part1(readTestInput()))
        val input = readInput()
        println(part1(input))

        validatePart2TestInput(part2(readTestInput()))
        println(part2(input))
    }

    private fun readInput() = Reader.readInput(fileName)
    private fun readTestInput() = Reader.readInput("${fileName}_test")
}