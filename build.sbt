name := "rabbitmq-manage"

licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php"))

resolvers += "typesafe" at "http://typesafe.artifactoryonline.com/typesafe/releases/"

scalaVersion := "2.11.5"

libraryDependencies ++= (
  ("com.typesafe.play" %% "play-json" % "2.3.8") ::
  ("com.github.xuwei-k" %% "httpz-native" % "0.2.17" % "test") ::
  Nil
)

scalacOptions ++= (
  "-deprecation" ::
  "-unchecked" ::
  "-Xlint" ::
  "-language:existentials" ::
  "-language:higherKinds" ::
  "-language:implicitConversions" ::
  Nil
)
