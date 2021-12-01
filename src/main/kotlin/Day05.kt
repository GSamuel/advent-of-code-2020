fun main() {
    ProblemDay05().solve()
}

class ProblemDay05:Problem<Int>("Day05") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 820)
    }

    override fun validatePart2TestInput(result: Int) {
    }

    override fun part1(input:List<String>):Int {
        return input.map { it.toSeatId() }.maxOf { it }
    }

    override fun part2(input:List<String>):Int {
        val seats = input.map { it.toSeatId() }
        val res = seats.filter { seatId -> (!seats.contains(seatId-1) || !seats.contains(seatId+1)) && seats.minByOrNull { it } != seatId&& seats.maxByOrNull { it } != seatId}
        return res.sumOf { it }.div(res.size)
    }

    private fun String.toSeatId(): Int {
        return map {
            when(it) {
                'B', 'R' -> 1
                else -> 0
            }
        }.joinToString(separator = "").toInt(2)
    }
}