package io.arausuy.checkout

import io.arausuy.checkout.model.{A, D, SpecialPricing}
import org.scalatest.{Matchers, WordSpecLike}

class CheckoutSpec extends WordSpecLike with Matchers {

  "Checkout" must {
    "calculate total of basket items" in {
      val checkout = new Checkout(Map.empty[String, SpecialPricing], List(A(15), A(15), A(15), D(24), D(24)))
      checkout.calculateTotal() shouldBe 93
    }
    "calculate total of basket items with offers" in {
      val specialOffer = SpecialPricing(2, 20, 15)
      val checkout = new Checkout(Map("A" -> specialOffer), List(A(15), A(15), A(15), D(24), D(24)))
      checkout.calculateTotal() shouldBe 83
    }

  }

}
