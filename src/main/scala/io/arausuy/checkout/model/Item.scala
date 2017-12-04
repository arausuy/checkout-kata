package io.arausuy.checkout.model

sealed trait Item {
  val itemType: String = this.getClass().getSimpleName
  val price: BigDecimal
}

case class A(price: BigDecimal) extends Item
case class B(price: BigDecimal) extends Item
case class C(price: BigDecimal) extends Item
case class D(price: BigDecimal) extends Item



object Item {

  def apply(itemName: String, price: BigDecimal): Either[Throwable, Item] = {
    itemName.toUpperCase match {
      case "A" => Right(A(price))
      case "B" => Right(B(price))
      case "C" => Right(C(price))
      case "D" => Right(D(price))
      case _ => Left(UnknownItemException("Unable to create object"))
    }
  }

  case class UnknownItemException(msg: String) extends Exception(msg)

}