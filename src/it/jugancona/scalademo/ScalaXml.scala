package it.jugancona.scalademo

// Meeting
class Meeting(val description : String, val subjects : List[String]) {  
	// Serializza
	def toXml = <meeting description={description} count={subjects.length.toString}>
	  				{ subjects.map( s => <subject>{s}</subject> ) }
                </meeting>
	  				
	// per l'output su console
	override def toString = "Meeting " + description + " has the following subjects: " + subjects.mkString(", ")
}

// Companion singleton object
object Meeting {
	def fromXml(node : scala.xml.Node) : Meeting =
		new Meeting( (node \ "@description").text, 
				(node \\ "subject").toList.map(s => s.text) )
}

object ScalaXml {
  def main(args: Array[String]): Unit = { 
	  
	  val meeting = new Meeting("Jug Ancona", List("Scala", "Lift"))
	  println(meeting.toXml);
	   
	  // mostra che l'oggetto è lo stesso
	  println(Meeting.fromXml(meeting.toXml))
	   
	  // dalla serializzazione
	  val serialized = <meeting description="JavaDay in Rome" count="3">
		  				    <subject>RestFul</subject>
		  					<subject>MongoDb</subject>
		  					<subject>Java 7</subject>
		  			   </meeting>
	 	  
	  val javaday = Meeting.fromXml(serialized)
	  println(javaday)
  }
}
