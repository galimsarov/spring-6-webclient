package guru.springframework.spring6webclient.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@Suppress("unused")
class WebClientConfig(
    @Value("\${webclient.rooturl}") private val rootUrl: String,
    private val authorizedClientManager: ReactiveOAuth2AuthorizedClientManager
) : WebClientCustomizer {
    override fun customize(webClientBuilder: WebClient.Builder?) {
        val oauth = ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager)
        oauth.setDefaultClientRegistrationId("springauth")

        webClientBuilder?.filter(oauth)?.baseUrl(rootUrl)
    }
}