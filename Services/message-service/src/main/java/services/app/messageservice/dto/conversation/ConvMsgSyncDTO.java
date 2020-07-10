package services.app.messageservice.dto.conversation;


import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConvMsgSyncDTO {
    private Long convMainId;
    private String convName;
    private Long requestMainId;
    private String content;
    private String recieverEmail;
    private String senderEmail;
    private String senderFristName;
    private String senderLastName;
}
