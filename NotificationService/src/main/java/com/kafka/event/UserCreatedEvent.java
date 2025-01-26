
package com.kafka.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatedEvent {

    private Long id;

    private String email ;
  }
