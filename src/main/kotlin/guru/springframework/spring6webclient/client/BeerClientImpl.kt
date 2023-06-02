package guru.springframework.spring6webclient.client

import com.fasterxml.jackson.databind.JsonNode
import guru.springframework.spring6webclient.model.BeerDTO
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

private const val BEER_PATH = "/api/v3/beer"
private const val BEER_PATH_ID = "$BEER_PATH/{beerId}"

@Service
@Suppress("unused")
class BeerClientImpl(webClientBuilder: WebClient.Builder) : BeerClient {
    private val webClient = webClientBuilder.build()
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

    override fun getBeerById(id: String): Mono<BeerDTO> {
        return webClient.get().uri { it.path(BEER_PATH_ID).build(id) }.retrieve().bodyToMono(BeerDTO::class.java)
    }

    override fun getBeerByBeerStyle(beerStyle: String): Flux<BeerDTO> {
        return webClient.get().uri { it.path(BEER_PATH).queryParam("beerStyle", beerStyle).build() }.retrieve()
            .bodyToFlux(BeerDTO::class.java)
    }
}