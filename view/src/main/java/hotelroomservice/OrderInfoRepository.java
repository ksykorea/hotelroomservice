package hotelroomservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderInfoRepository extends CrudRepository<OrderInfo, Long> {

    List<OrderInfo> findByOrderdId(Long orderdId);

}