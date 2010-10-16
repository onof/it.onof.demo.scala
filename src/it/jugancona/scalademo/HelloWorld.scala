package it.jugancona.scalademo

class StupidPrinter(s : String) {
	println(s)
}

object Hello {
	
	def main(args: Array[String]) {
		
		val hw = new StupidPrinter("Hello World!")
		
	}
}
