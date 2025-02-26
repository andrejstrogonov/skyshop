package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.*;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private static Map<UUID, ProductInterface> products =  new HashMap<>();
    private final Map<UUID, Article> articles;

    public StorageService(Map<UUID, ProductInterface> products, Map<UUID, Article> articles) {
        this.products = products;
        this.articles = articles;
        List<ProductInterface> allProductInterfaces = returnAllProducts();
        List<Article> allArticles = returnAllArticles();
    }
    public static List<ProductInterface> returnAllProducts() {
        List<ProductInterface> productInterfaces = new ArrayList<>();
        ProductInterface productInterface1 = new SimpleProduct(UUID.randomUUID(), "Молоко", 80);
        ProductInterface productInterface2 = new FixPriceProduct(UUID.randomUUID(), "Хлеб");
        ProductInterface productInterface3 = new FixPriceProduct(UUID.randomUUID(), "Сыр");
        ProductInterface productInterface4 = new DiscountedProduct(UUID.randomUUID(), "Масло", 400, 20);
        ProductInterface productInterface5 = new DiscountedProduct(UUID.randomUUID(), "Яйца", 140, 10);
        ProductInterface productInterface6 = new SimpleProduct(UUID.randomUUID(), "Мясо", 900);
        ProductInterface productInterface7 = new SimpleProduct(UUID.randomUUID(), "Бластер", 200);

        productInterfaces.add(productInterface1);
        productInterfaces.add(productInterface2);
        productInterfaces.add(productInterface3);
        productInterfaces.add(productInterface4);
        productInterfaces.add(productInterface5);
        productInterfaces.add(productInterface6);
        productInterfaces.add(productInterface7);

        return productInterfaces;
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
        return (Product) Optional.ofNullable(products.get(id))
                .orElseThrow(() -> new NoSuchProductException(id));
    }

    public Object getUserBasket() {
        return productBasket;
    }
}
