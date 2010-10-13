package it.jugancona.scalademo

import scala.actors._
import scala.actors.Actor._
import scala.collection.mutable.HashSet
import scala.math._

case class Stop
case class Start(consumer: Actor)
case class WaitForStop(observer: Actor)

object Producer extends Actor {
	def act() {
		loop {
			react {
				case Start(consumer: Actor) =>
					consumer ! List.range(1, 300, 15)
					consumer ! Stop
					this ! Stop
				case Stop => 
					exit()
			}
		}
	}
}

object SquareCalculator extends Actor {
	val observers = new HashSet[Actor]
	
	def act() {
	  loop {
		react {
			case l : List[Int] =>
				Echo ! this + " ha ricevuto: " + l
				Echo ! "e ha calcolato: " + l.map(a => pow(a, 2.0))

			case Stop =>
				observers.toList.foreach(a => a.forward(Stop))
				exit()
			
			case WaitForStop(a) =>
				observers += a
			}
	  }
	}
	
	override def toString = "Calcolatore asincrono"
}

object Echo extends Actor {
	def act() {
		
	  SquareCalculator ! WaitForStop(this)
		
	  loop {
		react {
			case Stop =>
				exit()
			case a =>
				println(a.toString)
		}
	  }
	}
}

object ActorsDemo {
	
	def main(args: Array[String]): Unit = {
		
		SquareCalculator.start
		Producer.start
		Echo.start
		
		Echo ! "This is an actor test"
		
		Producer ! Start(SquareCalculator)
	}
}

