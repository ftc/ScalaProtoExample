package edu.colorado.cs.datamodels
import foo.AMessage
/**
 * Created by s on 7/29/15.
 */
object TestSimpleAst {
  def main(args: Array[String]): Unit = {
    val thing2 = "lolwut?"
    val thing1 = 31337
    val in = AMessage.fromFieldsMap(Map(1->Some(thing1)))
    val bytes = in.toByteArray
    val out = AMessage.parseFrom(bytes)
    if(out.kind.isA){
      println(out.getA)
    }else{
      println(out.getB)
    }

  }

}
