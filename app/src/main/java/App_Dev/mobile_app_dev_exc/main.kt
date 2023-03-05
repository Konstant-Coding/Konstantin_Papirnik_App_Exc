package App_Dev.mobile_app_dev_exc
import kotlin.random.Random

fun main() {
    val numberToGuess = generateNumber() //number generated to guess, used val as it is fixed
    var guess: String? = null //The input by the user
    var numberOfTries = 0 //Adds up until the number is guesses

    println("Care to try your mettle in a small game of number guessing?")

    // As long as the guess is not equal to the generated number, we continue the game
    while (guess != numberToGuess) {
        print("The game is guessing a 4 digit number. The number has no repeating digits! Start by writing your guess now: ")
        guess = readLine() //read-in your number
        if (!isValidNumber(guess)) { //checks if the user input is a valid 4 digit number without repeats
            println("Kindly enter a 4 digit number without repeating digits as your guess! ")
            continue
        }
        numberOfTries++ //raise number of tries by one
        val (n, m) = compareNumbers(numberToGuess, guess!!) //get feedback for your guess
        println("$n:$m")
    }

    println("With only $numberOfTries tries, you guessed the number $numberToGuess") //When number is correct and we exit while loop, then we have won
}

// to randomly generate the number
fun generateNumber(): String {
    var numberToGuess = "";
    val digits = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    //select a random number based on a randomized number for index, do it 4 times
    repeat(4)
    {
        val index = Random.nextInt(digits.size) //random index
        numberToGuess += digits[index] //add number to our numberToGuess variable
        digits.removeAt(index) //remove passed digit to avoid repeating numbers
    }

    return numberToGuess
}

//compare both numbers
fun compareNumbers(numberToGuess: String, guess: String): Pair<Int, Int> { //pair means pairing up two values
    var (n, m) = 0 to 0 //n will be the number of correctly guessed digits regardless of position, m will say how many correct numbers on correct positions are in the guess
    val digits = mutableSetOf<Char>()

    //check position for m number
    for (i in 0..3) { //range is 4 steps long
        if (guess[i] == numberToGuess[i]) { //check for exact match in position and digit
            m++
        }
        digits.add(numberToGuess[i])
    }

    //check if digit is correct for n
    for (i in 0..3) { //again 4 positions/steps
        if (guess[i] in digits) { //if the current number is found in digits, then raise n by one
            n++
        }
    }
    return Pair(n , m)
}

//check if input number is valid
fun isValidNumber(number: String?): Boolean {
    if (number == null || number.length != 4) { //we test if input is null or if length is not correct
        return false
    }

    val digits = mutableSetOf<Char>() //empty set of chars
    for (digit in number) {
        //Check, if we have a false digit or if digit is already used
        if (digit !in '0'..'9' || digit in digits) {
            return false
        }
        digits.add(digit)
    }
    return true
}