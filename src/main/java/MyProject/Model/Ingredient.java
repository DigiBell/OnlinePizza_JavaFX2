package MyProject.Model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Ingredient {
    private ObjectId id;
    @BsonProperty(value="ingredient_id")
    private int ingredientId;
    private String name;
    private String description;
    private int quantity;
    private String units;

    public Ingredient() { }

    public Ingredient(int ingredientId, String name, String description, int quantity, String units) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.units = units;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientId=" + ingredientId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", units='" + units + '\'' +
                '}';
    }
}
