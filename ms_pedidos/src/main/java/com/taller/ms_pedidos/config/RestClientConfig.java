package com.taller.ms_pedidos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${ms.usuarios.url}")
    private String msUsuariosUrl;

    @Value("${ms.productos.url}")
    private String msProductosUrl;

    //RestClient hacia ms-usuarios (puerto 8081)
    @Bean
    public RestClient usuarioRestClient() {
        return RestClient.builder()
                .baseUrl(msUsuariosUrl)
                .build();
    }

    //RestClient hacia ms-productos (puerto 8082)
    @Bean
    public RestClient productoRestClient() {
        return RestClient.builder()
                .baseUrl(msProductosUrl)
                .build();
    }

}