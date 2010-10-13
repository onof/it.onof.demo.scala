package it.jugancona.scalademo

object FindingInLists {
	
	def main(args: Array[String]) {
		
		val a = List.range(1, 10)
		
		findAndPrint(a, 4)
		findAndPrint(a, 12)
	}
	
	def findAndPrint[T](list: List[T], toFind : T) {
		list.find(a => a == toFind) match {
			case Some(found) => println(found + " ok!")
			case None => println("not found: " + toFind)
		}
	}
}
