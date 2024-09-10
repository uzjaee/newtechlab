package sparta.outapi;



import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CreatePetRequest {
  private Long petId;
  private String name;
  private Category category;
  private List<String> photoUrls;
  private List<Tag> tags;
  private String status;

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Category{
    private String name;
  }

  @Getter

  @AllArgsConstructor
  @NoArgsConstructor
  public static class Tag{
    private String name;
  }
}
