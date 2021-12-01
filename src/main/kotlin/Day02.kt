fun main() {
    ProblemDay02().solve()
}

class ProblemDay02:Problem<Int>("Day02") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 2)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 1)
    }

    override fun part1(input:List<String>):Int {
        return input.map { it.toPolicy() }.count { it.isValidRange() }
    }

    override fun part2(input:List<String>):Int {
        return input.map { it.toPolicy() }.count { it.isValidPosition() }
    }

    private fun String.toPolicy(): PasswordPolicy {
        val (range, char, pass) = split(" ")
        val (min, max) = range.split("-").map { it.toInt() }
        return PasswordPolicy(min, max, char[0], pass)
    }

    class PasswordPolicy(val first:Int, val second:Int, val char:Char, val password:String) {
        fun isValidRange():Boolean = password.count { it == char } in first..second
        fun isValidPosition():Boolean = (password[first-1] == char) xor (password[second-1] == char)
    }
}