import com.trueaccord.scalapb.{ScalaPbPlugin => PB}
lazy val commonSettings = Seq(
  organization := "edu.colorado",
  version := "0.1.0",
  scalaVersion := "2.10.2",
  name := "Example",
  javaOptions += "-Xmx32G"
)



PB.protobufSettings

