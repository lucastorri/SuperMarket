package supermarket.payment

trait PriceConverter {
  implicit def intTuple2Price(t: (Int, Int)) = new Price(t._1, t._2)
}
