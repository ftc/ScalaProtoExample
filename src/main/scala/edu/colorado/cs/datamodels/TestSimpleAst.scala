package edu.colorado.cs.datamodels

import foo.Expr.Kind
import foo.{Expr, Add, Sub, IntConst}
/**
 * Created by s on 7/29/15.
 */
abstract class ExprS
case class IntConstS(i: Int) extends ExprS
case class AddS(left: ExprS, right: ExprS) extends ExprS
case class SubS(left: ExprS, right: ExprS) extends ExprS

object TestSimpleAst {
  def main(args: Array[String]): Unit = {
    val someAst = s_to_proto(AddS(IntConstS(7), SubS(IntConstS(3), IntConstS(2))))
    val bytes = someAst.toByteArray
    val deserializedAst = proto_to_s(Expr.parseFrom(bytes))
    println(deserializedAst)
  }
  def s_to_proto(ast: ExprS): Expr = {
    ast match {
      case IntConstS(i) => constructInt(i)
      case AddS(left, right) => constructAdd(s_to_proto(left), s_to_proto(right))
      case SubS(left, right) => constructAdd(s_to_proto(left), s_to_proto(right))
    }
  }
  def proto_to_s(e: Expr): ExprS = {
    e.kind match{
      case Kind.IntConst(IntConst(Some(i))) => IntConstS(i)
      case Kind.Add(Add(Some(left),Some(right))) => AddS(proto_to_s(left), proto_to_s(right))
      case Kind.Sub(Sub(Some(left),Some(right))) => SubS(proto_to_s(left), proto_to_s(right))
      case _ => throw new UnsupportedOperationException
    }
  }


  def constructInt(i: Int): Expr = {
    Expr(Kind.IntConst(IntConst(Some(i))))
  }
  def constructAdd(left: Expr, right: Expr): Expr = {
    Expr(Kind.Add(Add(Some(left),Some(right))))
  }
  def constructSub(left: Expr, right: Expr): Expr = {
    Expr(Kind.Sub(Sub(Some(left),Some(right))))
  }


}
