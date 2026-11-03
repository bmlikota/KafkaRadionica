package hr.zaba.kafkademo.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.MediaType;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.zaba.kafkademo.dto.Order;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<Long, Order> ordersKafkaTemplate;
    private final StreamsBuilderFactoryBean factoryBean;

    @GetMapping("/test")
    public String test() {
        var poruka = "Moj prvi Spring Boot event";
        // kafkaTemplate.send(MY_TOPIC, poruka);
        // Ako zelimo cekati odgovor asinkrono
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("mytopic", poruka);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + poruka +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        poruka + "] due to : " + ex.getMessage());
            }
        });

        return "Ok";
    }

    @PostMapping("/order")
    public void postMethodName(@RequestBody Order order) {
        this.ordersKafkaTemplate.send("orders", order);
        // this.ordersKafkaTemplate.send("orders", order.getCustomerId(), order);
    }

    @GetMapping(value = "/topology", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getTopology() {
        return factoryBean.getTopology().describe().toString();
    }

}
