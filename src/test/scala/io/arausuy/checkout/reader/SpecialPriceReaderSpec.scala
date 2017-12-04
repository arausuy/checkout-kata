package io.arausuy.checkout.reader

import io.arausuy.checkout.model.SpecialPricing
import org.scalatest.{Matchers, WordSpecLike}

class SpecialPriceReaderSpec extends WordSpecLike with Matchers {

  "Special Price Reader" must {
    "read a correctly formed offer list" in {
      val offerList = "d,34,2 for 50~c,50,3 for 100"
      val parsedMap = SpecialPriceReader.createSpecialPricingMap(offerList)
      parsedMap.size shouldBe 2
      parsedMap("c") shouldBe SpecialPricing(3, BigDecimal(100), BigDecimal(50))
    }

    "not have any special offers for a malformed offerlist" in {
      val offerList = "d,34'2 for 50~c,50-3 for 100"
      val parsedMap = SpecialPriceReader.createSpecialPricingMap(offerList)
      parsedMap.size shouldBe 0
    }

    "parse whatever offers that fit" in {
      val offerList = "d,34,2 for 50~c,50,3 f0r 100"
      val parsedMap = SpecialPriceReader.createSpecialPricingMap(offerList)
      parsedMap.size shouldBe 1
      parsedMap("d") shouldBe SpecialPricing(2, BigDecimal(50), BigDecimal(34))
    }



  }

}
