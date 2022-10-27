package per.mike.example.ctrl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import per.mike.example.bean.ProductData;

@RestController
@RequestMapping("/product")
public class ProductController {

    public static final String HASH_KEY = "Product";

    @Autowired
    private RedisTemplate<String, ProductData> redisTemplate;

    @GetMapping("/fetch")
    public List<ProductData> fetch() {
        return redisTemplate.opsForValue().multiGet(redisTemplate.keys("*"));
    }

    @GetMapping("/get")
    public ProductData get(String id) {
        return redisTemplate.opsForValue().get(id);
    }

    @GetMapping("/add")
    public String add() {
        ProductData pd1 = new ProductData("1", "水", new BigDecimal(10));
        ProductData pd2 = new ProductData("2", "可樂", new BigDecimal(35));
        ProductData pd3 = new ProductData("3", "果汁", new BigDecimal(40));

        redisTemplate.opsForValue().set(pd1.getId(), pd1);
        redisTemplate.opsForValue().set(pd2.getId(), pd2);
        redisTemplate.opsForValue().set(pd3.getId(), pd3);

        return "add product success ~";
    }

    @GetMapping("/upd")
    public String upd() {

        ProductData pd1 = new ProductData("1", "進口水", new BigDecimal(100));
        ProductData pd2 = new ProductData("2", "進口可樂", new BigDecimal(350));
        ProductData pd3 = new ProductData("3", "進口果汁", new BigDecimal(400));

        redisTemplate.opsForValue().set(pd1.getId(), pd1);
        redisTemplate.opsForValue().set(pd2.getId(), pd2);
        redisTemplate.opsForValue().set(pd3.getId(), pd3);

        return "update product success ~";
    }

    @GetMapping("/del/{id}")
    public String del(@PathVariable("id") String id) {
        redisTemplate.delete(id);

        return "delete product success ~";
    }

    @GetMapping("/fetchForList")
    public List<ProductData> fetchForList() {
        Long size = redisTemplate.opsForList().size(HASH_KEY);
        return redisTemplate.opsForList().range(HASH_KEY, 0, size - 1);
    }

    @GetMapping("/rightPushForList")
    public Long rightPushForList() {
        return redisTemplate.opsForList()
                .rightPush(HASH_KEY, new ProductData("1", "水", new BigDecimal(10)));
    }

    @GetMapping("/leftPopForList")
    public ProductData leftPopForList() {
        return redisTemplate.opsForList()
                .leftPop(HASH_KEY);
    }

    @GetMapping("/delForList")
    public Boolean delForList() {
        return redisTemplate.delete(HASH_KEY);
    }

    @GetMapping("/fetchForHash")
    public Map<Object, Object> fetchForHash() {
        return redisTemplate.opsForHash().entries(HASH_KEY);
    }

    @GetMapping("/fetchForHashByKey")
    public Object fetchForHashByKey(String key) {
        return redisTemplate.opsForHash().entries(HASH_KEY).get(key);
    }

    @GetMapping("/putForHash")
    public String putForHash() {
        List<ProductData> list1 = Arrays.asList(
                new ProductData("1", "水1", new BigDecimal(10)),
                new ProductData("2", "水2", new BigDecimal(11)),
                new ProductData("3", "水3", new BigDecimal(12)));
        
        List<ProductData> list2 = Arrays.asList(
                new ProductData("1", "可樂1", new BigDecimal(110)),
                new ProductData("2", "可樂2", new BigDecimal(111)),
                new ProductData("3", "可樂3", new BigDecimal(112)));

        redisTemplate.opsForHash().put(HASH_KEY, "list1", list1);
        redisTemplate.opsForHash().put(HASH_KEY, "list2", list2);

        return "putForHash Success ~";
    }

    @GetMapping("/putForHashDoUpd")
    public String putForHashDoUpd(String key) {
        
        List<ProductData> list2 = Arrays.asList(
                new ProductData("11", "西瓜1", new BigDecimal(1101)),
                new ProductData("22", "西瓜2", new BigDecimal(1111)),
                new ProductData("33", "西瓜3", new BigDecimal(1121)));
        
        redisTemplate.opsForHash().put(HASH_KEY, key, list2);

        return "putForHash Do Upd Success ~";
    }

    @GetMapping("/delForHashByKey")
    public String delForHashByKey(String key) {
        redisTemplate.opsForHash().delete(HASH_KEY, key);
        return "delForHashByKey Success ~";
    }

    @GetMapping("/delForHash")
    public String delForHash() {
        redisTemplate.delete(HASH_KEY);
        return "delForHash Success ~";
    }
}
