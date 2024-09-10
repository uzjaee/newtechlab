package sparta.outapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PetResponse
{
  private Long id;
  private String name;
  @JsonProperty("photoUrls") // 키 치환
  private List<String> photos;
  private List<PetTagResponse> tags;

  // id , name
  public static class PetTagResponse{
    @JsonProperty("id")
    private String tagId;
    private String name;
  }
}
