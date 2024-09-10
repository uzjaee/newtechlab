package sparta.outapi;

import java.util.List;
import javax.print.DocFlavor.STRING;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sparta.outapi.CreatePetRequest.Category;
import sparta.outapi.CreatePetRequest.Tag;

@Service
public class SwaggerServiceImpl implements  SwaggerService{

  @Override
  public PetCreatResponse createPetData(Long petId,
      String name,
      Category category,
      List<String> photoUrls,
      List<Tag> tags,
      String status) {
    String url = "https://petstore.swagger.io/v2/pet/";
    CreatePetRequest requestData = new CreatePetRequest(
        petId,
        name,
        category,
        photoUrls,
        tags,
        status);

    HttpEntity<CreatePetRequest> request = new HttpEntity<>(requestData);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<PetCreatResponse> response = restTemplate.exchange(
        url,
        HttpMethod.POST,
        request,
        PetCreatResponse.class);
    return response.getBody();
  }

  @Override
  public PetResponse getPetData(Long petId) {
    String baseUrl = "https://petstore.swagger.io/v2/pet/";
    String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
        .path(petId.toString())
        .build()
        .toUriString();

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity request = new HttpEntity<>(null, headers);
    ResponseEntity<PetResponse> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        request,
        PetResponse.class);
    return response.getBody();
  }

}
