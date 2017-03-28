package nutrition.controller;

import nutrition.crawler.Crawler;
import nutrition.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by andrius on 3/27/17.
 */

@RestController
@RequestMapping("api/crawlers")
public class CrawlerController {

    private final Crawler crawler;
    private final ProductService productService;

    @Autowired
    public CrawlerController(Crawler crawler, ProductService productService) {
        this.crawler = crawler;
        this.productService = productService;
    }

    @GetMapping(path = "crawl")
    public long crawlForProducts() throws IOException {
        crawler.crawl("https://www.barbora.lt");
        return productService.getCount();
    }
}
