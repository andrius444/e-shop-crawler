package nutrition.crawler;

import nutrition.enumerator.Nutritions;
import nutrition.model.Product;
import nutrition.service.ProductService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by andrius on 3/27/17.
 */

@Component
public class Crawler {

    private final ProductService productService;

    @Autowired
    public Crawler(ProductService productService) {
        this.productService = productService;
    }

    public void crawl(String url) throws IOException {
        List<Element> rootUrlsAnchors = Jsoup.connect(url)
                .get()
                .select(".b-categories-root-category")
                .select("a[href]")
                .subList(0, 1);
        List<String> categories = rootUrlsAnchors.stream()
                .map(Element::text)
                .collect(Collectors.toList());

        int i = 0;
        Map<Integer, Set<Element>> productsAnchors = new HashMap<>();
        for (String categoryUrl : rootUrlsAnchors.stream().map(el -> el.attr("href")).collect(Collectors.toList())) {
            Set<Element> grandChildrenAnchors = new HashSet<>();
            Document doc = Jsoup.connect(url + categoryUrl).get();
            doc.select(".b-single-category--grandchild").forEach(anchor -> {
                try {
                    grandChildrenAnchors.addAll(
                            new ArrayList<>(
                                    Jsoup.connect(url + anchor.attr("href"))
                                            .get()
                                            .select(".b-link--product-info")
                                            .stream()
                                            .filter(element -> element.attr("href").contains("produktai"))
                                            .collect(Collectors.toList())
                            )
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            productsAnchors.put(i, grandChildrenAnchors);
            i++;
        }

        System.out.println("============== END");

        productsAnchors.forEach((key, value) -> {
            value.forEach(element -> {
                Document product = null; Elements trs = null; String productName = null;
                try {
                    product = Jsoup.connect(url + element.attr("href")).get();
                    Elements tbody = product.select("tbody");
                    if (tbody.size() == 0) {
                        return;
                    } else {
                        trs = tbody.first().select("tr");
                        productName = product.select("title").first().text();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Map<String, Double> nutritions = new HashMap<>();
                assert trs != null;
                trs.stream().filter(this::filterRow).forEach(el -> {
                    Elements tds = el.select("td");
                    nutritions.put(tds.first().text(), this.parseDouble(tds.last().text()));
                });

                Product prodPersisted = new Product(
                        productName,
                        categories.get(key),
                        nutritions.get(Nutritions.KCALS.getValue()),
                        nutritions.get(Nutritions.FATS.getValue()),
                        nutritions.get(Nutritions.CARBS.getValue()),
                        nutritions.get(Nutritions.PROTEINS.getValue())
                );

                productService.saveProduct(prodPersisted);
            });
        });
    }

    private boolean filterRow(Element el) {
        String header = el.select("td").first().text();
        return header.contains(Nutritions.CARBS.getValue()) ||
                header.contains(Nutritions.FATS.getValue()) ||
                header.contains(Nutritions.PROTEINS.getValue()) ||
                header.contains(Nutritions.KCALS.getValue());
    }

    private Double parseDouble(String string) {
        return (string.contains("Kcal")) ?
                parseKcals(string) : Double.parseDouble(string.substring(0, string.length()-1).replace(",", "."));
    }

    private Double parseKcals(String string) {
        int start = string.indexOf("/");
        int end = string.indexOf("Kcal");
        return Double.parseDouble(string.substring(start+1, end-1).replace(",", "."));
    }
}
