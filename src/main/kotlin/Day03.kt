import java.math.BigInteger

fun main() {
    ProblemDay03().solve()
}

class ProblemDay03:Problem<BigInteger>("Day03") {
    override fun validatePart1TestInput(result: BigInteger) {
        check(result == 7.toBigInteger())
    }

    override fun validatePart2TestInput(result: BigInteger) {
        check(result == 336.toBigInteger())
    }

    override fun part1(input:List<String>):BigInteger {
        return Grid(input).countTrees(3,1).toBigInteger()
    }

    override fun part2(input:List<String>):BigInteger {
        return Grid(input).run {
            listOf(Pair(1,1),Pair(3,1),Pair(5,1),Pair(7,1),Pair(1,2))
                .map { countTrees(it.first, it.second) }
                .map { it.toBigInteger() }
                .reduce { a, b -> a * b }
        }
    }

    private fun Grid.countTrees(slopeX:Int, slopeY:Int): Int {
        return (0 until height()).count { isTree(it*slopeX, it*slopeY) }
    }

    class Grid(private val lines:List<String>) {
        fun width() = lines.first().length
        fun height() = lines.size
        fun inBounds(x:Int, y:Int) = x >= 0 && y >= 0 && y < height()
        fun isTree(x:Int, y:Int) = inBounds(x,y) && lines[y][x%width()] == '#'
    }
}