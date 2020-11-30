package hotelroomservice;

import hotelroomservice.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    RoomServiceRepository roomServiceRepository;


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_ServiceProvide(@Payload Ordered ordered){

        if(ordered.isMe()){

            try {
                Thread.currentThread().sleep((long) (880 + Math.random() * 220));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(ordered.getOrderStatus().equals("SERVICE_REQUEST")) {

                RoomService roomService = new RoomService();
                roomService.setOrderId(ordered.getOrderId());
                roomService.setRoomNum(ordered.getRoomNum());
                roomService.setServiceNum(ordered.getServiceNum());
                roomService.setOrderStatus("SERVICE_PROVIDED");

                roomServiceRepository.save(roomService);

            }
        }
    }

}
