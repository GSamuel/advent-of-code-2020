import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String):List<String> = File("src/resources", "$name.txt").readLines()

object Reader {
    fun readInput(name:String):List<String> = File(this::class.java.getClassLoader().getResource("$name.txt").getFile()).readLines()
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun List<String>.toNumbers() = map { it.toInt() }