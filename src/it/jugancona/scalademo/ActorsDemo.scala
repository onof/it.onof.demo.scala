package it.jugancona.scalademo

import scala.actors._
import scala.actors.Actor._

object ActorsDemo {
	
	def main(args: Array[String]): Unit = {
		val echoActor = actor {
			while (true) { 
				receive {
					case msg =>
						println("received message: "+ msg)
				}
			}
		}	
		
		echoActor ! "Hello world!"
		echoActor ! "Hi guys"
		echoActor ! "See you later"
	}
}
