package sparta.outapi;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sparta.outapi.CreatePetRequest.Category;
import sparta.outapi.CreatePetRequest.Tag;

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
		Category category = new Category("cat");
		List<String> photos = Arrays.asList("photo1,ptoto2");
		List<Tag> tags = Arrays.asList(new Tag("cat"));
		PetCreatResponse createResponse =  swaggerService.createPetData(
				1L,
				"sparta",
				new Category("dog"),
				photos,
				tags,
				"available");
		PetResponse response = swaggerService.getPetData(createResponse.getId());
		System.out.println("""
				petId : %d
				Name : %s
				images : %s
				tags : %s
				category : %s
				status : %s
				""".formatted(
				response.getId(),
				response.getName(),
				response.getPhotos().stream().collect(Collectors.joining(",")),
				response.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")),
				response.getCategory().getName(),
				response.getStatus()
		));
		}

}
