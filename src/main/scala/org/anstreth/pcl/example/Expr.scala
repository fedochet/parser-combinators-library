package org.anstreth.pcl.example

trait Expr {
  def eval(): Int
}

case class Number(n: Int) extends Expr {
  override def eval(): Int = n
}

case class Mul(left: Expr, right: Expr) extends Expr {
  override def eval(): Int = left.eval() * right.eval()
}

case class Plus(left: Expr, right: Expr) extends Expr {
  override def eval(): Int = left.eval() + right.eval()
}