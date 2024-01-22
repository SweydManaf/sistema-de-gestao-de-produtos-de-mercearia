package console;

import controllers.ProductController;
import entities.Product;

import java.time.LocalDate;
import java.util.List;

public class Console {
    public static void main(String[] args) throws Exception {
        ProductController controller = new ProductController();
//        createProduct(controller);
        getAllProducts(controller);
    }

    public static void createProduct(ProductController controller) throws Exception{
        Product product = new Product("feijao", "comida", 20, LocalDate.of(2024, 1, 20) , LocalDate.now());
        controller.create(product);
    }

    public static void getAllProducts(ProductController controller) throws Exception{
        List<Product> products = controller.getAll();
        for (int i = 0; i < products.size(); i++){
            System.out.println((i+1) + "o -> " + products.get(i).toString());
        }
    }
}
