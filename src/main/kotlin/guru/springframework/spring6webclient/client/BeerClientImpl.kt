package guru.springframework.spring6webclient.client

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Service
@Suppress("unused")
class BeerClientImpl(webClientBuilder: WebClient.Builder) : BeerClient {
    private val webClient = webClientBuilder.baseUrl("http://localhost:8080").build()
    override fun listBeer(): Flux<String> {
        return webClient.get().uri("/api/v3/beer", String::class.java).retrieve().bodyToFlux(String::class.java)
    }
}