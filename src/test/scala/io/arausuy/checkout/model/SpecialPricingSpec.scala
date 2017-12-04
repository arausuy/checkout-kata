package io.arausuy.checkout.model

import org.scalatest.{Matchers, WordSpecLike}

class SpecialPricingSpec extends WordSpecLike with Matchers {

  "Special Pricing" must {
      "calculate the subtotal for an item with a qualifiying special offer" in {
        val sP = SpecialPricing(2, 20, 15)

        val item = A
        val tmpList = List(item,item,item)
        sP.calculateSubtotal(tmpList) shouldBe 35
      }

    "calculate the subtotal for an item that does not qualify for a special offer" in {
        val sP = SpecialPricing(9, 20, 15)

        val item = A
        val tmpList = List(item,item,item)
        sP.calculateSubtotal(tmpList) shouldBe 45
      }



  }

}
