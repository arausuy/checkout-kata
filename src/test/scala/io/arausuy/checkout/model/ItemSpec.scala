package io.arausuy.checkout.model

import io.arausuy.checkout.model.Item.{PricingMissingException, UnknownItemException}
import org.scalatest.{Matchers, WordSpecLike}


class ItemSpec extends WordSpecLike with Matchers {

  val priceMap1 = Map[String, BigDecimal]("A"->1, "B"->2, "C"->3,"D"->4)
  val priceMap2 = Map[String, BigDecimal]("A"->1, "B"->2, "C"->3,"G"->5)

  "Items" must {
    "inform when an an attempt on creating a non-existing item" in {
      val attempt = Item("G", priceMap2)
      attempt.isLeft shouldBe true
      attempt.left.get shouldBe a[UnknownItemException]
    }

    "inform when an an attempt on creating a non-priced item" in {
      val attempt = Item("D", priceMap2)
      attempt.isLeft shouldBe true
      attempt.left.get shouldBe a[PricingMissingException]
    }

    "create an A" in {
      val attempt = Item("A", priceMap1)
      val anA = A
      attempt.isRight shouldBe true
      attempt.right.get shouldBe anA
      attempt.right.get.itemType shouldBe "A"
    }
    "create a B" in {
      val attempt = Item("B", priceMap1)
      val b = B
      attempt.isRight shouldBe true
      attempt.right.get shouldBe b
      attempt.right.get.itemType shouldBe "B"
    }
    "create a C" in {
      val attempt = Item("C",priceMap1)
      val c = C
      attempt.isRight shouldBe true
      attempt.right.get shouldBe c
      attempt.right.get.itemType shouldBe "C"
    }
    "create a D" in {
      val attempt = Item("D", priceMap1)
      val d = D
      attempt.isRight shouldBe true
      attempt.right.get shouldBe d
      attempt.right.get.itemType shouldBe "D"
    }
  }
}
