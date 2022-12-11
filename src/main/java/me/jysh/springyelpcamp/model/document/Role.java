package me.jysh.springyelpcamp.model.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jysh.springyelpcamp.enums.RoleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document("roles")
@NoArgsConstructor
public class Role {

  @Id String id;

  @Field("role")
  private RoleEnum name;

  public Role(RoleEnum name) {
    this.name = name;
  }
}
