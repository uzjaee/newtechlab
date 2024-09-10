package sparta.outapi;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OutapiApplication {

	private final SwaggerService swaggerService;

	public OutapiApplication(SwaggerService swaggerService) {
		this.swaggerService = swaggerService;
	}


	public static void main(String[] args) {
		SpringApplication.run(OutapiApplication.class, args);
	}

	@PostConstruct
	public void init() {
		PetCreatResponse createResponse =  swaggerService.createPetData(1L,"sparta");
		PetResponse response = swaggerService.getPetData(createResponse.getId());
		System.out.println("petId = " + response.getId() + ", name = " + response.getName() + ", images= " + response.getPhotos() + ", tags = " + response.getTags());
	}

}
