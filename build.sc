import mill._, scalalib._, scalajslib._, define.Task
import ammonite.ops._

trait CommonJsModule extends ScalaJSModule {
  def scalaVersion = "2.12.4"
  def scalaJSVersion = "0.6.22"
  import coursier.maven.MavenRepository

  def repositories = super.repositories ++ Seq(
    MavenRepository("https://oss.sonatype.org/content/repositories/releases")
  )

  def ivyDeps = Agg(
    ivy"org.scala-js::scalajs-dom::0.9.4",
    ivy"com.lihaoyi::scalatags::0.6.7"
  )

  def pack() = T.command {
    def js = fastOpt()
    def targ = pwd / "docs" / artifactName()
    cp.over(js.path, targ / "out.js")
    cp.over(js.path / up / "out.js.map", targ / "out.js.map")
    js
  }
}

object dyn extends CommonJsModule

object three extends CommonJsModule
