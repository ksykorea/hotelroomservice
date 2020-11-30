package hotelroomservice;

public class Ordered extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Integer roomNum;
    private Integer serviceNum;
    private String orderStatus;

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