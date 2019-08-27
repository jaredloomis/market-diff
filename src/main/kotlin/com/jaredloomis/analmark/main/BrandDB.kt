package com.jaredloomis.analmark.main

import kotlin.collections.HashSet

interface BrandDB {
  fun addBrand(brand: Brand)
  fun findMatches(brandStr: String): List<Brand>
}

class DummyBrandDB: BrandDB {
  val brands = HashSet<Brand>()

  override fun addBrand(brand: Brand) {
    brands.add(brand)
  }

  // TODO return matches in order, instead of just best match
  override fun findMatches(brandStr: String): List<Brand> {
    val searchTokens = tokenize(brandStr)
    var maxScore = 0
    var bestMatch: Brand? = null

    for(brand in brands) {
      val productTokens = tokenize(brand.name)
      // Score = number of tokens which are common across both search product and search
      val score = productTokens.filter {
        searchTokens.contains(it)
      }.size

      if(score > maxScore) {
        maxScore  = score
        bestMatch = brand
      }
    }

    val ret = ArrayList<Brand>()
    if(bestMatch != null) {
      ret.add(bestMatch)
    }
    return ret
  }
}

/*
class PostgresBrandDB {
  fun addProduct(product: Product) {

  }

  fun findMatch(title: String): Product? {

  }
}
*/