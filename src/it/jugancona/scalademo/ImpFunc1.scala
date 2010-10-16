package it.jugancona.scalademo

class ImpFunc1(elements : List[String]) {
	
	// Stampa gli elementi (imperativo)
	def printElements() {
		var i = 0;
		while(i < elements.length) {
			println("Element " + i + " = " + elements.apply(i));
			i = i + 1
		}
	}
	
	// Un po' più functional oriented
	def printElementsFunc() {
		val toPrint = elements.indices.map(i => "Element " + i + " = " + elements(i));
		toPrint.foreach(println(_))
	}
	
	// con tuple (1)
	def printElementsFuncTupled1() {
		val tuples = elements.indices.map(i => (i, elements(i)))
		val formatted = tuples.map(t => "Element " + t._2 + " = " + t._1)
		
		for( row <- formatted )
		  println(row)
	}
	
    // con tuple (2)
	def printElementsFuncTupled() {
		val formatted = elements.zip(elements.indices)
							.map(t => "Element " + t._2 + " = " + t._1 )
		formatted.foreach(println(_))
	}
	
	// SENZA side-effects
	def getStrings = elements.zipWithIndex
		.map(t => "Element " + t._2 + " = " + t._1 )
		
	// oppure
	def doSomethingWithStrings(doSomething : String => Unit) =
		elements.zipWithIndex
				.map(t => "Element " + t._2 + " = " + t._1 )
				.foreach(doSomething(_))
	
	// oppure, con gli indici
	def doSomethingWithIndexAndValue(doSomeThing : (String, Int) => Unit) =
		elements.zipWithIndex
				.foreach(a => doSomeThing(a._1, a._2))
				
	// oppure, con tuple
	def doSomethingWithTuples(doSomeThing: ((String, Int)) => Unit) =
		elements.zipWithIndex
				.foreach(doSomeThing(_))
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
		
		println("Con tuple")
		test.printElementsFuncTupled()
		println()
		
		println("Meglio")
		test.getStrings.foreach(a => println(a))
		
		println("Oppure:")
		test.doSomethingWithStrings(println(_))
		
		println("Ancora:")
		test.doSomethingWithIndexAndValue(
				(v, i) => println("Element " + i + " = " + v)
				)
				
		println("e, con tuple:")
		test.doSomethingWithTuples(
				tuple => println("Element " + tuple._2 + " = " + tuple._1)
				)
	}
}
