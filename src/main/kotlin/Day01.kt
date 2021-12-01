fun main() {
    ProblemDay01().solve()
}

class ProblemDay01:Problem("Day01") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 1)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 1)
    }

    override fun part1(input:List<String>):Int {
        return input.size
    }

    override fun part2(input:List<String>):Int {
        return input.size
    }
}