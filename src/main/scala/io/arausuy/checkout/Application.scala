package io.arausuy.checkout

import io.arausuy.checkout.model.Item
import io.arausuy.checkout.reader.PriceReader

import scala.io.StdIn

object Application {

  def main(args: Array[String]): Unit = {
    println("Please enter your pricing:")
    val pricingIn = StdIn.readLine()

    val pricingMap = PriceReader.createPriceMap(pricingIn)
    val specialPricingMap = PriceReader.createSpecialPricingMap(pricingIn)

    val checkout = new Checkout(pricingMap, specialPricingMap)
    val itemFactory = Item.apply(pricingMap) _
    scan(checkout, itemFactory)
  }

  def scan(checkout: Checkout,
           itemFactory: (String) => Either[Throwable, Item],
           items: List[Item] = List.empty[Item]): Unit = {

    if (items.isEmpty) {
      println("End checkout session with \"CHECKOUT\"\nReady to start scanning: ")
    }

    val itemIn = StdIn.readLine()

    itemIn match {
      case "CHECKOUT" =>
        println(s"Final total: ${checkout.calculateTotal(items)}")
        Unit
      case a =>
        val item = itemFactory(a) match {
          case Right(r) =>
            val newItems = r :: items
            println(s"Current total: ${checkout.calculateTotal(newItems)}")
            scan(checkout, itemFactory, newItems)
          case Left(l) =>
            println(s"Failed to add item $itemIn because $l")
            scan(checkout, itemFactory, items)

        }

    }

  }

}
