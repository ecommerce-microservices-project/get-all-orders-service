package tz.ega.allorderservices.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tz.ega.allorderservices.dto.Product;

@FeignClient(name = "get-products-by-filter-service")
public interface ProductClient {
  
	@GetMapping("/products-filtered")
	List<Product> findProductByIds(@RequestParam(required = true) List<Integer> ids);
}
