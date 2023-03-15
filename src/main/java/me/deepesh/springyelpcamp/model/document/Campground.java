package me.deepesh.springyelpcamp.model.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document("campgrounds")
@NoArgsConstructor
public class Campground {

  @Id private String id;

  private String name;

  private Double price;

  private String image;

  private String description;

  private String location;

  private String phone;

  private List<String> tags;

  @DBRef private User author;

  @DBRef private List<Review> reviews = new ArrayList<>();

  @Override
  public String toString() {

    return "Campground{"
        + "name='"
        + name
        + '\''
        + ", author="
        + author
        + ", reviews="
        + reviews
        + '}';
  }
}
