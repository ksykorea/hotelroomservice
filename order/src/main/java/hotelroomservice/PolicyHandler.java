package hotelroomservice;

import hotelroomservice.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverServiceProvided_Changeorderinfo(@Payload ServiceProvided serviceProvided){

        if(serviceProvided.isMe()){
            try {
                Thread.currentThread().sleep((long) (880 + Math.random() * 220));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(serviceProvided.getOrderStatus().equals("SERVICE_PROVIDED")) {
                Optional<Order> orderOptional = orderRepository.findById(serviceProvided.getOrderId());
                Order order = orderOptional.get();
                order.setOrderId(serviceProvided.getOrderId());
                order.setOrderStatus(serviceProvided.getOrderStatus());

                orderRepository.save(order);
            }
        }
    }

}
