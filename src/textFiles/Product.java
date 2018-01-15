package textFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import textFiles.Item;

public class Product {

    //////////////////////////////////////////////
    //////////// FIELDS (ATTRIBUTES) /////////////
    //////////////////////////////////////////////

    private String quantity;

    private String productName;

    private String price;

    public List<Product> execute(String filePath) throws IOException {

        FileReader fr = new FileReader(filePath);

        BufferedReader br = new BufferedReader(fr);

        List<String> textData = new ArrayList<>();

        List<List<String>> twoDimensionList = new ArrayList<>();

        int lines = Item.readLines(filePath);

        textData = Item.openFile(lines, filePath);

        twoDimensionList = Item.createTwoDimensionList(textData);

        return productFactory(twoDimensionList);

    }

    //////////////////////////////////////////////
    //////////// INITIALIZER METHOD //////////////
    //////////////////////////////////////////////
    public Product() {

    }

    public Product(String quantity, String productName, String price) {

        this.quantity = quantity;

        this.productName = productName;

        this.price = price;
    }

    //////////////////////////////////////////////
    //////////////////////////////////////////////
    //////////////////////////////////////////////
    public List<Product> productFactory(List<List<String>> twoDimensionList) throws IOException {

        List<Product> shoppingProducts = new ArrayList<Product>();

        for (List<String> oneDimensionList : twoDimensionList) {

            shoppingProducts.add(productGenerator(oneDimensionList));
        }

        return shoppingProducts;
    }

    //////////////////////////////////////////////
    //////////////////////////////////////////////
    //////////////////////////////////////////////
    private Product productGenerator(List<String> properties) {

        int lastItem = (properties.size() - 1);

        // ASSIGN QUANTITY VALUE.
        quantity = properties.get(0);

        // ASSIGN PRICE VALUE.
        price = properties.get(lastItem);

        ////////////////////////////////////////
        for (String property : properties) {

            ///////////////////////////////////////////////////////////////////////////////////////////////
            if ((properties.indexOf(property) != 0) && (properties.indexOf(property) != lastItem)) {

                // ASSIGN PRODUCT-NAME;
                productName = String.join(" ", properties);

            }
        }

        return new Product(quantity, productName, price);
    }

    //////////////////////////////////////////////
    //////////////////////////////////////////////
    //////////////////////////////////////////////
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    //////////////////////////////////////////////
    //////////////////////////////////////////////
    //////////////////////////////////////////////
    public String getQuantity() {

        return this.quantity;
    }

    public String getProductName() {

        return this.productName;
    }

    public String getPrice() {

        return this.price;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // THE MAIN METHOD WHERE I WILL RUN MY PROGRAM. ////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        String fileName = "/Users/andersoncardoso/Desktop/test.txt";

        Product prod = new Product();

        try {

            System.out.println(prod.execute(fileName));

        } catch (Error e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}