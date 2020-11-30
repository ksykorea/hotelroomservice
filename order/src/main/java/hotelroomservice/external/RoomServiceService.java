
package hotelroomservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="roomservice", url="http://roomservice:8080")
public interface RoomServiceService {

    @RequestMapping(method= RequestMethod.POST, path="/roomServices")
    public void serviceCancel(@RequestBody RoomService roomService);

}