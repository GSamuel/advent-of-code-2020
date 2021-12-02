fun main() {
    ProblemDay08().solve()
}

class ProblemDay08 : Problem<Int>("Day08") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 5)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 8)
    }

    override fun part1(input: List<String>): Int {
        val program = input.toProgram()
        program.run()
        return program.lastState.acc
    }

    override fun part2(input: List<String>): Int {
        val program = input.toProgram()
        val programs = program.operations.mapIndexedNotNull { p, op:Op ->
            when(op) {
                is Acc -> null
                is Jmp -> program.change(p, op.toNop())
                is Nop -> program.change(p, op.toJmp())
            }
        }
        val success = programs.filter { it.apply { it.run() }.terminationState == TerminationState.SUCCESS }
        return success.first().acc
    }

    private fun List<String>.toProgram():Program = Program(this.map { it.toOp() })

    private fun String.toOp(): Op {
        val (op, v) = split(" ")
        return when (op) {
            "acc" -> Acc(v.toInt())
            "nop" -> Nop(v.toInt())
            "jmp" -> Jmp(v.toInt())
            else -> error("Invalid operation")
        }
    }

    sealed class Op(val value: Int, val f: (State) -> (State)) {
        fun execute(state: State): State = f(state)
    }

    class Nop(value: Int) : Op(value, { State(it.p + 1, it.acc) })
    class Jmp(value: Int) : Op(value, { State(it.p + value, it.acc) })
    class Acc(value: Int) : Op(value, { State(it.p + 1, it.acc + value) })

    private fun Op.toNop() = Nop(value)
    private fun Op.toJmp() = Jmp(value)
    private fun Op.toAcc() = Acc(value)

    data class State(val p: Int, val acc: Int)

    enum class TerminationState { SUCCESS, INFINITE_LOOP}

    class Program(val operations: List<Op>, private var state: State = State(0, 0)) {

        var lastState:State = state
        var terminationState:TerminationState? = null

        fun next() {
            if(state.p >= operations.size) {
                terminationState = TerminationState.SUCCESS
                return
            }
            state = operations[state.p].execute(state)
        }

        fun run() {
            val visited = HashSet<Int>()
            while(terminationState == null) {
                lastState = state
                next()
                if(pointer in visited) {
                    terminationState = TerminationState.INFINITE_LOOP
                    return
                }
                visited.add(lastState.p)
            }
        }

        val acc: Int
            get():Int = state.acc
        val pointer: Int
            get():Int = state.p

        fun change(p:Int, op:Op): Program {
            return Program(operations.toMutableList().apply { this[p] = op }, state)
        }
    }
}