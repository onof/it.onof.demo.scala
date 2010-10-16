package it.jugancona.scalademo

abstract class Expr
case class Var(name: String) extends Expr                                { override def toString = name }
case class Number(num: Double) extends Expr                              { override def toString = num.toString }
case class UnOp(operator: String, arg: Expr) extends Expr                { override def toString = "(" + operator + arg + ")" }
case class BinOp(left: Expr, operator: String, right: Expr) extends Expr { override def toString = "(" + left + operator + right + ")" }

object Simplifier {
	// Semplifica il primo livello dell'espressione
	private def simplifyTop(expr: Expr) : Expr = expr match {
		case UnOp("-", UnOp("-", e))  => e // Doppia negazione
		case BinOp(e, "+", Number(0)) => e // Aggiunta zero
		case BinOp(e, "*", Number(1)) => e // Moltiplicazione per 1
		case _ => expr
	}
	
	// semplifica
	def simplify(expr: Expr) : Expr = simplifyTop(
			expr match {
				case BinOp(a, op, b) => BinOp(simplify(a), op, simplify(b))
				case UnOp(op, a) => UnOp(op, simplify(a))
				case e => e
			}
		)
}

class Evaluator(val context : Map[String, Double]) {
	
	def evaluate(expr: Expr) : Double = expr match {
		case BinOp(a, "+", b) =>  evaluate(a) + evaluate(b)
		case BinOp(a, "-", b) => evaluate(a) - evaluate(b)
		case BinOp(a, "*", b) => evaluate(a) * evaluate(b)
		case UnOp("-", a) => - evaluate(a)
		case Number(a) => a
		case Var(a) => context(a)
	}
	
	def apply(expr : Expr) = evaluate(expr)
}

object EvaluatorDemo {
	def main(args: Array[String]): Unit = {
		
		val expr =
				BinOp(
					BinOp(
						BinOp(
							BinOp(
									Var("variable1"),
									"*", 
									UnOp("-", BinOp(Number(2), "*", Number(1)))),
							"-", 
							Var("variable2")),
							"*", 
							Number(1)),
						"+",
						Number(0))
		
		val simplified = Simplifier simplify expr
		println(expr + " = " + simplified)
		println()
		
		var evaluator = new Evaluator(Map(
				"variable1" -> -5,
				"variable2" -> 2
				))
		
		println("variables = " + evaluator.context)
		println("value of " + simplified + " = " + evaluator(simplified))
	}
}
