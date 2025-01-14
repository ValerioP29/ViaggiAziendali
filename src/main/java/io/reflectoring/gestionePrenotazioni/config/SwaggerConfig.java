/*package io.reflectoring.gestionePrenotazioni.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.PathItem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean (name = "swaggerOpenAPI")
    public OpenAPI swaggerOpenAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("Gestione prenotazioni API")
                        .version("1.0")
                        .description("API per gestione viaggi aziendali"));

        // Creazione di un oggetto Paths per configurare i percorsi
        Paths paths = new Paths();

        // Aggiungi la configurazione dell'upload per il file
        PathItem pathItem = new PathItem()
                .post(new io.swagger.v3.oas.models.Operation()
                        .summary("Upload immagine profilo")
                        .description("Carica un'immagine per il dipendente.")
                        .requestBody(
                                new RequestBody()
                                        .required(true)
                                        .content(
                                                new io.swagger.v3.oas.models.media.Content()
                                                        .addMediaType("multipart/form-data", new MediaType()
                                                                .schema(new Schema<>()
                                                                        .type("object")
                                                                        .addProperties("file", new Schema<>()
                                                                                .type("string")
                                                                                .format("binary")
                                                                        )
                                                                )
                                                        )
                                        )
                        )
                );

        // Aggiungi il percorso specifico
        paths.addPathItem("/api/dipendenti/{id}/upload", pathItem);

        openAPI.paths(paths);

        return openAPI;
    }
} */
