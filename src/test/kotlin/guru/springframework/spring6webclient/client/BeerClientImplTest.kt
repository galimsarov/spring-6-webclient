package guru.springframework.spring6webclient.client

import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
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
}