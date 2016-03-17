package se.callista.microservises.core.recommendation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import se.callista.microservises.core.recommendation.model.Recommendation;
import se.callista.microservises.core.recommendation.service.util.SetProcTimeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magnus on 04/03/15.
 */
@RestController
public class RecommendationService {

    private static final Logger LOG = LoggerFactory.getLogger(RecommendationService.class);

    @Autowired
    private SetProcTimeBean setProcTimeBean;

    @Autowired
    private LoadBalancerClient loadBalancer;
	private RestTemplate restTemplate = new RestTemplate();

    /*
    private int port;

    @Value("local.server.port")
    public void setPort (int port) {
        LOG.info("getReviews will be called on port: {}", port);
        this.port = port;
    }
    */

    /**
     * Sample usage: curl $HOST:$PORT/recommendation?productId=1
     *
     * @param productId
     * @return
     */
    @RequestMapping("/recommendation")
    @HystrixCommand(fallbackMethod = "defaultRecommendations")
    public List<Recommendation> getRecommendations(
            @RequestParam(value = "productId",  required = true) int productId) {

        int pt = setProcTimeBean.calculateProcessingTime();
        LOG.info("/recommendation called, processing time: {}", pt);

        sleep(pt);

        List<Recommendation> list = new ArrayList<>();
        list.add(new Recommendation(productId, 1, "Author 1", 1, "Content 1"));
        list.add(new Recommendation(productId, 2, "Author 2", 2, "Content 2"));
        list.add(new Recommendation(productId, 3, "Author 3", 3, "Content 3"));

        LOG.info("/recommendation response size: {}", list.size());

        return list;
    }

    private void sleep(int pt) {
        try {
            Thread.sleep(pt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sample usage:
     *
     *  curl "http://localhost:10002/set-processing-time?minMs=1000&maxMs=2000"
     *
     * @param minMs
     * @param maxMs
     */
    @RequestMapping("/set-processing-time")
    public void setProcessingTime(
        @RequestParam(value = "minMs", required = true) int minMs,
        @RequestParam(value = "maxMs", required = true) int maxMs) {

        LOG.info("/set-processing-time called: {} - {} ms", minMs, maxMs);

        setProcTimeBean.setDefaultProcessingTime(minMs, maxMs);
    }


    /**
     * Fallback method for getRecommendations()
     *
     * @param productId
     * @return
     */
    public ResponseEntity<List<Recommendation>> defaultRecommendations(int productId) {
        return new ResponseEntity<List<Recommendation>>(HttpStatus.BAD_GATEWAY);
    }
}
