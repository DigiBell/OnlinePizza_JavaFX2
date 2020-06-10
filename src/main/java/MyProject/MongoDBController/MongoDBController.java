package MyProject.MongoDBController;

import MyProject.MainController;
import MyProject.Model.Account;
import MyProject.Model.Ingredient;
import MyProject.Model.Product;
import MyProject.Model.Order;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDBController {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MainController mainController;
    private final static MongoDBController mongoDBController = new MongoDBController();

    private MongoDBController(){
        initializeDBConnection(); }

    public static MongoDBController getMongoDBControllerInstance() {
        return mongoDBController;
    }

//    public static void main(String[] args){
//        initializeDBConnection();
//        System.out.println("\nInitialization complete");
//
//        mongoClient.getDatabase("OnlinePizza").getCollection("Products").drop();
//        System.out.println("\nCollection Products deleted");
//
//        mongoClient.getDatabase("OnlinePizza").createCollection("Products");
//        System.out.println("\nCollections Products created");
//
//        sendProducts();
//        System.out.println("\nProducts care sent");
//        //sendAccount(mainController.generateManager());
//    }


    private static void initializeDBConnection() {
        mainController = MainController.getMainControllerInstance();
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        String connectStr = "mongodb+srv://Anna_a:mongodbtest20@clusterproject-2hdjy.mongodb.net/test?retryWrites=true&w=majority";
        ConnectionString connectionString = new ConnectionString(connectStr);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        mongoClient = MongoClients.create(clientSettings);
        database = mongoClient.getDatabase("OnlinePizza");
    }

    //DELETE ALL COLLECTIONS
    private static void deleteCollections() {
        //mongoClient.getDatabase("OnlinePizza").getCollection("Accounts").drop();
        //mongoClient.getDatabase("OnlinePizza").getCollection("Ingredients").drop();
        //mongoClient.getDatabase("OnlinePizza").getCollection("Products").drop();
        //mongoClient.getDatabase("OnlinePizza").getCollection("Sales").drop();
        //mongoClient.getDatabase("OnlinePizza").getCollection("Orders").drop();
    }

    //CREATE COLLECTIONS
    private static void createCollections() {
        //mongoClient.getDatabase("OnlinePizza").createCollection("Accounts");
        //mongoClient.getDatabase("OnlinePizza").createCollection("Products");
        //mongoClient.getDatabase("OnlinePizza").createCollection("Ingredients");
        //mongoClient.getDatabase("OnlinePizza").createCollection("Sales");
        //mongoClient.getDatabase("OnlinePizza").createCollection("Orders");
    }

    //SEND PRODUCTS
    private static void sendProducts() {
        MongoCollection<Product> products = database.getCollection("Products", Product.class);
        products.insertMany(mainController.generateTestData());//Products
    }

    //GET PRODUCTS
    public List<Product> getProducts() {
        MongoCollection<Product> products = database.getCollection("Products", Product.class);
        try {
            List<Product> productList = products.find().into(new ArrayList<>());
//            System.out.println(productList.toString());
//            mainController.setProductList(productList);
            return productList;
        }catch(NullPointerException n){
            return null;
        }
    }

    //SEND INGREDIENTS
    private static void sendIngredients() {
        MongoCollection<Ingredient> ingredients = database.getCollection("Ingredients", Ingredient.class);
        ingredients.insertMany(mainController.generateTestDataIngredients());
    }

    //GET INGREDIENTS
    public static List<Ingredient> getIngredients() {
        MongoCollection<Ingredient> ingredients = database.getCollection("Ingredients", Ingredient.class);
        try {
            List<Ingredient> ingredientList = ingredients.find().into(new ArrayList<>());
            return ingredientList;
        }catch(NullPointerException n){
            return null;
        }
    }

    //SEND ACCOUNT DOCUMENT
    public static void sendAccount(Account account) {
        MongoCollection<Account> accounts = database.getCollection("Accounts", Account.class);
        accounts.insertOne(account);
    }

    //GET ACCOUNT DOCUMENT
    public Account getAccount(String email, String password) {
        MongoCollection<Account> accounts = database.getCollection("Accounts", Account.class);
        Account account;
        try{
            account = accounts.find(and(eq("email", email), eq("password", password))).first();
            return account;
        }catch(NullPointerException n){
            return null;
        }
    }

    //SEND ORDER DOCUMENT
    private static void sendOrderDocument() {//send sale document
        MongoCollection<Order> orders = database.getCollection("Orders", Order.class);
        //orders.insertOne();
    }

    //DELETE A DOCUMENTC
    private static void deleteDocuments() {
        MongoCollection<Document> pizzaMenu = mongoClient.getDatabase("OnlinePizze").getCollection("PizzaMenu");
        pizzaMenu.deleteMany(new Document("pizza_name", "BBQ Pizza"));
    }

    //UPDATE A DOCUMENT
    private static void updateDocuments() {
        MongoCollection<Document> pizzaMenu = mongoClient.getDatabase("OnlinePizze").getCollection("PizzaMenu");
        pizzaMenu.updateMany(new Document(), Updates.set("pizza_price", 100));
    }

}

