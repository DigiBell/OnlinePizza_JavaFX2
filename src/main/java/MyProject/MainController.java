package MyProject;

import MyProject.Model.*;
import MyProject.MongoDBController.MongoDBController;
import javafx.collections.ObservableList;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

// SINGLETON
public class MainController {
    public final static int MAIN_STAGE_HEIGHT = 700;
    public final static int MAIN_STAGE_WIDTH = 900;
    public final static String CUSTOMER_ACCESS_LEVEL = "customer";
    public final static String EMPLOYEE_ACCESS_LEVEL = "employee";
    public final static String MANAGER_ACCESS_LEVEL = "manager";
    private Random rand = new Random();

    private Account loginAccount;

    //CUSTOMER VARIABLES
    private Order order;
    private Product productSelected;
    private OrderLine orderLine;
    private String cartLine;
    private Double totalPrice = 0.0;
    private List<OrderLine> orderLineList = new ArrayList<>();
    private List<Product> productListCustomer = new ArrayList<>();
    private List<Product> productsExtraCMC = new ArrayList<>();
    private List<Product> productsExtraOther = new ArrayList<>();
    private List<Sale> saleListToAdd = new ArrayList<>();
    private ObservableList<String> cartLineList; //lines in the Cart listView

    //MANAGER VARIABLES
    private List<Order> orderList = new ArrayList<>(); //orders from database
    private List<Sale> saleList = new ArrayList<>(); //sales from database
    private List<Ingredient> ingredientList = new ArrayList<>(); //ingredients from database
    private Ingredient ingredientToChange;
    private List<Product> productList = new ArrayList<>(); //products from database, both pizza and non-pizza
    private List<Document> topSales = new ArrayList<>();
    private ObservableList<String> topSalesLineList;


    private final static MainController mainControllerInstance = new MainController();
    private MongoDBController mongoDBController = MongoDBController.getMongoDBControllerInstance();


    public static MainController getMainControllerInstance() {
        return mainControllerInstance;
    }

    ////////////////////////////////////////////----GENERATE METHODS----///////////////////////////////////

    public Account generateManager(){
        Account account = new Account((rand.nextInt(2000)+1), "marta@gmail.com", "marta",  MANAGER_ACCESS_LEVEL, new Date(),
                "Marta", "Smith", "Sweden", "Helsingborg",
                "Tranemansgatan 23", "234156", "0703245658");
        return account;
    }

