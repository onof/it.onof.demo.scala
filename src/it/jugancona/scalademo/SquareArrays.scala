package it.jugancona.scalademo
import scala.math._
import scala.collection.JavaConversions._

object SquareArrays {

  def main(args: Array[String]): Unit = {
	  println("Hi, here's Scala")
	  
	  // odd integers from 1 to 19 and 30
	  val input = ((1 to 20) filter(_ % 2 == 1)) :+ 30
	  println("input: " + input)
	   
	  // map to doubled values
	  val flatten = input.flatMap( List.fill(2)(_) )
	  println("flatten: " + flatten)
	  // println("flatten2: " + input.map(List.fill(2)(_)).flatten)
	  
	  // compute the square 
	  val square1 = for(a <- flatten) yield pow(a, 2)
	  println("square1: " + square1)
	  
	  val square2 = flatten.map(pow(_, 2))
	  println("square2: " + square2)
	  
	  println("Are equals? " + square1.equals(square2))
	  
	  val squareSum = (0.0 /: square1)(_ + _)
	  println("Square sum: " + squareSum)
	  
	  
	  /*
	  println("Square sum: " + 
	 		  (0.0 /: 
	 			  input
	 			  	.flatMap(List.fill(2)(_))
	 			  	.map(pow(_, 2)))
	 			  (_ + _))
	  */
	  
	  // Java conversion
	  val buffer : java.util.Collection[Double] = square1
	  println("Java buffer: " + buffer)
  }
}
