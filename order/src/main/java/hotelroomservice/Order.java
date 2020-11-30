package hotelroomservice;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Integer roomNum;
    private Integer serviceNum;
    private String orderStatus;

    @PostPersist
    public void onPostPersist(){
        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        ordered.setOrderId(getId());
        ordered.setOrderStatus("SERVICE_REQUEST");
        ordered.publish();
    }

    @PreRemove
    public void onPreRemove(){
//        Canceled canceled = new Canceled();
//        BeanUtils.copyProperties(this, canceled);
//        canceled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        System.out.println(getId() +","+getOrderId() + "," + getRoomNum() + "," + getServiceNum() + ","
                + getOrderStatus());

        hotelroomservice.external.RoomService roomService = new hotelroomservice.external.RoomService();
        // mappings goes here
        roomService.setOrderId(getOrderId());
        roomService.setRoomNum(getRoomNum());
        roomService.setServiceNum(getServiceNum());
        roomService.setOrderStatus("CANCEL_REQUEST");

        OrderApplication.applicationContext.getBean(hotelroomservice.external.RoomServiceService.class)
            .serviceCancel(roomService);


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }
    public Integer getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(Integer serviceNum) {
        this.serviceNum = serviceNum;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }




}