    public List<Product> generateTestData(){
        productList = new ArrayList<>();

        Product product = new Product(111, "Pizza", "Basic", "Cheese and tomato sauce – and nothing else! \nOst, tomatsås – och bara det! ", "20", "cm", 49, true, null);
        productList.add(product);
        product = new Product(112, "Pizza", "Basic", "Cheese and tomato sauce – and nothing else! \nOst, tomatsås – och bara det! ","30",  "cm", 79, true, null);
        productList.add(product);
        product = new Product(113, "Pizza", "Basic", "Cheese and tomato sauce – and nothing else! \nOst, tomatsås – och bara det! ", "40", "cm", 119, true, null);
        productList.add(product);
        product = new Product(114, "Pizza", "Basic (Glutenfree)", "Cheese and tomato sauce – and nothing else! \nOst, tomatsås – och bara det! ", "30","cm", 139, true, null);
        productList.add(product);
        product = new Product(115, "Pizza", "HAWAII 5-0", "Cheese, tomato sauce, minced meat, onion and pineapple \nOst, tomatsås, köttfärs, lök och ananas", "20", "cm", 65, true, null);
        productList.add(product);
        product = new Product(116, "Pizza", "HAWAII 5-0", "Cheese, tomato sauce, minced meat, onion and pineapple \nOst, tomatsås, köttfärs, lök och ananas", "30","cm", 105, true, null);
        productList.add(product);
        product = new Product(117, "Pizza", "HAWAII 5-0", "Cheese, tomato sauce, minced meat, onion and pineapple \nOst, tomatsås, köttfärs, lök och ananas", "40","cm", 159, true, null);
        productList.add(product);
        product = new Product(118, "Pizza", "HAWAII 5-0 (Glutenfree)", "Cheese, tomato sauce, minced meat, onion and pineapple \nOst, tomatsås, köttfärs, lök och ananas", "30", "cm", 139, true, null);
        productList.add(product);
        product = new Product(119, "Pizza", "SVINGOD", "Cheese, tomato sauce, ham and mushroom \nOst, tomatsås, skinka och champinjoner",  "20", "cm", 59, true, null);
        productList.add(product);
        product = new Product(120, "Pizza", "SVINGOD", "Cheese, tomato sauce, ham and mushroom \nOst, tomatsås, skinka och champinjoner",  "30", "cm", 99, true, null);
        productList.add(product);
        product = new Product(121, "Pizza", "SVINGOD", "Cheese, tomato sauce, ham and mushroom \nOst, tomatsås, skinka och champinjoner",  "40", "cm", 155, true, null);
        productList.add(product);
        product = new Product(122, "Pizza", "SVINGOD (Glutenfri)", "Cheese, tomato sauce, ham and mushroom \nOst, tomatsås, skinka och champinjoner",  "30", "cm", 139, true, null);
        productList.add(product);
        product = new Product(123, "Pizza", "PIZZABAKEREN SPECIAL", "Cheese, tomato sauce, minced meat, onion and bacon \nOst, tomatsås, köttfärs, lök och bacon", "20", "cm", 65, true, null);
        productList.add(product);
        product = new Product(124, "Pizza", "PIZZABAKEREN SPECIAL", "Cheese, tomato sauce, minced meat, onion and bacon \nOst, tomatsås, köttfärs, lök och bacon", "30", "cm", 105, true, null);
        productList.add(product);
        product = new Product(125, "Pizza", "PIZZABAKEREN SPECIAL", "Cheese, tomato sauce, minced meat, onion and bacon \nOst, tomatsås, köttfärs, lök och bacon", "40", "cm", 159, true, null);
        productList.add(product);
        product = new Product(126, "Pizza", "PIZZABAKEREN SPECIAL (Glutenfri)", "Cheese, tomato sauce, minced meat, onion and bacon \nOst, tomatsås, köttfärs, lök och bacon", "30", "cm", 139, true, null);
        productList.add(product);
        product = new Product(127, "Pizza", "EL MEXICO", "Cheese, tomato sauce, strips of marinated chicken, marinated beef, nacho chips, garlic, sweet corn and chili \nOst, tomatsås, marinerad kyckling, marinerad biff, nachoschips, vitlök, majs och chili","20",  "cm", 69, true, null);
        productList.add(product);
        product = new Product(128, "Pizza", "EL MEXICO", "Cheese, tomato sauce, strips of marinated chicken, marinated beef, nacho chips, garlic, sweet corn and chili \nOst, tomatsås, marinerad kyckling, marinerad biff, nachoschips, vitlök, majs och chili","30",  "cm", 115, true, null);
        productList.add(product);
        product = new Product(129, "Pizza", "EL MEXICO", "Cheese, tomato sauce, strips of marinated chicken, marinated beef, nacho chips, garlic, sweet corn and chili \nOst, tomatsås, marinerad kyckling, marinerad biff, nachoschips, vitlök, majs och chili","40",  "cm", 165, true, null);
        productList.add(product);
        product = new Product(130, "Pizza", "EL MEXICO (Glutenfri)", "Cheese, tomato sauce, strips of marinated chicken, marinated beef, nacho chips, garlic, sweet corn and chili \nOst, tomatsås, marinerad kyckling, marinerad biff, nachoschips, vitlök, majs och chili","30",  "cm", 139, true, null);
        productList.add(product);
        product = new Product(131, "Pizza", "FLAMMAN", "Cheese, taco sauce, minced meat, nacho chips and jalapeños \nOst, tacosås, köttfärs, nachochips och jalapeños","20",  "cm", 65, true, null);
        productList.add(product);
        product = new Product(132, "Pizza", "FLAMMAN", "Cheese, taco sauce, minced meat, nacho chips and jalapeños \nOst, tacosås, köttfärs, nachochips och jalapeños", "30", "cm", 105, true, null);
        productList.add(product);
        product = new Product(133, "Pizza", "FLAMMAN", "Cheese, taco sauce, minced meat, nacho chips and jalapeños \nOst, tacosås, köttfärs, nachochips och jalapeños","40",  "cm", 159, true, null);
        productList.add(product);
        product = new Product(134, "Pizza", "FLAMMAN (Glutenfri)", "Cheese, taco sauce, minced meat, nacho chips and jalapeños \nOst, tacosås, köttfärs, nachochips och jalapeños","30",  "cm", 139, true, null);
        productList.add(product);
        product = new Product(135, "Pizza", "TACOKYCKLINGEN", "Cheese, taco sauce, marinated chicken, nacho chips and jalapeños \nOst, tacosås, marinerad kyckling, nachoschips och jalapeños","20",  "cm", 69, true, null);
        productList.add(product);
        product = new Product(136, "Pizza", "TACOKYCKLINGEN", "Cheese, taco sauce, marinated chicken, nacho chips and jalapeños \nOst, tacosås, marinerad kyckling, nachoschips och jalapeños","30",  "cm", 115, true, null);
        productList.add(product);
        product = new Product(137, "Pizza", "TACOKYCKLINGEN", "Cheese, taco sauce, marinated chicken, nacho chips and jalapeños \nOst, tacosås, marinerad kyckling, nachoschips och jalapeños","40",  "cm", 165, true, null);
        productList.add(product);
        product = new Product(138, "Pizza", "TACOKYCKLINGEN (Glutenfri)", "Cheese, taco sauce, marinated chicken, nacho chips and jalapeños \nOst, tacosås, marinerad kyckling, nachoschips och jalapeños", "30", "cm", 139, true, null);
        productList.add(product);
        product = new Product(211, "Beverages", "CocaCola Classic", null, "0.33", "L", 15, true, 100);
        productList.add(product);
        product = new Product(212, "Beverages", "CocaCola Classic", null, "0.5", "L", 25, true, 100);
        productList.add(product);
        product = new Product(213, "Beverages", "CocaCola Classic", null, "1.5", "L", 35, true, 100);
        productList.add(product);
        product = new Product(214, "Beverages", "REDBULL", null, "0.25", "L", 29, true, 100);
        productList.add(product);
        product = new Product(311, "IceCream", "BEN & JERRY'S Classic", null, "0.5", "L", 79, true, 100);
        productList.add(product);
        product = new Product(312, "IceCream", "BEN & JERRY'S Core", null, "0.5", "L", 89, true, 100);
        productList.add(product);
        product = new Product(411, "Salad", "PIZZASALLAD", null, "250", "g", 15, true, 100);
        productList.add(product);
        product = new Product(511, "Sauce", "PB DIPPSÅS", null, "50", "ml", 8, true, 100);
        productList.add(product);
        product = new Product(611, "Extras", "Extra Ost/kött/kyckling", "Extra Cheese/meat/chicken", "20","cm", 10, true, null);
        productList.add(product);
        product = new Product(612, "Extras", "Extra Ost/kött/kyckling", "Extra Cheese/meat/chicken", "30","cm", 15, true, null);
        productList.add(product);
        product = new Product(613, "Extras", "Extra Ost/kött/kyckling", "Extra Cheese/meat/chicken", "40","cm", 20, true, null);
        productList.add(product);
        product = new Product(614, "Extras", "Extra Övrigt", "Other", "20","cm", 5, true, null);
        productList.add(product);
        product = new Product(615, "Extras", "Extra Övrigt", "Other", "30","cm", 10, true, null);
        productList.add(product);
        product = new Product(616, "Extras", "Extra Övrigt", "Other", "40","cm", 15, true, null);
        productList.add(product);

        return productList;
    }

