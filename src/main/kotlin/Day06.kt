fun main() {
    ProblemDay06().solve()
}

class ProblemDay06 : Problem<Int>("Day06") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 11)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 6)
    }

    override fun part1(input: List<String>): Int {
        return input.joinToString(separator = "") { it.ifBlank { "|" } }.split("|").sumOf { it.toSet().size }
    }

    override fun part2(input: List<String>): Int {
        return input.joinToString(separator = "_") { it.ifBlank { "|" } }.split("|").sumOf { group ->
            group.toSet().filter { it != '_' }
                .count { c -> group.split("_").filter { it.isNotBlank() }.all { it.contains(c) } }
        }
    }

}