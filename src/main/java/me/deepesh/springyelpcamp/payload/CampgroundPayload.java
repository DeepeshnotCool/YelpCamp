package me.deepesh.springyelpcamp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = Include.NON_NULL)
public class CampgroundPayload {

  private String id;

  private String name;

  private Double price;

  private String image;

  private String description;

  private String location;

  private String phone;

  private String tags;

  private String authorId;

  @Builder.Default private List<ReviewPayload> reviews = new ArrayList<>();
}
