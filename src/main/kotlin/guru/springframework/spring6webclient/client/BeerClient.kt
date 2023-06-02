package guru.springframework.spring6webclient.client

import com.fasterxml.jackson.databind.JsonNode
import guru.springframework.spring6webclient.model.BeerDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BeerClient {
    fun listBeer(): Flux<String>
    fun listBeerMap(): Flux<Map<*, *>>
    fun listBeersJsonNode(): Flux<JsonNode>
    fun listBeerDtos(): Flux<BeerDTO>
    fun getBeerById(id: String): Mono<BeerDTO>
    fun getBeerByBeerStyle(beerStyle: String): Flux<BeerDTO>
    fun createBeer(beerDTO: BeerDTO): Mono<BeerDTO>
    fun updateBeer(beerDTO: BeerDTO): Mono<BeerDTO>
    fun patchBeer(beerDTO: BeerDTO): Mono<BeerDTO>
    fun deleteBeer(beerDTO: BeerDTO): Mono<Void>
}