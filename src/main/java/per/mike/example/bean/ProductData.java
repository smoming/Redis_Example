package per.mike.example.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private BigDecimal price;

}
