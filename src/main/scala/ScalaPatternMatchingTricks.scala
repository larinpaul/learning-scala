/**
 * 8 useful pattern matching tricks
 */

object ScalaPatternMatchingTricks {

  // We can use PM in one of two ways:

  // Way 1 - switch on steroids
  val aNumber = 44
  val ordinal = aNumber match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => "aNumber" + "th"
  }

  // Way 2 - case class deconstruction
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 34)

  val bobGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
  }

  // Trick #1 - list extractors
  val numberList = List(1,2,3,42)

  val mustHaveThree = numberList match {
    case List(_, _, 3, somethingElse) => s"List has 3rd element 3, so the 4th element is $somethingElse"
  }

  // Trick #2 - Haskell-like prepending
  val startsWithOne = numberList match {
    case 1 :: tail => s"list starts with one, tail os $tail"
  }

  def process(aList: List[Int]) = aList match {
    case Nil => "list is empty"
    case head :: tail => s"ist starts with $head, tail is $tail"
  }

  // Trick #3 - vararg pattern
  val dontCareAboutTheRest = numberList match {
    case List(_, 2, _*) => "I only care about the second number being 2"
  }

  // Trick #4 - other infix patterns
  val mustEndWithTheMeaningOfLife = numberList match {
    case List(1,2,_) :+ 42 => "that's right, I have a meaning"
  }

  val mustEndWithTheMeaningOfLife2 = numberList match {
    case List(1, _*) :+ 42 => "I don't care how long the list is, I just want it to end in 42"
  }

  // Trick #5 - type specifiers
  def gimmeAValue(): Any = 45

  val gimmeTheType = gimmeAValue() match {
    case _: String => "I have a String"
    case _: Int => "I have an int"
    case _ => "Something else"
  }

  // Trick #6 - name binding
  def requestMoreInfo(p: Person): String = s"The person ${p.name} is a good person."

  val bobsInfo = bob match {
//    case Person(n, a) => s"$n's info: ${requestMoreInfo(Person(n, a))}." // Meh approach
    case p @ Person(n, a) => s"$n's info: ${requestMoreInfo(p)}"
  }

  // Trick #7 - conditional clauses (guards)
  val ordinal2 = aNumber match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case n if n % 10 == 1 => n + "st"
    case n if n % 10 == 2 => n + "nd"
    case n if n % 10 == 3 => n + "rd"
    case _ => aNumber + "th"
  }

  // Trick #8 - alternative patterns
  val myOptimalList = numberList match {
//    case List(1, _*) => "I like this list" // meh approach
//    case List(_, _, 3, _*) => "I like this list" // meh approach
    case List(1, _*) | List(_, _, 3, _*) => "I like this list"
    case _ => "I dislike this list"
  }

  def main(args: Array[String]): Unit = {
    println(myOptimalList)
  }



}
