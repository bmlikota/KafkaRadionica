package hr.zaba.kafkademo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String transactionId;
    private String accountId;
    private double amount;
    private String status;
}
