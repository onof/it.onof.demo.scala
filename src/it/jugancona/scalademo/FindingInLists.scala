package it.jugancona.scalademo

object FindingInLists {
	
	def main(args: Array[String]) {
		
		val a = List.range(1, 10)
		
		println(findAndMakeString(a, 4))
		println(findAndMakeString(a, 12))
	}
	
	def findAndMakeString[T](list: List[T], toFind : T) = {
		val found = list.find(a => a == toFind)
		
		found match {
			case Some(found) => found + " ok!"
			case None => "not found: " + toFind
		}
	}
}
