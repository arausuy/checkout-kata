package io.arausuy.checkout.reader

import io.arausuy.checkout.model.SpecialPricing

import scala.util.Try

object PriceReader {
  val pricingDelimiter = '~'
  val fieldDelimiter = ','

  def createSpecialPricingMap(pricing: String) = {
    pricing.split(pricingDelimiter).flatMap { line =>
      if (line.count(_.equals(fieldDelimiter)) == 2) {
        val offer = line.split(fieldDelimiter)
        createSpecialPrice(offer(0), offer(2), offer(1))
      } else {
        None
      }
    }.toMap

  }

  private def createSpecialPrice(offerFor: String, priceOffer: String, originalPricing: String) = {
    val splits = priceOffer.toLowerCase.replace(" ", "").split("for")
    //Unacceptably lazy approach
    Try(offerFor -> SpecialPricing(splits(0).toInt, BigDecimal(splits(1)), BigDecimal(originalPricing))).toOption
  }

}
