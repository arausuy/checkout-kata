package io.arausuy.checkout

import io.arausuy.checkout.model.{A, D, SpecialPricing}
import org.scalatest.{Matchers, WordSpecLike}

class CheckoutSpec extends WordSpecLike with Matchers {

  "Checkout" must {
    "calculate total of basket items" in {
      val priceMap = Map[String, BigDecimal]("A" -> 15, "D" -> 24)

      val checkout = new Checkout(priceMap, Map.empty[String, SpecialPricing])
      checkout.calculateTotal(List(A, A, A, D, D)) shouldBe 93
    }
    "calculate total of basket items with offers" in {
      val priceMap = Map[String, BigDecimal]("A" -> 15, "D" -> 24)

      val specialOffer = SpecialPricing(2, 20, 15)
      val checkout = new Checkout(priceMap, Map("A" -> specialOffer))
      checkout.calculateTotal(List(A, A, A, D, D)) shouldBe 83
    }
    "calculate total of basket items with offers regardless of order" in {
      val priceMap = Map[String, BigDecimal]("A" -> 15, "D" -> 24)

      val specialOffer = SpecialPricing(2, 20, 15)
      val checkout = new Checkout(priceMap, Map("A" -> specialOffer))
      checkout.calculateTotal(List(A, D, D, A, A)) shouldBe 83
    }
    "calculate total of basket items with multiple offers" in {
      val priceMap = Map[String, BigDecimal]("A" -> 15, "D" -> 24)

      val specialOffer = SpecialPricing(2, 20, 15)
      val specialOffer2 = SpecialPricing(4, 32, 10)

      val checkout = new Checkout(priceMap, Map("A" -> specialOffer, "D" -> specialOffer2))
      checkout.calculateTotal(List(A, D, D, A, A, D, D)) shouldBe 67
    }

  }

}
