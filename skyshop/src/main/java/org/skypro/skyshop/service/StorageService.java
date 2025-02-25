package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private static Map<UUID, Product> products =  new HashMap<>();
    private final Map<UUID, Article> articles;

    public StorageService(Map<UUID, Product> products, Map<UUID, Article> articles) {
        this.products = products;
        this.articles = articles;
        List<Product> allProducts = returnAllProducts();
        List<Article> allArticles = returnAllArticles();
    }
    public static List<Product> returnAllProducts() {
        List<Product> products = new ArrayList<>();
        Product product1 = new SimpleProduct(UUID.randomUUID(), "Молоко", 80);
        Product product2 = new FixPriceProduct(UUID.randomUUID(), "Хлеб");
        Product product3 = new FixPriceProduct(UUID.randomUUID(), "Сыр");
        Product product4 = new DiscountedProduct(UUID.randomUUID(), "Масло", 400, 20);
        Product product5 = new DiscountedProduct(UUID.randomUUID(), "Яйца", 140, 10);
        Product product6 = new SimpleProduct(UUID.randomUUID(), "Мясо", 900);
        Product product7 = new SimpleProduct(UUID.randomUUID(), "Бластер", 200);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);

        return products;
    }
    public static List<Article> returnAllArticles() {
        List<Article> articles = new ArrayList<>();
        Article article1 = new Article(UUID.randomUUID(), "Хлеб и молоко - можно ли выжить?",
                "Выжить на хлебе и молоке невозможно, " +
                        "так как ни один продукт не способен дать человеку всё, " +
                        "что нужно для здорового образа жизни.");

        Article article2 = new Article(UUID.randomUUID(), "Что нужно есть время от времени",
                "При составлении рациона питания стоит учитывать " +
                        "индивидуальные особенности человека, " +
                        "в том числе биологические ритмы. " +
                        "Но мясо есть необходимо.");

        Article article3 = new Article(UUID.randomUUID(), "Lorem Ipsum,",
                "У меня когда-то давно был автомобиль Toyota Ipsum в 10-м кузове. " +
                        "Лучшая машина на планете Земля.");

        Article article4 = new Article(UUID.randomUUID(), "Lorem Ipsum про автомобиль Toyota Ipsum в 10-м кузове.",
                "У меня когда-то давно был автомобиль Toyota Ipsum в 10-м кузове. " +
                        "Лучшая машина на планете Земля - это минивэн Toyota Ipsum");
        // Add articles to the list
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);

        return articles; // Return the list of articles
    }
    public Collection<Searchable> getAllSearchables() {
        List<Searchable> searchables = new ArrayList<>();
        searchables.addAll(articles.values());
        searchables.addAll((Collection<? extends Searchable>) products);
        return searchables;
    }
    public static Product getProductById(UUID id) {
        return Optional.ofNullable(products.get(id))
                .orElseThrow(() -> new NoSuchProductException(id));
    }
}
