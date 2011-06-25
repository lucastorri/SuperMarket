package supermarket.payment

import supermarket.ShoppingCart
trait PriceConverter {
  implicit def intTuple2Price(t: (Int, Int)) = new Price(t._1, t._2)

  def whenCheckingOut = new {
    def in(counter: CheckoutCounter) = new {
      def theTotalPriceFor(cart: ShoppingCart) = {
        counter.checkout(cart).getTotalPrice
      }
    }
  }

}

object PriceConverter extends PriceConverter
