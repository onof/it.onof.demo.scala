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
trait ParagraphWriter extends AWriter {
	abstract override def write(s : String) {
		super.write("<p>" + s + "</p>")
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
	  val emph = (new ConsoleWriter with ParagraphWriter with EmphasizedWriter)
	  emph.write("Hello world!")
	  
	  val nonEmph = (new ConsoleWriter with ParagraphWriter)
	  nonEmph.write("Goodbye")
  }
}
