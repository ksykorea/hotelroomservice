package hotelroomservice;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="RoomService_table")
public class RoomService {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Integer roomNum;
    private Integer serviceNum;
    private String orderStatus;

    @PostPersist
    public void onPostPersist(){
        if(getOrderStatus().equals("SERVICE_PROVIDED")) {

            //부하테스트, 서킷 브레이킹
            /*
            try {
                Thread.currentThread().sleep((long) (1000 + Math.random() * 220));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */

            ServiceProvided serviceProvided = new ServiceProvided();
            BeanUtils.copyProperties(this, serviceProvided);
            serviceProvided.publish();

        }
        else if(getOrderStatus().equals("CANCEL_REQUEST")){

            ServiceCanceled serviceCanceled = new ServiceCanceled();
            BeanUtils.copyProperties(this, serviceCanceled);
            serviceCanceled.setOrderStatus("SERVICE_CANCELED");

            serviceCanceled.publishAfterCommit();
        }
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
