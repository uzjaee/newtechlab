package sparta.outapi;

import org.springframework.http.ResponseEntity;

public interface SwaggerService {

  PetCreatResponse createPetData(Long petId, String name);
  PetResponse getPetData(Long petId);

}
