package com.jaredloomis.analmark

import com.jaredloomis.analmark.db.PostgresPostingDBModel
import com.jaredloomis.analmark.db.PostgresProductDBModel
import com.jaredloomis.analmark.model.*
import com.jaredloomis.analmark.scrape.MarketType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.random.Random

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DBModelTest {
  val randID    = Random.nextInt(100)
  val exProduct = Product(12, "iPhone $randID", Brand("Apple"))
  val exPosting = RawPosting(
    MarketType.EBAY, "", "Brand new in case very ciol iPhone $randID New buy cehap",
    "", CurrencyAmount("$700"), emptyMap()
  )
  val exProductPosting = ProductPosting(exProduct, exPosting)

  @Test
  fun pgPosting() {
    val model = PostgresPostingDBModel(PostgresProductDBModel())
    val posting = model.insert(exProductPosting)
    println(posting.product)
    val foundProdPost = model.findOne(posting.product)
    assert(foundProdPost?.product?.canonicalName == exProductPosting.product.canonicalName) {
      "insert a posting and find it by its product. $foundProdPost\n$exProductPosting"
    }
  }
}