package com.sakthi.dev.ecommerce.seed;

import com.sakthi.dev.ecommerce.entity.Category;
import com.sakthi.dev.ecommerce.entity.Product;
import com.sakthi.dev.ecommerce.repository.CategoryRepository;
import com.sakthi.dev.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSeeder implements CommandLineRunner {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductSeeder(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(productRepository.count() == 0){

            // 🔹 Create Categories
            Category electronics = Category.builder().name("Electronics").build();
            Category laptops = Category.builder().name("Laptops").build();
            Category accessories = Category.builder().name("Accessories").build();

            categoryRepository.saveAll(List.of(electronics, laptops, accessories));


            List<Product> demoproducts = List.of(

                    // 🔹 Mobiles
                    Product.builder()
                            .name("iPhone 15")
                            .price(80000.0)
                            .description("Apple latest mobile")
                            .seller("Apple")
                            .stock(10)
                            .ratings(4.5)
                            .numberOfReviews(120)
                            .category(electronics)
                            .build(),

                    Product.builder()
                            .name("Samsung Galaxy S24")
                            .price(70000.0)
                            .description("Samsung flagship phone")
                            .seller("Samsung")
                            .stock(15)
                            .ratings(4.3)
                            .numberOfReviews(95)
                            .category(electronics)
                            .build(),

                    Product.builder()
                            .name("OnePlus 12")
                            .price(65000.0)
                            .description("Flagship killer smartphone")
                            .seller("OnePlus")
                            .stock(20)
                            .ratings(4.4)
                            .numberOfReviews(110)
                            .category(electronics)
                            .build(),

                    Product.builder()
                            .name("Realme GT 6")
                            .price(35000.0)
                            .description("Performance gaming phone")
                            .seller("Realme")
                            .stock(25)
                            .ratings(4.2)
                            .numberOfReviews(80)
                            .category(electronics)
                            .build(),

                    // 🔹 Laptops
                    Product.builder()
                            .name("MacBook Air M2")
                            .price(120000.0)
                            .description("Apple laptop")
                            .seller("Apple")
                            .stock(5)
                            .ratings(4.7)
                            .numberOfReviews(150)
                            .category(laptops)
                            .build(),

                    Product.builder()
                            .name("Dell XPS 13")
                            .price(110000.0)
                            .description("Premium Windows laptop")
                            .seller("Dell")
                            .stock(7)
                            .ratings(4.6)
                            .numberOfReviews(90)
                            .category(laptops)
                            .build(),

                    Product.builder()
                            .name("HP Spectre x360")
                            .price(105000.0)
                            .description("Convertible laptop")
                            .seller("HP")
                            .stock(6)
                            .ratings(4.5)
                            .numberOfReviews(70)
                            .category(laptops)
                            .build(),

                    Product.builder()
                            .name("Lenovo ThinkPad E14")
                            .price(75000.0)
                            .description("Business laptop")
                            .seller("Lenovo")
                            .stock(10)
                            .ratings(4.3)
                            .numberOfReviews(85)
                            .category(laptops)
                            .build(),

                    Product.builder()
                            .name("Asus ROG Strix")
                            .price(130000.0)
                            .description("Gaming laptop")
                            .seller("Asus")
                            .stock(4)
                            .ratings(4.6)
                            .numberOfReviews(60)
                            .category(laptops)
                            .build(),

                    // 🔹 Accessories
                    Product.builder()
                            .name("Sony Headphones")
                            .price(30000.0)
                            .description("Noise cancelling headphones")
                            .seller("Sony")
                            .stock(18)
                            .ratings(4.8)
                            .numberOfReviews(200)
                            .category(accessories)
                            .build(),

                    Product.builder()
                            .name("Boat Airdopes")
                            .price(1500.0)
                            .description("Wireless earbuds")
                            .seller("Boat")
                            .stock(50)
                            .ratings(4.0)
                            .numberOfReviews(300)
                            .category(accessories)
                            .build(),

                    Product.builder()
                            .name("JBL Flip 6")
                            .price(12000.0)
                            .description("Portable Bluetooth speaker")
                            .seller("JBL")
                            .stock(22)
                            .ratings(4.5)
                            .numberOfReviews(140)
                            .category(accessories)
                            .build(),

                    Product.builder()
                            .name("Logitech MX Master 3")
                            .price(9000.0)
                            .description("Wireless mouse")
                            .seller("Logitech")
                            .stock(30)
                            .ratings(4.7)
                            .numberOfReviews(180)
                            .category(accessories)
                            .build(),

                    Product.builder()
                            .name("Apple Magic Keyboard")
                            .price(11000.0)
                            .description("Wireless keyboard")
                            .seller("Apple")
                            .stock(12)
                            .ratings(4.6)
                            .numberOfReviews(95)
                            .category(accessories)
                            .build(),

                    Product.builder()
                            .name("Samsung SSD 1TB")
                            .price(8000.0)
                            .description("External storage device")
                            .seller("Samsung")
                            .stock(20)
                            .ratings(4.7)
                            .numberOfReviews(160)
                            .category(accessories)
                            .build(),

                    Product.builder()
                            .name("Anker Power Bank")
                            .price(2500.0)
                            .description("Fast charging power bank")
                            .seller("Anker")
                            .stock(40)
                            .ratings(4.4)
                            .numberOfReviews(210)
                            .category(accessories)
                            .build(),

                    Product.builder()
                            .name("Mi Smart Band 8")
                            .price(3500.0)
                            .description("Fitness tracker")
                            .seller("Xiaomi")
                            .stock(35)
                            .ratings(4.2)
                            .numberOfReviews(170)
                            .category(accessories)
                            .build(),

                    Product.builder()
                            .name("Canon EOS 1500D")
                            .price(45000.0)
                            .description("DSLR Camera")
                            .seller("Canon")
                            .stock(8)
                            .ratings(4.5)
                            .numberOfReviews(75)
                            .category(electronics)
                            .build(),

                    Product.builder()
                            .name("GoPro Hero 12")
                            .price(50000.0)
                            .description("Action camera")
                            .seller("GoPro")
                            .stock(6)
                            .ratings(4.6)
                            .numberOfReviews(65)
                            .category(electronics)
                            .build(),

                    Product.builder()
                            .name("Amazon Echo Dot")
                            .price(5000.0)
                            .description("Smart speaker with Alexa")
                            .seller("Amazon")
                            .stock(25)
                            .ratings(4.3)
                            .numberOfReviews(190)
                            .category(electronics)
                            .build()
            );
            productRepository.saveAll(demoproducts);
            System.out.println("Products seeded!!");
        }
        else{
            System.out.println("Skipping seeding !!");
        }
    }
}
