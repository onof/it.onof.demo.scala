package it.jugancona.scalademo
import scala.collection._

object MapAll {
	def main(args : Array[String]) {
		
		val italiaCentrale = Map(
				"Marche" -> "Ancona",
				"Umbria" -> "Perugia",
				"Toscana" -> "Firenze",
				"Emilia Romagna" -> "Bologna"
			)
		
		def format(regions : Map[String, String]) =
			regions
				.map(a => a._1 + " ha capoluogo " + a._2 )
		
		format(italiaCentrale)
			.foreach(println(_))
		
		val italiaInsulare = Map(
				"Sardegna" -> "Cagliari",
				"Sicilia" -> "Palermo"
			)
	
		val capoluogoSardo = 
			if(italiaInsulare.contains("Sardegna"))
				italiaInsulare("Sardegna")
			else
				"sconosciuto"
		println("\nCapoluogo sardo: " + capoluogoSardo)
		
		assert(capoluogoSardo == italiaInsulare.getOrElse("Sardegna", "sconosciuto"))
		
		val italiaCentroInsulare = italiaCentrale ++ italiaInsulare
		println("Italia Centro e Isole: " + italiaCentroInsulare.mkString(", "))
		
		//*****************************
		// Riassegnamento di immutabili
		var numbers = Map(1 -> "uno")
		numbers += (2 -> "due")
		println("\nNumeri" + numbers)
		
		//******************************
		// Mutabili thread-safe
		val stati = new mutable.HashMap[String, String] 
			with mutable.SynchronizedMap[String, String] {
				override def default(key: String) = key + " esiste?"
			}
		stati += ("Italia" -> "Roma")
		stati ++= List("France" -> "Paris", "UK" -> "London")
		
		println(stati.keySet.mkString("\nStati conosciuti: ", ", ", "\n"))
		
		var capitaleDiElbonia = stati("Elbonia")
		println("> Qual è la capitale di Elbonia?")
		println("< " + capitaleDiElbonia)
	}
}
