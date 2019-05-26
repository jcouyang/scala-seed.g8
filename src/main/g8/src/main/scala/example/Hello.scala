package example

import cats.effect._
import cats.syntax.apply._
import cats.data.Kleisli

object Hello extends IOApp {
  def run(args: List[String]) = {
    object interp extends HasConfig
    Greeting.provide(interp) *>
      IO(ExitCode.Success)
  }
}

object Greeting {
  lazy val logger = org.log4s.getLogger
  def provide =
    for {
      config <- read(_.config)
      _ <- Kleisli.liftF(IO(logger.info("Hello " + config.name)))
    } yield ()
}

case class Config(name: String)
trait HasConfig {
  def config: IO[Config] = IO(Config("$name$"))
}
