package sparta.outapi;

import java.util.List;
import org.springframework.http.ResponseEntity;
import sparta.outapi.CreatePetRequest.Category;
import sparta.outapi.CreatePetRequest.Tag;

public interface SwaggerService {

  PetCreatResponse createPetData(CreatePetRequest requestData);
  PetResponse getPetData(Long petId);

}
