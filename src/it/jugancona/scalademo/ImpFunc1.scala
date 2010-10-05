package it.jugancona.scalademo

class ImpFunc1(elements : List[String]) {
	
	// Stampa gli elementi (imperativo)
	def printElements() {
		var a = 0;
		while(a < elements.length) {
			println("Element " + a + " = " + elements(a));
			a = a + 1
		}
	}
	
	// Un po' più functional oriented
	def printElementsFunc() {
		val toPrint = elements.indices.map(a => "Element " + a + " = " + elements(a));
		toPrint.foreach(println(_))
		
		// // Oppure:
		// for( row <- toPrint )
		//	println(row)
	}
	
	def tupled = elements.indices
						.map( a => (a, elements(a)))
	
    // Separa logica da formattazione
	def printElementsFuncTupled() {
		val formatted = tupled
							.map(t => "Element " + t._1 + " = " + t._2 )
		formatted.foreach(println(_))
	}
	
	// Soluzione ideale: SENZA side-effects
	def printString = tupled
		.map(t => "Element " + t._1 + " = " + t._2 )
		.mkString("\n")
}

object ImpFuncTest {
	
	def main(args : Array[String]) {
		val test = new ImpFunc1(List("Jug", "Ancona", "Scala"))
		
		println("Imperativo")
		test.printElements()
		println()
		
		println("Un po' più funzionale")
		test.printElementsFunc()
		println()
		
		println("Ancora un po' più funzionale con tuple")
		test.printElementsFuncTupled()
		println()
		
		println("Meglio")
		println(test.printString)
	}
}