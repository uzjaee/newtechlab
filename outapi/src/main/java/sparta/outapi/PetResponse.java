package sparta.outapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sparta.outapi.CreatePetRequest.Category;
import sparta.outapi.CreatePetRequest.Tag;

@Getter
public class PetResponse
{
  private Long id;
  private String name;
  @JsonProperty("photoUrls") // 키 치환
  private List<String> photos;
  private List<Tag> tags;
  private Category category;
  private String status;



}
