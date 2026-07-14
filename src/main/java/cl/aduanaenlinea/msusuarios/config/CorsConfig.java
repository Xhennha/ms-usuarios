package cl.aduanaenlinea.msusuarios.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configura que origenes (dominios del frontend) pueden llamar a esta API.
 * La lista se lee desde la propiedad app.cors.allowed-origins (variable de entorno
 * CORS_ALLOWED_ORIGINS), separada por comas, para poder agregar la URL de
 * produccion del frontend sin modificar codigo.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
