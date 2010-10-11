package it.jugancona.scalademo

import scala.actors._
import scala.actors.Actor._

case class Stop
case class End
case class Start(consumer: Actor)

object Producer extends Actor {
	def act() {
		loop {
			react {
				case Start(consumer: Actor) =>
					consumer ! List(1, 2, 3)
					consumer ! End
					this ! Stop
				case Stop => 
					exit()
			}
		}
	}
}

object SquareCalculator extends Actor {
	
	def act() {
	  loop {
		react {
			case l : List[Int] =>
				Echo ! "received: " + l
				Echo ! "computed: " + l.map(a => a*a)
			case End =>
				exit()
		}
	  }
	}
}

object Echo extends Actor {
	def act() {
	  loop {
		react {
			case End =>
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
		
		Echo ! "Hello world!"
		
		
		Producer ! Start(SquareCalculator)
	}
}

