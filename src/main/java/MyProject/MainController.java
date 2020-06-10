package MyProject;

import MyProject.Model.*;
import MyProject.MongoDBController.MongoDBController;
import javafx.collections.ObservableList;
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
    private Account newAccount;

    private Order order;
    private List<OrderLine> orderLines;
    private List<Product> productsExtraCMC = new ArrayList<>();
    private List<Product> productsExtraOther = new ArrayList<>();
    private Double totalPrice;

    private List<Order> orderList; //orders from database
    private List<Sale> saleList; //sales from database
    private List<Ingredient> ingredients; //ingredients from database
    private List<Product> productList; //products from database, both pizza and non-pizza

    private List<Product> productsInCart; //productList in the productsInCart
    private ObservableList<String> cartLineList; //lines in the Cart listView

    private final static MainController mainControllerInstance = new MainController();
    private MongoDBController mongoDBController = MongoDBController.getMongoDBControllerInstance();


    public static MainController getMainControllerInstance() {
        return mainControllerInstance;
    }

    ////////////////////////////////////////////----TEST METHODS----///////////////////////////////////

    public Account generateManager(){
        Account account = new Account((rand.nextInt(2000)+1), "marta@gmail.com", "marta",  MANAGER_ACCESS_LEVEL, new Date(),
                "Marta", "Smith", "Sweden", "Helsingborg",
                "Tranemansgatan 23", "234156", "0703245658");
        return account;
    }

    public List<Product> generateTestData(){
        productList = new ArrayList<>();

        Product product = new Product(111, "Pizza", "Basic", "Cheese and tomato sauce – and nothing else! \nOst, tomatsås – och bara det! ", 20, "cm", 49, true, null);
        productList.add(product);
        product = new Product(112, "Pizza", "Basic", "Cheese and tomato sauce – and nothing else! \nOst, tomatsås – och bara det! ",30,  "cm", 79, true, null);
        productList.add(product);
        product = new Product(113, "Pizza", "Basic", "Cheese and tomato sauce – and nothing else! \nOst, tomatsås – och bara det! ", 40, "cm", 119, true, null);
        productList.add(product);
        product = new Product(114, "Pizza", "Basic (Glutenfree)", "Cheese and tomato sauce – and nothing else! \nOst, tomatsås – och bara det! ", 30,"cm", 139, true, null);
        productList.add(product);
        product = new Product(115, "Pizza", "HAWAII 5-0", "Cheese, tomato sauce, minced meat, onion and pineapple \nOst, tomatsås, köttfärs, lök och ananas", 20, "cm", 65, true, null);
        productList.add(product);
        product = new Product(116, "Pizza", "HAWAII 5-0", "Cheese, tomato sauce, minced meat, onion and pineapple \nOst, tomatsås, köttfärs, lök och ananas", 30,"cm", 105, true, null);
        productList.add(product);
        product = new Product(117, "Pizza", "HAWAII 5-0", "Cheese, tomato sauce, minced meat, onion and pineapple \nOst, tomatsås, köttfärs, lök och ananas", 40,"cm", 159, true, null);
        productList.add(product);
        product = new Product(118, "Pizza", "HAWAII 5-0 (Glutenfree)", "Cheese, tomato sauce, minced meat, onion and pineapple \nOst, tomatsås, köttfärs, lök och ananas", 30, "cm", 139, true, null);
        productList.add(product);
        product = new Product(119, "Pizza", "SVINGOD", "Cheese, tomato sauce, ham and mushroom \nOst, tomatsås, skinka och champinjoner",  20, "cm", 59, true, null);
        productList.add(product);
        product = new Product(120, "Pizza", "SVINGOD", "Cheese, tomato sauce, ham and mushroom \nOst, tomatsås, skinka och champinjoner",  30, "cm", 99, true, null);
        productList.add(product);
        product = new Product(121, "Pizza", "SVINGOD", "Cheese, tomato sauce, ham and mushroom \nOst, tomatsås, skinka och champinjoner",  40, "cm", 155, true, null);
        productList.add(product);
        product = new Product(122, "Pizza", "SVINGOD (Glutenfri)", "Cheese, tomato sauce, ham and mushroom \nOst, tomatsås, skinka och champinjoner",  30, "cm", 139, true, null);
        productList.add(product);
        product = new Product(123, "Pizza", "PIZZABAKEREN SPECIAL", "Cheese, tomato sauce, minced meat, onion and bacon \nOst, tomatsås, köttfärs,lök och bacon", 20, "cm", 65, true, null);
        productList.add(product);
        product = new Product(124, "Pizza", "PIZZABAKEREN SPECIAL", "Cheese, tomato sauce, minced meat, onion and bacon \nOst, tomatsås, köttfärs,lök och bacon", 30, "cm", 105, true, null);
        productList.add(product);
        product = new Product(125, "Pizza", "PIZZABAKEREN SPECIAL", "Cheese, tomato sauce, minced meat, onion and bacon \nOst, tomatsås, köttfärs,lök och bacon", 40, "cm", 159, true, null);
        productList.add(product);
        product = new Product(126, "Pizza", "PIZZABAKEREN SPECIAL (Glutenfri)", "Cheese, tomato sauce, ham and mushroom \nOst, tomatsås, köttfärs,lök och bacon", 30, "cm", 139, true, null);
        productList.add(product);
        product = new Product(127, "Pizza", "EL MEXICO", "Cheese, tomato sauce, strips of marinated chicken, marinated beef, nacho chips, garlic, sweet corn and chili \nOst, tomatsås, marinerad kyckling, marinerad biff, nachoschips, vitlök, majs och chili",20,  "cm", 69, true, null);
        productList.add(product);
        product = new Product(128, "Pizza", "EL MEXICO", "Cheese, tomato sauce, strips of marinated chicken, marinated beef, nacho chips, garlic, sweet corn and chili \nOst, tomatsås, marinerad kyckling, marinerad biff, nachoschips, vitlök, majs och chili",30,  "cm", 115, true, null);
        productList.add(product);
        product = new Product(129, "Pizza", "EL MEXICO", "Cheese, tomato sauce, strips of marinated chicken, marinated beef, nacho chips, garlic, sweet corn and chili \nOst, tomatsås, marinerad kyckling, marinerad biff, nachoschips, vitlök, majs och chili",40,  "cm", 165, true, null);
        productList.add(product);
        product = new Product(130, "Pizza", "EL MEXICO (Glutenfri)", "Cheese, tomato sauce, strips of marinated chicken, marinated beef, nacho chips, garlic, sweet corn and chili \nOst, tomatsås, marinerad kyckling, marinerad biff, nachoschips, vitlök, majs och chili",30,  "cm", 139, true, null);
        productList.add(product);
        product = new Product(131, "Pizza", "FLAMMAN", "Cheese, taco sauce, minced meat, nacho chips and jalapeños \nOst, tacosås, köttfärs, nachochips och jalapeños",20,  "cm", 65, true, null);
        productList.add(product);
        product = new Product(132, "Pizza", "FLAMMAN", "Cheese, taco sauce, minced meat, nacho chips and jalapeños \nOst, tacosås, köttfärs, nachochips och jalapeños", 30, "cm", 105, true, null);
        productList.add(product);
        product = new Product(133, "Pizza", "FLAMMAN", "Cheese, taco sauce, minced meat, nacho chips and jalapeños \nOst, tacosås, köttfärs, nachochips och jalapeños",40,  "cm", 159, true, null);
        productList.add(product);
        product = new Product(134, "Pizza", "FLAMMAN (Glutenfri)", "Cheese, taco sauce, minced meat, nacho chips and jalapeños \nOst, tacosås, köttfärs, nachochips och jalapeños",30,  "cm", 139, true, null);
        productList.add(product);
        product = new Product(135, "Pizza", "TACOKYCKLINGEN", "Cheese, taco sauce, marinated chicken, nacho chips and jalapeños \nOst, tacosås, marinerad kyckling, nachoschips och jalapeños",20,  "cm", 69, true, null);
        productList.add(product);
        product = new Product(136, "Pizza", "TACOKYCKLINGEN", "Cheese, taco sauce, marinated chicken, nacho chips and jalapeños \nOst, tacosås, marinerad kyckling, nachoschips och jalapeños",30,  "cm", 115, true, null);
        productList.add(product);
        product = new Product(137, "Pizza", "TACOKYCKLINGEN", "Cheese, taco sauce, marinated chicken, nacho chips and jalapeños \nOst, tacosås, marinerad kyckling, nachoschips och jalapeños",40,  "cm", 165, true, null);
        productList.add(product);
        product = new Product(138, "Pizza", "TACOKYCKLINGEN (Glutenfri)", "Cheese, taco sauce, marinated chicken, nacho chips and jalapeños \nOst, tacosås, marinerad kyckling, nachoschips och jalapeños", 30, "cm", 139, true, null);
        productList.add(product);
        product = new Product(211, "Beverages", "CocaCola Classic", null, 0.33, "L", 15, true, 56);
        productList.add(product);
        product = new Product(212, "Beverages", "CocaCola Classic", null, 0.5, "L", 25, true, 35);
        productList.add(product);
        product = new Product(213, "Beverages", "CocaCola Classic", null, 1.5, "L", 35, true, 45);
        productList.add(product);
        product = new Product(214, "Beverages", "REDBULL", null, 0.25, "L", 29, true, 35);
        productList.add(product);
        product = new Product(311, "IceCream", "BEN & JERRY'S Classic", null, 0.5, "L", 79, true, 42);
        productList.add(product);
        product = new Product(312, "IceCream", "BEN & JERRY'S Core", null, 0.5, "L", 89, true, 27);
        productList.add(product);
        product = new Product(411, "Salad", "PIZZASALLAD", null, 250, "g", 15, true, 15);
        productList.add(product);
        product = new Product(511, "Sauce", "PB DIPPSÅS", null, 50, "ml", 8, true, 65);
        productList.add(product);
        product = new Product(611, "Extras", "Extra Ost/kött/kyckling", "Extra Cheese/meat/chicken", 20,"cm", 10, true, null);
        productList.add(product);
        product = new Product(612, "Extras", "Extra Ost/kött/kyckling", "Extra Cheese/meat/chicken", 30,"cm", 15, true, null);
        productList.add(product);
        product = new Product(613, "Extras", "Extra Ost/kött/kyckling", "Extra Cheese/meat/chicken", 40,"cm", 20, true, null);
        productList.add(product);
        product = new Product(614, "Extras", "Extra Övrigt", "Other", 20,"cm", 5, true, null);
        productList.add(product);
        product = new Product(615, "Extras", "Extra Övrigt", "Other", 30,"cm", 5, true, null);
        productList.add(product);
        product = new Product(616, "Extras", "Extra Övrigt", "Other", 40,"cm", 5, true, null);
        productList.add(product);

        return productList;
    }

    public List<Ingredient> generateTestDataIngredients(){
        List<Ingredient> ingredientList = new ArrayList<>();
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientId(131);
        ingredient.setName("Red paprika"); ingredient.setDescription("box 5 kg");
        ingredient.setQuantity(6); ingredient.setUnits("kg");
        ingredientList.add(ingredient);

        ingredient.setIngredientId(132);
        ingredient.setName("Chiken breast"); ingredient.setDescription("frozen, package 1 kg");
        ingredient.setQuantity(10); ingredient.setUnits("kg");
        ingredientList.add(ingredient);

        ingredient.setIngredientId(133);
        ingredient.setName("Ananas"); ingredient.setDescription("box 0.5 kg");
        ingredient.setQuantity(13); ingredient.setUnits("kg");
        ingredientList.add(ingredient);

        ingredient.setIngredientId(134);
        ingredient.setName("Tomato sauce"); ingredient.setDescription("box 1 liter");
        ingredient.setQuantity(20); ingredient.setUnits("liter");
        ingredientList.add(ingredient);

        ingredient.setIngredientId(135);
        ingredient.setName("Cheese Mozarella"); ingredient.setDescription("box 1 kg");
        ingredient.setQuantity(17); ingredient.setUnits("kg");
        ingredientList.add(ingredient);

        ingredient.setIngredientId(136);
        ingredient.setName("Dough"); ingredient.setDescription("box 1 kg");
        ingredient.setQuantity(15); ingredient.setUnits("kg");
        ingredientList.add(ingredient);

        ingredient.setIngredientId(137);
        ingredient.setName("Dough(Glutenfree)"); ingredient.setDescription("box 1 kg");
        ingredient.setQuantity(11); ingredient.setUnits("kg");
        ingredientList.add(ingredient);

        return getIngredients();
    }

    //////////////////////////////////////////---DATABASE METHODS---///////////////////////////////////


    public void sendAccountToDatabase(Account account){
        mongoDBController.sendAccount(generateManager());
    }

    public void sendOrderToDatabase(){
        getOrder();
        //send to database
    }

    public Account findAccount(String email, String password){
        loginAccount = mongoDBController.getAccount(email, password);
        System.out.println("Login account" + loginAccount.toString());
        setLoginAccount(loginAccount);
        return loginAccount;
    }

    public List<Product> getProductsFromDatabase(){
        productList = mongoDBController.getProducts();
        Product productCopy;
        List<Product> newProducts = new ArrayList<>();

        for (Product product: productList) {
            System.out.println("\n product " + product.toString());
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
        productList = newProducts;

        return productList;
    }

    public void getIngredientsFromDatabase(){
        //get from database
        //setIngredients();
    }

    public void getOrdersFromDatabase(){
        //get from database
        //setOrderList();
    }

    public void getSalesFromDatabase(String dateFrom, String dateTo){
        //get saleList made between dateFrom and dateTo
        //setSaleList();
    }

    public void getTotalSalesFromDatabase(String dateFrom, String dateTo){
        //aggregate(sum quantity, price) (group by productId) for saleList made between dateFrom and dateTo
        //setSaleList();
    }

    //////////////////////////////////////////-----HELP METHODS-----///////////////////////////////////

    public void clearCart(){
        getCartLineList().clear();
        getProductsInCart().clear();
        setTotalPrice(0.0);
    }

    public void clearAllData(){
        loginAccount = null;
        newAccount = null;
        order = null;
        orderList = null;
        saleList = null;
        ingredients = null;
        productList = null;
        productsInCart = null;
        cartLineList = null;
        totalPrice = 0.0;
    }

    //////////////////////////////////////////----GETTERS & SETTERS----//////////////////////////////////

    public Account getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(Account loginAccount) {
        this.loginAccount = loginAccount;
    }

    public Account getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(Account newAccount) {
        this.newAccount = newAccount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        System.out.println("\nMainController setProductList");
        System.out.println(productList.toString());
        System.out.println("\nMainController setProductList");
        this.productList.addAll(productList);
        //this.productList = productList;
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(List<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public ObservableList<String> getCartLineList() {
        return cartLineList;
    }

    public void setCartLineList(ObservableList<String> cartLineList) {
        this.cartLineList = cartLineList;
    }
}
