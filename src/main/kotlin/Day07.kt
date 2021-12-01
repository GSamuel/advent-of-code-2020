import java.util.*
import kotlin.collections.HashSet

fun main() {
    ProblemDay07().solve()
}

class ProblemDay07:Problem<Int>("Day07") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 4)
    }

    override fun validatePart2TestInput(result: Int) {
        //check(result == 1)
    }

    override fun part1(input:List<String>):Int {
        return input.toBags().findBagsContaining("shiny gold").size
    }

    override fun part2(input:List<String>):Int {
        return input.toBags().countBagsIn("shiny gold")
        //higher then: 581
    }

    private fun List<Bag>.countBagsIn(name:String): Int {
        val counts:MutableMap<String, Int> = mutableMapOf()

        while(!counts.containsKey(name)) {
            val bag = first { !counts.containsKey(it.name) && it.content.keys.all { counts.containsKey(it) } }
            counts[bag.name] = bag.content.entries.sumOf { (counts[it.key]!! + 1) * (it.value)}
        }
        return counts[name]!!
    }

    private fun List<Bag>.findBagsContaining(name:String): List<String> {

        val done:MutableSet<String> = HashSet()

        val frontier:Queue<String> = LinkedList()
        frontier.add(name)

        while(frontier.isNotEmpty()) {
            val newBag = frontier.remove()
            if(done.contains(newBag))
                continue
            filter { it.content.containsKey(newBag) }
                .forEach { frontier.add(it.name) }
            done.add(newBag)
        }
        return done.filter { it != name }
    }

    private fun List<String>.toBags() = map { it.toBag() }

    private fun String.toBag(): Bag {
        val (name, content) = this.split(" bags contain ")
        if(content == "no other bags.")
            return Bag(name)
        val bagcontent = content.split(", ").associate {
            it.removeSuffix(".").let { it.substring(2).split(" bag").first() to it.substring(0, 1).toInt() }
        }
        return Bag(name, bagcontent)
    }

}

data class Bag(val name:String, val content:Map<String, Int> = mapOf())



sealed class OldBag(val bags:Map<Int, OldBag> = mapOf())

object DottedBlackBag:OldBag()
object FadedBlueBag:OldBag()
object VibrantPlumBag:OldBag(mapOf(5 to FadedBlueBag))
object DarkOliveBag:OldBag(mapOf(3 to FadedBlueBag, 4 to DottedBlackBag))
object ShinyGoldBag:OldBag(mapOf(1 to DarkOliveBag, 2 to VibrantPlumBag))
object MutedYellowBag:OldBag(mapOf(2 to ShinyGoldBag, 9 to FadedBlueBag))
object BrightWhiteBag:OldBag(mapOf(1 to ShinyGoldBag))
object DarkOrangeBag:OldBag(mapOf(3 to BrightWhiteBag, 4 to MutedYellowBag))
object LightRedBag:OldBag(mapOf(1 to BrightWhiteBag, 2 to MutedYellowBag))
