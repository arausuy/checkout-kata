package io.arausuy.checkout.model

import io.arausuy.checkout.model.Item.UnknownItemException
import org.scalatest.{Matchers, WordSpecLike}


class ItemSpec extends WordSpecLike with Matchers {

  "Items" must {
    "inform when an an attempt on creating a non-existing item" in {
      val attempt = Item("G", BigDecimal(1.0))
      attempt.isLeft shouldBe true
      attempt.left.get shouldBe a[UnknownItemException]
    }

    "create an A" in {
      val attempt = Item("A", BigDecimal(1.0))
      val anA = A(BigDecimal(1.0))
      attempt.isRight shouldBe true
      attempt.right.get shouldBe anA
      attempt.right.get.itemType shouldBe "A"
    }
    "create a B" in {
      val attempt = Item("B", BigDecimal(1.0))
      val b = B(BigDecimal(1.0))
      attempt.isRight shouldBe true
      attempt.right.get shouldBe b
      attempt.right.get.itemType shouldBe "B"
    }
    "create a C" in {
      val attempt = Item("C", BigDecimal(1.0))
      val c = C(BigDecimal(1.0))
      attempt.isRight shouldBe true
      attempt.right.get shouldBe c
      attempt.right.get.itemType shouldBe "C"
    }
    "create a D" in {
      val attempt = Item("D", BigDecimal(1.0))
      val d = D(BigDecimal(1.0))
      attempt.isRight shouldBe true
      attempt.right.get shouldBe d
      attempt.right.get.itemType shouldBe "D"
    }



  }



}
