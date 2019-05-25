package example

import org.specs2.mutable.Specification
import cats.effect._

class HelloSpec extends Specification {
  "it should run" >> {
    object interp extends HasConfig {
      override def config: IO[Config] = IO(Config("Test User"))
    }
    Greeting.provide(interp).unsafeRunSync() must_== (())
  }
}
