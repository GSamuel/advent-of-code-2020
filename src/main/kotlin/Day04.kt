fun main() {
    ProblemDay04().solve()
}

class ProblemDay04:Problem<Int>("Day04") {
    override fun validatePart1TestInput(result: Int) {
        check(result == 6)
    }

    override fun validatePart2TestInput(result: Int) {
        check(result == 6)
    }

    override fun part1(input:List<String>):Int {
        return input.toPassports().count { it.containsRequiredFields() }
    }

    override fun part2(input:List<String>):Int {
        return input.toPassports().count { it.isValid() }
    }

    private fun List<String>.toPassports(): List<Passport> {
        return Passport.Builder().also { builder ->
            forEach { builder.addLine(it) }
        }.build()
    }


    sealed class Field(val name:String, private val pred:(String)->Boolean, val required:Boolean = true) {

        fun isValid(input:String):Boolean = pred(input)

        companion object {
            val ALL = listOf(BirthYear, IssueYear, ExpirationYear, Height, HairColor, EyeColor, PassportID, CountryID)
        }
    }

    object BirthYear:Field("byr", {
        (1920..2002).contains(it.toIntOrNull())
    })

    object IssueYear:Field("iyr",{
        (2010..2020).contains(it.toIntOrNull())
    })

    object ExpirationYear:Field("eyr",{
        (2020..2030).contains(it.toIntOrNull())
    })

    object Height:Field("hgt",{
        if(it.endsWith("cm"))
            (150..193).contains(it.removeSuffix("cm").toIntOrNull())
        else if(it.endsWith("in"))
            (59..76).contains(it.removeSuffix("in").toIntOrNull())
        else false
    })

    object HairColor:Field("hcl",{
        it.startsWith("#") && it.removePrefix("#").all {
            ('0'..'9').contains(it) || ('a'..'f').contains(it)
        } && it.length == 7
    })

    object EyeColor:Field("ecl", {
        it in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    })

    object PassportID:Field("pid",{
        it.length ==9 && it.toIntOrNull() != null
    })

    object CountryID:Field("cid",{
        true
    }, false)
 /*
    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    hgt (Height) - a number followed by either cm or in:
    If cm, the number must be at least 150 and at most 193.
    If in, the number must be at least 59 and at most 76.
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    pid (Passport ID) - a nine-digit number, including leading zeroes.
    cid (Country ID) - ignored, missing or not.*/

    data class Passport internal constructor(val entries: Map<String, String>) {

        fun isValid(): Boolean {
            return entries.all { entry ->  Field.ALL.first { it.name == entry.key }.isValid(entry.value) } &&
                    containsRequiredFields()
        }

        fun containsRequiredFields() = entries.keys.containsAll(Field.ALL.filter { it.required }.map { it.name })

        class Builder {
            var lines:MutableList<String> = mutableListOf()
            var passports:MutableList<Passport> = mutableListOf()

            fun addLine(line:String) = apply {
                if(line.isBlank()) {
                    createPassport()
                } else {
                    lines.add(line)
                }
            }

            private fun createPassport() {
                if(lines.isNotEmpty()) {
                    passports.add(Passport(lines.toPassportMap()))
                    lines.clear()
                }
            }

            private fun List<String>.toPassportMap(): Map<String, String> {
                return joinToString(separator = " ").split(" ").map {
                    it.split(":").let { it[0] to it[1] }
                }.toMap()
            }

            fun build() = apply { createPassport() }.passports.toList()
        }

    }
}