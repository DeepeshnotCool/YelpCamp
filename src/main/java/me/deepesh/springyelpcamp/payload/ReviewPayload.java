package me.deepesh.springyelpcamp.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewPayload {

  private String id;

  private String text;

  private String authorId;
}
