package io.arausuy.checkout

import io.arausuy.checkout.model.{Item, SpecialPricing}


class Checkout(pricingRules: Map[String, SpecialPricing], basket: List[Item]) {

  def calculateTotal() = {
    basket.groupBy(i => i.itemType).map{i =>
      val key = i._1
      val items = i._2
      pricingRules.get(key) match {
        case Some(sp) => sp.calculateSubtotal(items)
        case None => items.map(f => f.price).sum
      }
    }.sum
  }

}