    public List<Ingredient> generateTestDataIngredients(){
        List<Ingredient> ingredientList = new ArrayList<>();

        Ingredient ingredient = new Ingredient(131, "Red paprika", "package 5 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(132, "Chiken breast", "frozen, package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(133, "Ananas", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(134, "Tomato sauce", "package 1 liter", 10, "L");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(135, "Cheese Mozarella", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(136, "Jalapeños", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(137, "Dough", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(138, "Dough(Glutenfree)", "package 5 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(139, "Nacho chips", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(140, "Taco sauce", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(141, "Beef", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(142, "Garlic", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(143, "Sweet corn", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(144, "Chili peppar", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(145, "Minced meat", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(146, "Onion", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(147, "Mashroom", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(148, "Bacon", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);
        ingredient = new Ingredient(149, "Ham", "package 1 kg", 10, "kg");
        ingredientList.add(ingredient);

        return ingredientList;
    }

    //////////////////////////////////////////---DATABASE METHODS---///////////////////////////////////

    public boolean sendAccountToDatabase(Account account){
        account.setUserId((rand.nextInt(4999)+1));
        boolean result = mongoDBController.sendAccount(account);
        return result;
    }

    public boolean sendOrderToDatabase(Order order){
        order.setOrderId((rand.nextInt(5000)+5000));
        boolean resultOrder = mongoDBController.sendOrder(order);
        boolean resultSales = sendSalesToDatabase(order);
        mongoDBController.updateProduct(getSaleListToAdd());
        if(resultOrder && resultSales){
            return true;
        }else{
            return false;
        }
    }

    public boolean sendSalesToDatabase(Order order){
        //Create Sale document and insert into Sales
        List<OrderLine> orderLineList = getOrderLineList();
        System.out.println("\norder list\n" + orderLineList.toString());
        int numberOfProducts = orderLineList.size();
        List<Sale> saleList = new ArrayList<>();
        for (int i = 0; i < numberOfProducts; i++) {
            Sale sale = new Sale();
            sale.setOrderId(order.getOrderId());
            sale.setDate(order.getDate());
            sale.setProductId(orderLineList.get(i).getProductId());
            sale.setProductName(orderLineList.get(i).getProductName());
            sale.setProductCategory(orderLineList.get(i).getProductCategory());
            sale.setQuantity(1);
            sale.setPrice(orderLineList.get(i).getTotalPrice());
            saleList.add(sale);
            System.out.println("Sale document: " + sale.toString());
        }
        setSaleListToAdd(saleList);
        //INSERT SALE DOCUMENTS MANY (1 FOR EACH PRODUCT)
        return mongoDBController.sendSales(saleList);
    }

    public boolean findAccountByEmailPassword(String email, String password){
        this.loginAccount = mongoDBController.getAccount(email, password);
        if(loginAccount == null){
            return false;
        }else{
            setLoginAccount(loginAccount);
            return true;
        }
    }

    public boolean findAccountByEmail(String email){
        return mongoDBController.getAccount(email);
    }

    public void getProductsFromDatabase(){
        this.productList = mongoDBController.getProducts();
        Product productCopy;
        List<Product> newProducts = new ArrayList<>();

        for (Product product: productList) {
            productCopy = product;
            if(product.getName().equals("Extra Ost/kött/kyckling")){
                productsExtraCMC.add(productCopy);
            }
            if(product.getName().equals("Extra Övrigt")){
                productsExtraOther.add(productCopy);
            }
            if(!product.getCategory().equals("Extras")){
                newProducts.add(productCopy);
            }
        }
        this.productListCustomer = newProducts;
    }

    public void getIngredientsFromDatabase(){
        this.ingredientList = mongoDBController.getIngredients();
    }

    public boolean updateIngredient( int quantity){
       return mongoDBController.updateIngredinet(getIngredientToChange(), quantity);
    }

    public void getOrdersFromDatabase(){
        this.orderList = mongoDBController.getOrders();
    }

    public void getOrdersFromDatabase(Date from, Date to){
        this.orderList = mongoDBController.getOrders(from, to);
    }

    public void getSalesFromDatabase(){ this.saleList = mongoDBController.getSales(); }

    public void getSalesFromDatabase(Date from, Date to){ this.saleList = mongoDBController.getSales(from, to); }

    public void getTopSalesFromDatabase(Date dateFrom, Date dateTo){ this.topSales = mongoDBController.getTopSales(dateFrom, dateTo); }

    //////////////////////////////////////////-----HELP METHODS-----///////////////////////////////////

    public void clearCart(){
        setOrder(new Order());
        setProductSelected(new Product());
        setOrderLine(new OrderLine());
        setCartLine("");
        setCartLineList(null);
        getOrderLineList().clear();
        getProductsExtraCMC().clear();
        getProductsExtraOther().clear();
        setTotalPrice(0.0);
    }

    public void clearDataCustomer(){
        getProductListCustomer().clear();
        getProductsExtraCMC().clear();
        getProductsExtraOther().clear();
        getSaleListToAdd().clear();
        clearCart();
    }

    public void clearDataManager(){
        setLoginAccount(new Account());
        getOrderList().clear();
        getSaleList().clear();
        getIngredientList().clear();
        getProductList().clear();
    }

    //////////////////////////////////////////----GETTERS & SETTERS----//////////////////////////////////

    public Account getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(Account loginAccount) {
        this.loginAccount = loginAccount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProductSelected() {
        System.out.println("\nget product selected");
        return productSelected; }

    public void setProductSelected(Product productSelected) {
        System.out.println("\nset product selected");
        this.productSelected = productSelected; }

    public OrderLine getOrderLine() { return orderLine; }

    public void setOrderLine(OrderLine orderLine) { this.orderLine = orderLine; }

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }

    public List<Product> getProductListCustomer() { return productListCustomer; }

    public void setProductListCustomer(List<Product> productListCustomer) { this.productListCustomer = productListCustomer; }

    public List<Product> getProductsExtraCMC() {
        return productsExtraCMC;
    }

    public void setProductsExtraCMC(List<Product> productsExtraCMC) {
        this.productsExtraCMC = productsExtraCMC;
    }

    public List<Product> getProductsExtraOther() {
        return productsExtraOther;
    }

    public void setProductsExtraOther(List<Product> productsExtraOther) {
        this.productsExtraOther = productsExtraOther;
    }

    public List<Sale> getSaleListToAdd() {
        return saleListToAdd;
    }

    public void setSaleListToAdd(List<Sale> saleListToAdd) {
        this.saleListToAdd = saleListToAdd;
    }

    public Double getTotalPrice() {
        System.out.println("\ngetTotalPrice:" + totalPrice);
        return totalPrice; }

    public void setTotalPrice(Double totalPrice) {

        System.out.println("\nsetTotalPrice:" + totalPrice);
        this.totalPrice = totalPrice;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredients) {
        this.ingredientList = ingredients;
    }

    public Ingredient getIngredientToChange() { return ingredientToChange; }

    public void setIngredientToChange(Ingredient ingredientToChange) { this.ingredientToChange = ingredientToChange; }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) { this.productList.addAll(productList); }

    public List<Document> getTopSales() {
        return topSales;
    }

    public void setTopSales(List<Document> topSales) {
        this.topSales = topSales;
    }

    public ObservableList<String> getTopSalesLineList() {
        return topSalesLineList;
    }

    public void setTopSalesLineList(ObservableList<String> topSalesLineList) {
        this.topSalesLineList = topSalesLineList;
    }

    public String getCartLine() { return cartLine; }

    public void setCartLine(String cartLine) { this.cartLine = cartLine; }

    public ObservableList<String> getCartLineList() {
        System.out.println("getCartLineList");
        return cartLineList;
    }

    public void setCartLineList(ObservableList<String> cartLineList) {
        System.out.println("getCartLineList");
        this.cartLineList = cartLineList;
    }
}
