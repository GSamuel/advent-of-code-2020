fun main() {
    ProblemDay01().solve()
}

class ProblemDay01:Problem<Int>("Day01") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 514579)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 241861950)
    }

    override fun part1(input:List<String>):Int {
        return input.toNumbers().findMatch(2020)!!.multiply()
    }

    private fun List<Int>.findMatch(goal:Int): List<Int>? {
        return groupBy { it.toLower(goal) }.values.firstOrNull { it.size == 2 }?.firstOrNull()?.run {
            listOf(this, this.flip(goal))
        }
    }

    override fun part2(input:List<String>):Int {
        return with(input.toNumbers()) {
            mapNotNull { findMatch(it.flip()) }.flatten().distinct().multiply()
        }
    }

    private fun Int.flip(goal:Int = 2020) = goal - this
    private fun Int.toLower(goal: Int = 2020) = if(this > goal*0.5) flip(goal) else this
    private fun Int.toResult(goal:Int = 2020) = this * this.flip(goal)
    private fun List<Int>.multiply() = reduce {res, el -> res * el}
}