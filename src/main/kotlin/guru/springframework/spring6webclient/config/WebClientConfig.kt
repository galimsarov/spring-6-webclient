package guru.springframework.spring6webclient.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@Suppress("unused")
class WebClientConfig(@Value("\${webclient.rooturl}") private val rootUrl: String) : WebClientCustomizer {
    override fun customize(webClientBuilder: WebClient.Builder?) {
        webClientBuilder?.baseUrl(rootUrl)
    }
}