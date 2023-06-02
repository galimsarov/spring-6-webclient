package guru.springframework.spring6webclient.client

import reactor.core.publisher.Flux

interface BeerClient {
    fun listBeer(): Flux<String>
}