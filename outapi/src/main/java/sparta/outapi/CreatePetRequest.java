package sparta.outapi;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class CreatePetRequest {
  private Long petId;
  private String name;

}
