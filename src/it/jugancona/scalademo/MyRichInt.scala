package it.jugancona.scalademo

case class MyInt(val i : Int) {
    
	private def factorial(a : Int) : Int = a match {
        case 0 => 1
        case n => (n) * factorial(n-1)
    }
    
    // definizione dell'operatore
    def ! = factorial(i)
    
    override def toString = i.toString
}

object MyInt {
    implicit def intToMyInt(x : Int) = MyInt(x)
    implicit def myIntToInt(x : MyInt) = x.i
}
import MyInt._

object Factorial {
	
    def main(args: Array[String]): Unit = {
     
    	val a = 5
        val aFact = a!
        
        println("factorial of " + a + " is " + aFact)

  }
}