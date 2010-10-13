package it.jugancona.scalademo

// Base class for writers
abstract class AWriter {
	def write(s : String) : Unit
}

// write on console
class ConsoleWriter extends AWriter {
	override def write(s : String) {
		println(s)
	}
}

// Enclude in paragraph
trait TagWriter extends AWriter {
	var tag = "p"
	
	abstract override def write(s : String) {
		super.write("<" + tag + ">" + s + "</" + tag + ">")
	}
}

// emphasize string
trait EmphasizedWriter extends AWriter {
	abstract override def write(s : String) {
		val text = s.toList.map(a =>
			a match {
				case '!' => "_!!"
				case _   => "_" + a
			}
		  ).mkString
		super.write(text)
	}
}

object ScalaTraits {
  def main(args: Array[String]): Unit = { 
	  val emph = (new ConsoleWriter with TagWriter with EmphasizedWriter)
	  emph.tag = "h1"
	  emph.write("Hello world!")
	  
	  val nonEmph = (new ConsoleWriter with TagWriter)
	  nonEmph.write("This is some text")
	  
	  val simple = new ConsoleWriter
	  simple.write("this is a comment")
  }
}
