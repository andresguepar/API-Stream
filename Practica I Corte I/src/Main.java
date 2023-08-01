import domain.enums.ClientType;
import domain.enums.OrderType;
import domain.enums.ProductType;
import domain.models.Customer;
import domain.models.Order;
import domain.models.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class Main {
    public static void main(String[] args) {

        List<Product> products = initializeProducts();
        List<Customer> customers = initializeCustomer();
        List<Order> orders = initializeOrder(products,customers);

        System.out.println(products);

        String option = "0";

        do{
            Scanner sc = new Scanner(System.in);

            System.out.printf("\n⟡ MENU ⟡" +
                    "\n1. List of products higher than $5.00" +
                    "\n2. List of products for category Frezee" +
                    "\n3. List of products with 10%% of discount" +
                    "\n4. List of product ordered by a client Level 2" +
                    "\n5. List of minimun price of beaked products" +
                    "\n6. List of lastest orders" +
                    "\n7. Total order in 2023-02-02" +
                    "\n8. Average price in 2023-04-28" +
                    "\n9. Data map" +
                    "\n10. Maximun price per category" +
                    "\n11. Exit" +
                    "\nChoose your optionn");

            option = sc.next();

            switch (option){

                case "1": {

                    List<Product> lisByPrice = products.stream()
                            .filter(p -> p.getPrice() > 5.00)
                            .filter(p -> p.getCategory().equals(ProductType.BEAKED))
                            .collect(toList());
                    System.out.println("\nList of products higher than $5.00 of category beaked");
                    System.out.println(lisByPrice);
                    break;
                }
                case "2": {

                    List<Product> listByCategory = products.stream()
                            .filter(p -> p.getCategory().equals(ProductType.FREZEE))
                            .toList();
                    System.out.println("\nList of products for category Frezee");
                    System.out.println(listByCategory);
                    break;
                }
                case "3": {

                    getDiscountList(products);
                    break;
                }
                case "4": {

                    listDate(orders);
                    break;
                }
                case "5": {

                    Optional<Product> lisMinPrice = products.stream()
                            .filter(p -> p.getCategory().equals(ProductType.BEAKED))
                            .min(Comparator.comparing(Product::getPrice));

                    System.out.println("\nList minimun price of beaked products");
                    System.out.println(lisMinPrice);
                    break;
                }
                case "6": {

                    listRecentOrders(orders);
                    break;
                }
                case "7": {

                    double totalPriceOrder = orders.stream()
                            .filter(o -> o.getOrderDate()
                                    .isEqual(LocalDate.of(2023, 02, 02)))
                            .flatMap(o -> o.getProducts()
                                    .stream())
                            .mapToDouble(o-> o.getPrice())
                            .sum();
                    System.out.println("\nTotal order in 2023-02-02: $" + totalPriceOrder);
                    break;
                }
                case "8": {

                    OptionalDouble averagePriceOrder = OptionalDouble.of(orders.stream()
                            .filter(o -> o.getOrderDate()
                                    .isEqual(LocalDate.of(2023, 04, 28)))
                            .flatMap(o -> o.getProducts().stream())
                            .mapToDouble(o -> o.getPrice())
                            .average()
                            .getAsDouble());
                    System.out.println("\nAverage price in 2023-04-28: $" + averagePriceOrder);
                    break;
                }
                case "9": {

                    System.out.println(mapOrder(orders));
                    break;
                }
                case "10": {
                    expensiveProductByCategory(products);
                    break;
                }
                case "11": {
                    option = "11";
                    break;
                }
                default:
                    System.out.println( "\n" +
                            "I ∧,,,∧   ~ ┏━━━━━━━━┓\n" +
                            "(  ̳• · • ̳)   ~ ♡  Invalid Option   ♡\n" +
                            "/       づ  ~ ┗━━━━━━━━┛     ");
            }

        }while (!option.equals("11"));

    }

//------------- Initializers ---------------
    public static List<Customer> initializeCustomer() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Wu Bi", ClientType.LEVEL1));
        customers.add(new Customer(2, "Su Yu", ClientType.LEVEL2));
        customers.add(new Customer(3, "Cho", ClientType.LEVEL3));
        return customers;
    }

    public static List<Product> initializeProducts() {
        List<Product> products = new ArrayList<Product>();

        products.add(new Product(1, "Cake", ProductType.BEAKED, 15.00));
        products.add(new Product(2, "Ice Cream", ProductType.FREZEE, 4.50));
        products.add(new Product(3, "Sorbet", ProductType.BEVERAGE, 3.00));
        products.add(new Product(4, "lemon Pie", ProductType.BEAKED, 12.50));
        products.add(new Product(5, "Popsicles", ProductType.FREZEE, 1.75));
        products.add(new Product(6, "Cookies", ProductType.BEAKED, 5.00));
        products.add(new Product(7, "Jelly Beans", ProductType.CANDY, 2.50));
        products.add(new Product(8, "Milkshake", ProductType.BEVERAGE, 6.00));
        products.add(new Product(9, "Marshmallows", ProductType.CANDY, 3.50));
        products.add(new Product(10, "Macarons", ProductType.BEAKED, 2.00));
        return products;
    }

    public static List<Order> initializeOrder(List<Product> products,List<Customer> customers) {
        List<Order> orders = new ArrayList<Order>();
        Customer customer1 = customers.get(0);
        Customer customer2 = customers.get(1);
        Customer customer3 = customers.get(2);

        List<Product> proOrder1 = new ArrayList<Product>();
        proOrder1.add(products.get(0));
        proOrder1.add(products.get(1));
        proOrder1.add(products.get(3));

        Order order1 = new Order(1,OrderType.DELIVERED, LocalDate.of(2023,02,28),
                LocalDate.of(2023,03,04),proOrder1,customer1);
        orders.add(order1);

        List<Product> proOrder2 = new ArrayList<>();
        proOrder2.add(products.get(4));
        proOrder2.add(products.get(5));
        proOrder2.add(products.get(9));

        Order order2 = new Order(1,OrderType.PREPARING, LocalDate.of(2023,03,18),
                LocalDate.of(2023,03,26),proOrder2,customer2);
        orders.add(order2);

        List<Product> proOrder3 = new ArrayList<>();
        proOrder3.add(products.get(8));
        proOrder3.add(products.get(9));
        proOrder3.add(products.get(6));

        Order order3 = new Order(1,OrderType.DELIVERED, LocalDate.of(2023,02,02),
                LocalDate.of(2023,02,21),proOrder3,customer3);
        orders.add(order3);

        List<Product> proOrder4 = new ArrayList<>();
        proOrder4.add(products.get(2));
        proOrder4.add(products.get(4));
        proOrder4.add(products.get(8));

        Order order4 = new Order(1,OrderType.ON_WAY, LocalDate.of(2023,05,12),
                LocalDate.of(2023,05,21),proOrder4,customer1);
        orders.add(order4);

        List<Product> proOrder5 = new ArrayList<>();
        proOrder5.add(products.get(1));
        proOrder5.add(products.get(3));
        proOrder5.add(products.get(9));

        Order order5 = new Order(1,OrderType.PREPARING, LocalDate.of(2023,04,28),
                LocalDate.of(2023,05,15),proOrder5,customer3);
        orders.add(order5);

        return orders;
    }

    //Exercise 3
    public  static void  getDiscountList (List<Product> products){
        List<Product>  productsDiscount = products.stream()
                .filter(p -> p.getCategory().equals(ProductType.CANDY))
                .map(p -> {
                    p.setPrice(p.getDiscount());
                return p;
                })
                .collect(toList());

        System.out.println("\nList of products with 10% of discount");
        productsDiscount.forEach(System.out::println);
    }

    //Exercise 4
    public  static void listDate(List<Order> orders) {
        List<Order> orderByClient = orders.stream()
                .filter(o -> o.getCustomer().getTier().equals(ClientType.LEVEL2))
                .filter(o -> o.getOrderDate().isAfter(LocalDate.of(2023,02,01)))
                .filter(p -> p.getDeliveryDate().isBefore(LocalDate.of(2023,04,01)))
                .toList();

        List<Product> productByClient = orderByClient.stream()
                .flatMap(o -> o.getProducts().stream())
                .toList();

        System.out.println("\nProduct ordered between 2023-02-01 2023-04-01 by a client Level 2");
        productByClient.forEach(System.out::println);

    }

    //Exercise 6
    public  static void listRecentOrders(List<Order> orders){
        List<Order> recentOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(2)
                .toList();

        System.out.println("\nList of lastest orders");
        recentOrders.forEach(System.out::println);

    }

    //Exercise 9
    public static Map<Customer, List<Order>> mapOrder (List<Order> orders){
        return orders.stream()
                .collect(Collectors
                        .groupingBy(Order::getCustomer,HashMap::new,Collectors.toList()));

    }

    //Exercise 10
   public static void expensiveProductByCategory (List<Product> products){
       Map<String, Product> expensiveProduct = new HashMap<>();
       for (Product product : products) {
           expensiveProduct
                   .merge(String.valueOf(product.getCategory()), product,
                           (e, r) -> e.getPrice() > r.getPrice() ? e : r);
       }
       expensiveProduct.values().forEach(System.out::println);
   }

    

}