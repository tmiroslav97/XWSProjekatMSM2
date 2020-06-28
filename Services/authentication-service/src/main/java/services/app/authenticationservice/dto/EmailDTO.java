package services.app.authenticationservice.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO implements Serializable {
    String email;
    String subject;
    String message;
}