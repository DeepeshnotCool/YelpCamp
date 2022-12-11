package me.jysh.springyelpcamp.model.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("reviews")
@NoArgsConstructor
public class Review {

  @Id private String id;

  private String text;

  @DBRef private User author;

  @Override
  public String toString() {

    return "Review{" + "id='" + id + '\'' + ", text='" + text + '\'' + ", author=" + author + '}';
  }
}
