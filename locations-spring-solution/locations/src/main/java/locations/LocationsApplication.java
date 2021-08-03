package locations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LocationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationsApplication.class, args);
    }

    @Bean
    public ModelMapper modelmapper() {
        return new ModelMapper();
    }
}
