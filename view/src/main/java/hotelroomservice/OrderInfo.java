package hotelroomservice;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="OrderInfo_table")
public class OrderInfo {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Integer roomNum;
        private Integer menuNum;
        private String currentOrderStatus;
        private Long orderdId;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Integer getRoomNum() {
            return roomNum;
        }

        public void setRoomNum(Integer roomNum) {
            this.roomNum = roomNum;
        }
        public Integer getMenuNum() {
            return menuNum;
        }

        public void setMenuNum(Integer menuNum) {
            this.menuNum = menuNum;
        }
        public String getCurrentOrderStatus() {
            return currentOrderStatus;
        }

        public void setCurrentOrderStatus(String currentOrderStatus) {
            this.currentOrderStatus = currentOrderStatus;
        }
        public Long getOrderdId() {
            return orderdId;
        }

        public void setOrderdId(Long orderdId) {
            this.orderdId = orderdId;
        }

}
