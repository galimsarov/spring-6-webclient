package guru.springframework.spring6webclient.client

import com.fasterxml.jackson.databind.JsonNode
import guru.springframework.spring6webclient.model.BeerDTO
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

private const val BEER_PATH = "/api/v3/beer"

@Service
@Suppress("unused")
class BeerClientImpl(webClientBuilder: WebClient.Builder) : BeerClient {
    private val webClient = webClientBuilder.baseUrl("http://localhost:8080").build()
    override fun listBeer(): Flux<String> {
        return webClient.get().uri(BEER_PATH).retrieve().bodyToFlux(String::class.java)
    }

    override fun listBeerMap(): Flux<Map<*, *>> {
        return webClient.get().uri(BEER_PATH).retrieve().bodyToFlux(Map::class.java)
    }

    override fun listBeersJsonNode(): Flux<JsonNode> {
        return webClient.get().uri(BEER_PATH).retrieve().bodyToFlux(JsonNode::class.java)
    }

    override fun listBeerDtos(): Flux<BeerDTO> {
        return webClient.get().uri(BEER_PATH).retrieve().bodyToFlux(BeerDTO::class.java)
    }
}