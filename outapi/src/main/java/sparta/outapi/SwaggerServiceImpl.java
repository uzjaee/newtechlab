package sparta.outapi;

import java.util.List;
import javax.print.DocFlavor.STRING;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SwaggerServiceImpl implements  SwaggerService{

  // 리펙토링1. restTemplate 를 Bean 으로 등록해서 생성자 없이 사용
  private final RestTemplate restTemplate;
  // 리펙토링3. Swagger API URL 을 BASE_URL 상수로 등록하여 중복 코드 방지
  public static final String BASE_URL = "https://petstore.swagger.io/v2/pet/";

  // 리펙토링4. request 를 외부에서 불러옴으로써 구현체는 API 전송만 행하도록 수정
  @Override
  public PetCreatResponse createPetData(CreatePetRequest requestData) {
    HttpEntity<CreatePetRequest> request = new HttpEntity<>(requestData);
    ResponseEntity<PetCreatResponse> response = restTemplate.exchange(
        BASE_URL,
        HttpMethod.POST,
        request,
        PetCreatResponse.class);
    return response.getBody();
  }

  @Override
  public PetResponse getPetData(Long petId) {
    String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
        .path(petId.toString())
        .build()
        .toUriString();

    HttpHeaders headers = getHttpHeaders();

    HttpEntity request = new HttpEntity<>(null, headers);
    ResponseEntity<PetResponse> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        request,
        PetResponse.class);
    return response.getBody();
  }

  // 리펙토링2. 헤더 생성 함수를 getHeader() private 함수로 생성하여 중복 코드 방지
  private static HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    return headers;
  }

}
