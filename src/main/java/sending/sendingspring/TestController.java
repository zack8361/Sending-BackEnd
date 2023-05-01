package sending.sendingspring;


import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public ArrayList<String> testHandler() {
        ArrayList<String> list = new ArrayList<>();
        list.add("황윤규");
        list.add("이찬호");
        list.add("콩희찡꼴라");
        list.add("이하나");
        return list;
    }
}
