package io.arausuy.checkout.reader

import io.arausuy.checkout.model.SpecialPricing

import scala.util.Try

object SpecialPriceReader {
  val pricingDelimiter = '~'
  val fieldDelimiter = ','

  def createSpecialPricingMap(pricing: String) = {
    pricing.split(pricingDelimiter).flatMap { line =>
      if (line.count(_.equals(fieldDelimiter)) == 2) {
        val offer = line.split(fieldDelimiter)
        createSpecialPrice(offer(0), offer(2))
      } else {
        None
      }
    }.toMap

  }

  private def createSpecialPrice(offerFor: String, priceOffer: String) = {
    val splits = priceOffer.toLowerCase.replace(" ", "").split("for")
    Try(offerFor -> SpecialPricing(splits(0).toInt, BigDecimal(splits(1)))).toOption //Unacceptably lazy approach
  }

}
