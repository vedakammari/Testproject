import javax.swing.JOptionPane;
import java.util.ArrayList;

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class Ecommerce {
    static ArrayList<Product> cart = new ArrayList<>();
    static double totalAmount = 0;

    public static void main(String[] args) {
        while (true) {
            String menu = "===== MAIN CATEGORIES =====\n" +
                          "1. Home Baker\n" +
                          "2. Electronics\n" +
                          "3. Fashion\n" +
                          "4. Household\n" +
                          "5. Cosmetics\n" +
                          "6. Checkout\n" +
                          "7. Exit\n" +
                          "---";
            
            String input = JOptionPane.showInputDialog(menu);
            if (input == null) break;

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> homeBaker();
                case 2 -> electronics();
                case 3 -> fashion();
                case 4 -> household();
                case 5 -> cosmetics();
                case 6 -> checkout();
                case 7 -> System.exit(0);
            }
        }
    }

    // =================== HOME BAKER ===================
    static void homeBaker() {
        String menu = "--- Home Baker ---\n" +
                      "1. Cakes\n" +
                      "2. Desserts\n" +
                      "3. Pastry\n" +
                      "4. Biscuits\n" +
                      "5. Back\n" +
                      "---";

        int ch = Integer.parseInt(JOptionPane.showInputDialog(menu));

        switch (ch) {
            case 1 -> showProducts(new String[]{
                "Chocolate Cake", "Vanilla Cake", "Black Forest Cake", "White Forest Cake",
                "Red Velvet Cake", "Pineapple Cake", "Butterscotch Cake",
                "Strawberry Cake", "Fruit Cake", "Coffee Cake"
            });
            case 2 -> showProducts(new String[]{
                "Brownies", "Cupcakes", "Muffins", "Donuts", "Cheesecake",
                "Ice Cream", "Pudding", "Custard", "Tiramisu", "Mousse"
            });
            case 3 -> showProducts(new String[]{
                "Chocolate Pastry", "Black Forest Pastry", "Pineapple Pastry",
                "Butterscotch Pastry", "Strawberry Pastry", "Vanilla Cream Pastry",
                "Red Velvet Pastry", "Coffee Pastry", "Fruit Pastry", "Caramel Pastry"
            });
            case 4 -> showProducts(new String[]{
                "Butter Biscuit", "Chocolate Biscuit", "Oat Biscuit", "Digestive Biscuit",
                "Almond Biscuit", "Cashew Biscuit", "Honey Biscuit",
                "Milk Biscuit", "Cream Biscuit", "Sugar Free Biscuit"
            });
        }
    }

    // =================== ELECTRONICS ===================
    static void electronics() {
        String menu = "--- Electronics ---\n" +
                      "1. Phones\n" +
                      "2. Tablets\n" +
                      "3. Headphones\n" +
                      "4. Smart Watches\n" +
                      "5. Back\n" +
                      "---";

        int ch = Integer.parseInt(JOptionPane.showInputDialog(menu));

        switch (ch) {
            case 1 -> showProducts(new String[]{
                "iPhone 15", "Samsung Galaxy S23", "OnePlus 11", "Redmi Note 13",
                "Realme GT Neo", "Vivo V27", "Oppo Reno 10",
                "Nothing Phone 2", "Motorola Edge 40", "Poco X5 Pro"
            });
            case 2 -> showProducts(new String[]{
                "iPad 10th Gen", "Samsung Galaxy Tab S9", "Lenovo Tab P11",
                "Realme Pad", "Redmi Pad", "Xiaomi Pad 6",
                "OnePlus Pad", "Honor Pad", "Nokia T21", "Micromax Canvas"
            });
            case 3 -> showProducts(new String[]{
                "Boat Rockerz", "Sony WH-1000XM4", "JBL Tune 760",
                "Noise Cancelling 450", "Sennheiser HD 450",
                "Realme Buds", "OnePlus Buds", "Apple AirPods",
                "Skullcandy Crusher", "Bose QC45"
            });
            case 4 -> showProducts(new String[]{
                "Apple Watch S8", "Samsung Galaxy Watch 6", "Noise ColorFit",
                "Firebolt Ninja", "Boat Xtend", "Amazfit GTS",
                "Fossil Gen 6", "Fitbit Versa", "Garmin Venu", "Realme Watch"
            });
        }
    }

    static void fashion() {
        showProducts(new String[]{
            "BIBA Kurta", "Roadster Shirt", "Levis Jeans", "Nike T-Shirt",
            "Adidas Track Pant", "Puma Hoodie", "Allen Solly Blazer",
            "Zara Top", "H&M Dress", "FabIndia Saree"
        });
    }

    static void household() {
        showProducts(new String[]{
            "Floor Cleaner", "Bathroom Cleaner", "Broom Stick",
            "Dustbin", "Mop Set", "Cleaning Cloth",
            "Scrubber", "Liquid Detergent", "Hand Wash", "Phenyl"
        });
    }

    static void cosmetics() {
        showProducts(new String[]{
            "Lakme Cream", "Mamaearth Moisturizer", "Plum Serum",
            "Biotique Lotion", "Cetaphil Cleanser", "Minimalist Serum",
            "Nivea Soft", "Ponds Gel", "Himalaya Cream", "WOW Face Gel"
        });
    }

    // =================== PRODUCT DISPLAY ===================
    static void showProducts(String[] names) {
        Product[] products = new Product[10];
        StringBuilder list = new StringBuilder("PRODUCTS\n\n");

        for (int i = 0; i < 10; i++) {
            products[i] = new Product(names[i], 500 + i * 150);
            list.append(i + 1).append(". ").append(products[i].name).append("\n");
        }

        list.append("\nSelect product number (0 to back): ");
        int sel = Integer.parseInt(JOptionPane.showInputDialog(list.toString()));

        if (sel > 0 && sel <= 10) {
            productOptions(products[sel - 1]);
        }
    }

    // =================== PRODUCT OPTIONS ===================
    static void productOptions(Product p) {
        String menu = """
                --- %s ---
                1. Add to Cart
                2. Buy Now
                3. Back
                """.formatted(p.name);

        int op = Integer.parseInt(JOptionPane.showInputDialog(menu));

        if (op == 1) {
            cart.add(p);
            totalAmount += p.price;
            JOptionPane.showMessageDialog(null, "Added to cart");
        } else if (op == 2) {
            payment(p);
        }
    }

    // =================== PAYMENT ===================
    static void payment(Product p) {
        String menu = """
                Payment Options
                1. UPI
                2. Net Banking
                3. Cash on Delivery
                """;

        int pay = Integer.parseInt(JOptionPane.showInputDialog(menu));

        if (pay == 1) {
            JOptionPane.showInputDialog("Enter Name:");
            JOptionPane.showInputDialog("Enter UPI Number:");
        } else if (pay == 2) {
            JOptionPane.showInputDialog("Account Holder Name:");
            JOptionPane.showInputDialog("Account Number:");
            JOptionPane.showInputDialog("IFSC Code:");
        } else if (pay == 3) {
            JOptionPane.showInputDialog("Enter Address:");
            JOptionPane.showInputDialog("Enter Phone Number:");
        }

        JOptionPane.showMessageDialog(null, "Payment Successful for " + p.name);
    }

    // =================== CHECKOUT ===================
    static void checkout() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cart is empty!");
            return;
        }

        StringBuilder bill = new StringBuilder("RECEIPT\n\n");
        for (Product p : cart) {
            bill.append(p.name).append(" - ").append(p.price).append("\n");
        }

        double gst = totalAmount * 0.18;
        double grandTotal = totalAmount + gst;

        bill.append("\nSub Total: ").append(totalAmount);
        bill.append("\nGST (18%): ").append(gst);
        bill.append("\nTotal: ").append(grandTotal);

        JOptionPane.showMessageDialog(null, bill.toString());
        cart.clear();
        totalAmount = 0;
    }
}