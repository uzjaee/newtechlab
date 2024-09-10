package sparta.outapi;

import java.util.List;
import org.springframework.http.ResponseEntity;
import sparta.outapi.CreatePetRequest.Category;
import sparta.outapi.CreatePetRequest.Tag;

public interface SwaggerService {

  PetCreatResponse createPetData(Long petId,
      String name,
      Category category,
      List<String> photoUrls,
      List<Tag> tags,
      String status);
  PetResponse getPetData(Long petId);

}
