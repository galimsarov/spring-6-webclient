package guru.springframework.spring6webclient.client

import guru.springframework.spring6webclient.model.BeerDTO
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.util.concurrent.atomic.AtomicBoolean

@SpringBootTest
class BeerClientImplTest {
    @Autowired
    private lateinit var client: BeerClient

    @Test
    fun listBeer() {
        val atomicBoolean = AtomicBoolean(false)

        client.listBeer().subscribe {
            println(it)
            atomicBoolean.set(true)
        }
        await().untilTrue(atomicBoolean)
    }

    @Test
    fun testGetMap() {
        val atomicBoolean = AtomicBoolean(false)

        client.listBeerMap().subscribe {
            println(it)
            atomicBoolean.set(true)
        }
        await().untilTrue(atomicBoolean)
    }

    @Test
    fun testGetBeerJson() {
        val atomicBoolean = AtomicBoolean(false)

        client.listBeersJsonNode().subscribe {
            println(it.toPrettyString())
            atomicBoolean.set(true)
        }
        await().untilTrue(atomicBoolean)
    }

    @Test
    fun testGetBeerDto() {
        val atomicBoolean = AtomicBoolean(false)

        client.listBeerDtos().subscribe {
            println(it.beerName)
            atomicBoolean.set(true)
        }
        await().untilTrue(atomicBoolean)
    }

    @Test
    fun testGetBeerById() {
        val atomicBoolean = AtomicBoolean(false)

        client.listBeerDtos().flatMap { client.getBeerById(it.id) }.subscribe {
            println(it.beerName)
            atomicBoolean.set(true)
        }
        await().untilTrue(atomicBoolean)
    }

    @Test
    fun testGetBeerByBeerStyle() {
        val atomicBoolean = AtomicBoolean(false)

        client.getBeerByBeerStyle("Pale Ale").subscribe {
            println(it.toString())
            atomicBoolean.set(true)
        }
        await().untilTrue(atomicBoolean)
    }

    @Test
    fun testCreateBeer() {
        val atomicBoolean = AtomicBoolean(false)

        val newDto = BeerDTO(
            price = BigDecimal(10.99),
            beerName = "Mango Bobs",
            beerStyle = "IPA",
            quantityOnHand = 500,
            upc = "123245"
        )

        client.createBeer(newDto).subscribe {
            println(it.toString())
            atomicBoolean.set(true)
        }
        await().untilTrue(atomicBoolean)
    }

    @Test
    fun testUpdate() {
        val name = "New Name"
        val atomicBoolean = AtomicBoolean(false)

        client.listBeerDtos().next().doOnNext { it.beerName = name }.flatMap { client.updateBeer(it) }.subscribe {
            println(it.toString())
            atomicBoolean.set(true)
        }
        await().untilTrue(atomicBoolean)
    }
}