package se.callista.microservises.core.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import se.callista.microservises.core.product.model.Product;

/**
 * Created by magnus on 04/03/15.
 */
@RestController
public class ProductService {

	@Autowired
    private LoadBalancerClient loadBalancer;
	private RestTemplate restTemplate = new RestTemplate();

    /**
     * Sample usage: curl $HOST:$PORT/product/1
     *
     * @param productId
     * @return
     */
    @RequestMapping("/product/{productId}")
    @HystrixCommand(fallbackMethod = "defaultProduct")
    public Product getProduct(@PathVariable int productId) {

        return new Product(productId, "name", 123);
    }

    /**
     * Fallback method for getProduct()
     *
     * @param productId
     * @return
     */
    public ResponseEntity<Product> defaultProduct(int productId) {
        return new ResponseEntity<Product>(HttpStatus.BAD_GATEWAY);
    }

}
