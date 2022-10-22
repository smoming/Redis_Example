package per.mike.example.ctrl;

import java.math.BigDecimal;
import java.util.List;

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
}
