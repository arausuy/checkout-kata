package io.arausuy.checkout.model


case class SpecialPricing(quantity: Int, specialPricing: BigDecimal, originalPricing: BigDecimal) {

  def calculateSubtotal(items: List[Item]): BigDecimal = {
    val qualifyingItems = items.size / quantity
    val nonQualifyingItems = items.size % quantity
    (qualifyingItems * specialPricing) + (nonQualifyingItems * originalPricing)
  }

}
