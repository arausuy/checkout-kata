package io.arausuy.checkout.model

sealed trait Item {
  val itemType: String = this.getClass().getSimpleName.replace("$", "")
}

case object A extends Item
case object B extends Item
case object C extends Item
case object D extends Item



object Item {

  def apply(priceMap: Map[String, BigDecimal])(itemName:String): Either[Throwable, Item] = {
    if(priceMap.get(itemName.toUpperCase).isDefined) {
    itemName.toUpperCase match {
      case "A" => Right(A)
      case "B" => Right(B)
      case "C" => Right(C)
      case "D" => Right(D)
      case _ => Left(UnknownItemException("Unable to find item but pricing exists!"))
    }
  } else {
    Left(PricingMissingException(s"Missing Pricing for ${itemName.toUpperCase}"))
    }
  }

  case class UnknownItemException(msg: String) extends Exception(msg)
  case class PricingMissingException(msg: String) extends Exception(msg)

}