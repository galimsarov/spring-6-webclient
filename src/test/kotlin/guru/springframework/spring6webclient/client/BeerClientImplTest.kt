package guru.springframework.spring6webclient.client

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BeerClientImplTest {
    @Autowired
    private lateinit var client: BeerClient

    @Test
    fun listBeer() {
        client.listBeer().subscribe { println(it) }
        Thread.sleep(1000)
    }
}