package guru.springframework.spring6webclient.model

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class BeerDTO(
    var id: String = UUID.randomUUID().toString(),
    var beerName: String = "",
    var beerStyle: String = "",
    var upc: String = "",
    var quantityOnHand: Int = 0,
    var price: BigDecimal = BigDecimal(0),
    var createdDate: LocalDateTime = LocalDateTime.now(),
    var lastModifiedDate: LocalDateTime = LocalDateTime.now(),
)