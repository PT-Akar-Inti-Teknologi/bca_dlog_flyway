package erp.logisticassistant.gista.assetmanagement.configuration.documentation;

import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalConstant;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
@Profile({"!prod"})
@RequiredArgsConstructor
@SecurityScheme(
        name = GlobalConstant.TAG_AUTHENTICATION_HEADER,
        type = SecuritySchemeType.HTTP,
        bearerFormat = GlobalConstant.TAG_AUTHENTICATION_FORMAT,
        scheme = GlobalConstant.TAG_AUTHENTICATION_TYPE
)
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    private final ApplicationProperties applicationProperties;
    @Bean
    public GroupedOpenApi kafkaLog() {
        return GroupedOpenApi.builder()
                .group(GlobalConstant.DOCUMENTATION_GROUP_KAFKALOG)
                .packagesToScan(GlobalConstant.DOCUMENTATION_PACKAGE_KAFKALOG)
                .build();
    }

    @Bean
    public OpenAPI springOAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url(applicationProperties.getServer().getServlet().getContextPath()))
                .info(new Info().title(applicationProperties.getSpring().getApplication().getName())
                        .description(GlobalConstant.DOCUMENTATION_DESCRIPTION)
                        .version(applicationProperties.getSpring().getApplication().getVersion())
                        .contact(new Contact()
                                .url(GlobalConstant.DOCUMENTATION_CONTACT_URL)
                                .name(GlobalConstant.DOCUMENTATION_CONTACT_NAME)
                                .email(GlobalConstant.DOCUMENTATION_CONTACT_EMAIL)));
    }
}
