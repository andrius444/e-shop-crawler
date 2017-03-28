package nutrition.crawler;

import nutrition.enumerator.Nutritions;
import nutrition.model.Product;
import nutrition.service.CategoryService;
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
    private final CategoryService categoryService;

    @Autowired
    public Crawler(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    public void crawl(String url) throws IOException {
        List<Element> rootUrlsAnchors = Jsoup.connect(url)
                .get()
                .select(".b-categories-root-category")
                .select("a[href]")
                .subList(0, 5);
        List<String> categories = rootUrlsAnchors.stream()
                .map(Element::text)
                .collect(Collectors.toList());

        categoryService.saveCrawledCategories(categories);

        int i = 0;
        Map<Integer, Set<String>> productsAnchors = new HashMap<>();
        for (String categoryUrl : rootUrlsAnchors.stream().map(el -> el.attr("href")).collect(Collectors.toList())) {
            Set<String> grandChildrenAnchors = new HashSet<>();
            Document doc = Jsoup.connect(url + categoryUrl).get();
            doc.select(".b-single-category--grandchild").forEach(anchor -> {
                try {
                    grandChildrenAnchors.addAll(
                            new ArrayList<>(
                                    Jsoup.connect(url + anchor.attr("href"))
                                            .get()
                                            .select(".b-link--product-info")
                                            .stream()
                                            .map(el -> el.attr("href"))
                                            .filter(element -> element.contains("produktai"))
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

        productsAnchors.forEach((key, value) -> {
            value.forEach(element -> {
                Document product; Elements trs = null; String productName = null;
                try {
                    product = Jsoup.connect(url + element).get();
                    Elements tbody = product.select("tbody");
                    if (tbody.size() == 0) {
                        return;
                    } else {
                        trs = tbody.first().select("tr");
                        productName = normalizeProductName(product.select("title").first().text());
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

                List<Product> rawProducts = new ArrayList<>();
                Product raw = productService.crawledDataToEntity(nutritions, productName, categories.get(key));
                rawProducts.add(raw);
                productService.saveAllProducts(rawProducts);
            });
            System.out.println("=== CATEGORY " + (key+1) + " PERSISTED");
        });

        System.out.println("============== END CRAWL");
    }

    // PRIVATE

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

    private String normalizeProductName(String name) {
        int idx1 = name.indexOf("(");
        String substring = idx1 > 0 ? name.substring(0, idx1) : name;
        int idx2 = substring.indexOf(",");
        return idx2 > 0 ? substring.substring(0, idx2) : substring;
    }
}
