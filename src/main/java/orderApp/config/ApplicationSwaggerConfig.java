package orderApp.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApplicationSwaggerConfig {
	
	@Bean
	OpenAPI openApi() {
		Server localhost = new Server();
		localhost.setUrl("http://localhost:8089");
		localhost.setDescription("Local Environment");
		
		Server development = new Server();
		development.setUrl("http://192:243:67:8089");
		development.setDescription("Dev Environment");
		
		Contact contact = new Contact();
		contact.setEmail("rohitjay920@gmail.com");
		contact.setName("Rohit");
		
		Info info = new Info().title("Online Food Order Application")
				.version("1.0").contact(contact)
				.description("This documentation exposes API endpoints"
						+ " to manage food application");
		
		return new OpenAPI().info(info).servers(List.of(localhost,development));
	}
}
