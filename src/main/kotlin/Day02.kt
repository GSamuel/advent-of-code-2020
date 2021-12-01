fun main() {
    ProblemDay02().solve()
}

class ProblemDay02:Problem("Day02") {
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