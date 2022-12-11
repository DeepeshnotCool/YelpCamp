package me.jysh.springyelpcamp.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

  private LocalDateTime timeStamp;

  private int errorCode;

  private String errorMessage;

  private List<String> userMessages;

  private String path;
}
