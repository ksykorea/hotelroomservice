package hotelroomservice;

import hotelroomservice.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderInfoViewHandler {


    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {
            if (ordered.isMe()) {

                if(ordered.getOrderStatus().equals("SERVICE_REQUEST")) {
                    // view 객체 생성
                    OrderInfo orderInfo = new OrderInfo();
                    // view 객체에 이벤트의 Value 를 set 함
                    orderInfo.setRoomNum(ordered.getRoomNum());
                    orderInfo.setMenuNum(ordered.getServiceNum());
                    orderInfo.setCurrentOrderStatus(ordered.getOrderStatus());
                    orderInfo.setOrderdId(ordered.getOrderId());
                    // view 레파지 토리에 save
                    orderInfoRepository.save(orderInfo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenServiceProvided_then_UPDATE_1(@Payload ServiceProvided serviceProvided) {
        try {
            if (serviceProvided.isMe()) {

                if(serviceProvided.getOrderStatus().equals("SERVICE_PROVIDED")) {
                    // view 객체 조회
                    List<OrderInfo> orderInfoList = orderInfoRepository.findByOrderdId(serviceProvided.getOrderId());
                    for (OrderInfo orderInfo : orderInfoList) {
                        // view 객체에 이벤트의 eventDirectValue 를 set 함
                        orderInfo.setCurrentOrderStatus(serviceProvided.getOrderStatus());
                        // view 레파지 토리에 save
                        orderInfoRepository.save(orderInfo);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCanceled_then_UPDATE_2(@Payload Canceled canceled) {
        try {
            if (canceled.isMe()) {

                if(canceled.getOrderStatus().equals("CANCEL_REQUEST")) {
                    // view 객체 조회
                    List<OrderInfo> orderInfoList = orderInfoRepository.findByOrderdId(canceled.getOrderId());
                    for (OrderInfo orderInfo : orderInfoList) {
                        // view 객체에 이벤트의 eventDirectValue 를 set 함
                        orderInfo.setCurrentOrderStatus(canceled.getOrderStatus());
                        // view 레파지 토리에 save
                        orderInfoRepository.save(orderInfo);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenServiceCanceled_then_UPDATE_3(@Payload ServiceCanceled serviceCanceled) {
        try {
            if (serviceCanceled.isMe()) {

                if(serviceCanceled.getOrderStatus().equals("SERVICE_CANCELED")) {
                    // view 객체 조회
                    List<OrderInfo> orderInfoList = orderInfoRepository.findByOrderdId(serviceCanceled.getOrderId());
                    for (OrderInfo orderInfo : orderInfoList) {
                        // view 객체에 이벤트의 eventDirectValue 를 set 함
                        orderInfo.setCurrentOrderStatus(serviceCanceled.getOrderStatus());
                        // view 레파지 토리에 save
                        orderInfoRepository.save(orderInfo);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenServiceCanceled_then_DELETE_1(@Payload ServiceCanceled serviceCanceled) {
        try {
            if (serviceCanceled.isMe()) {
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}