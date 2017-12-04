# Checkout Kata
Implement the code for a supermarket checkout that calculates the total price of a number of
items.

In a normal supermarket, things are identified using Stock Keeping Units, or SKUs.
In our store, we’ll use individual letters of the alphabet (A, B, C, and so on as the SKUs). Our goods
are priced individually. In addition, some items are multi-priced: buy n of them, and they’ll cost
you y. For example, item ‘A’ might cost 50 pence individually, but this week we have a special
offer: buy three ‘A’s and they’ll cost you £1.30. In fact this week’s prices are:

| Item | Unit Price | Special Price |
|------|------------|---------------|
| A    | 50         | 3 for 130     |
| B    | 30         | 2 for 45      |
| C    | 20         |               |
| D    | 15         |               |


Our checkout accepts items in any order, so that if we scan a B, an A, and another B, we’ll
recognise the two B’s and price them at 45 (for a total price so far of 95). Because the pricing
changes frequently, we need to be able to pass in a set of pricing rules each time we start
handling a checkout transaction.


## To Run

`sbt run`

You will then be prompted to enter your pricing. There is a specific format for this:

`a,50,3 for 130~b,30,2 for 45~c,20~d,15`

You will then be prompted to scan your items.
A <enter>
B <enter>
B <enter>
CHECKOUT <enter>

You will receive a running total of your basket and a final one upon finishing checking out.

## To Test

`sbt test` will run the test suite. The tests use Scalatests WordSpec.