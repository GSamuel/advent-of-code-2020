import ProblemDay04.*
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day04Test {

    @Test
    fun birthYear() {
        assertTrue(BirthYear.isValid("2002"))
        assertTrue(BirthYear.isValid("1920"))

        assertFalse(BirthYear.isValid(""))
        assertFalse(BirthYear.isValid("2003"))
        assertFalse(BirthYear.isValid("1919"))
    }

    @Test
    fun issueYear() {
        assertTrue(IssueYear.isValid("2020"))
        assertTrue(IssueYear.isValid("2010"))

        assertFalse(IssueYear.isValid(""))
        assertFalse(IssueYear.isValid("2021"))
        assertFalse(IssueYear.isValid("2009"))
    }

    @Test
    fun height() {
        assertTrue(Height.isValid("60in"))
        assertTrue(Height.isValid("190cm"))

        assertFalse(Height.isValid("190in"))
        assertFalse(Height.isValid("190"))
    }

    @Test
    fun hairColor() {
        assertTrue(HairColor.isValid("#123abc"))

        assertFalse(HairColor.isValid("#123abz"))
        assertFalse(HairColor.isValid("123abc"))
    }

    @Test
    fun eyeColor() {
        assertTrue(EyeColor.isValid("brn"))

        assertFalse(EyeColor.isValid("wat"))
    }

    @Test
    fun passportID() {
        assertTrue(PassportID.isValid("000000001"))

        assertFalse(PassportID.isValid("0123456789"))
    }
}