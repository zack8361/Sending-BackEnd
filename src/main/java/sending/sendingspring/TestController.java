package sending.sendingspring;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class TestController {
    @Autowired
    ObjectMapper om;

    @GetMapping("/test")
    public ArrayList<String> testHandler() {
        ArrayList<String> list = new ArrayList<>();
        list.add("황윤규");
        list.add("이찬호");
        list.add("콩희찡꼴라");
        list.add("이하나");
        return list;
    }
    @GetMapping("/list")
    public ResponseEntity<List<String>> listTest() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("황윤규");
        list.add("이찬호");
        list.add("콩희찡꼴라");
        list.add("이하나");
        return ResponseEntity.ok(list);
    }
    @GetMapping("/map")
    public ResponseEntity<Map<String, Object>> mapTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("황윤규",26);
        map.put("이찬호",26);
        map.put("이하나",25);
        map.put("공희진",24);
        return ResponseEntity.ok(map);
    }
    @GetMapping("/mapList")
    public ResponseEntity<List<Map<String, Object>>> mapListTest() {
        List<Map<String, Object>> mapList = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "황윤규");
        map1.put("age",26);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "이찬호");
        map2.put("age",26);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "이하나");
        map3.put("age",25);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("name", "공희진");
        map4.put("age",24);

        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);

        return ResponseEntity.ok(mapList);
    }

    @GetMapping("/kkk")
    public ResponseEntity<Map<String, Object>> errorTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("code",403);
        map.put("msg","please try later");
        return ResponseEntity.status(403).body(map);
    }
}