package it.jugancona.scalademo

abstract class Expr
case class Var(name: String) extends Expr                                { override def toString = name }
case class Number(num: Double) extends Expr                              { override def toString = num.toString }
case class UnOp(operator: String, arg: Expr) extends Expr                { override def toString = "(" + operator + arg + ")" }
case class BinOp(left: Expr, operator: String, right: Expr) extends Expr { override def toString = "(" + left + operator + right + ")" }

class Evaluator(val context : Map[String, Double]) {	
	def simplifyTop(expr: Expr) : Expr = expr match {
		case UnOp("-", UnOp("-", e))  => e // Doppia negazione
		case BinOp(e, "+", Number(0)) => e // Aggiunta zero
		case BinOp(e, "*", Number(1)) => e // Moltiplicazione per 1
		case _ => expr
	}
	
	def evaluate(expr: Expr) : Double = expr match {
		case BinOp(a, "+", b) =>  evaluate(a) + evaluate(b)
		case BinOp(a, "-", b) => evaluate(a) - evaluate(b)
		case BinOp(a, "*", b) => evaluate(a) * evaluate(b)
		case UnOp("-", a) => - evaluate(a)
		case Number(a) => a
		case Var(a) => context(a)
	}
}

object EvaluatorDemo {
	def main(args: Array[String]): Unit = {
		// ( variable1 * (-2) ) - variable2 + 0
		val expr = BinOp(
					BinOp(
							BinOp(
									Var("variable1"),
									"*", 
									UnOp("-", Number(2))),
							"-", 
							Var("variable2")),
					"+", 
					Number(0)
				)

		var evaluator = new Evaluator(Map(
				"variable1" -> -5,
				"variable2" -> 2
				))
		
		val simplified = evaluator simplifyTop expr
		println(expr + " = " + simplified)
		
		println()
		println("variables = " + evaluator.context)
		
		println("value of " + simplified + " = " + evaluator.evaluate(simplified))
	}
}
