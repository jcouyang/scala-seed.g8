import cats.data.Kleisli
import cats.effect.IO

package object example {
  type Interpreter = HasConfig
  type Pub[A] = Kleisli[IO, Interpreter, A]
  def read[A](f: Interpreter => IO[A]) = Kleisli[IO, Interpreter, A](f)
}
