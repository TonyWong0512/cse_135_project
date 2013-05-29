import java.util.Scanner;
import java.util.Vector;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.io.File;
import java.lang.Math;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator {
	private final static String[] PRODUCTS = {""};
	private final static String[] CATEGORIES = {"chair", "table", "computer", "keyboard", "burger", "french fries",
        "bike", "helmet", "couch", "mouse", "dog", "cat", "fish", "cell phone", "sweater", "pants", "shirt", "underwear",
        "sandwich", "bagel", "glove", "hat", "wheel", "car", "jacket", "coat", "monitor", "pen", "pencil", "paper", "notebook",
        "backpack", "umbrella", "surfboard", "box", "cabinet", "trunk", "shoe", "sandal", "glasses", "hoodie",
        "flowers", "sponge", "myster object", "spaghetti sauce", "beef", "fish", "pork", "shrimp", "scallops"};

	private final static String[] ADJECTIVES = {"smelly", "old", "antiquated", "tall", "short", "narrow", 
		"wide", "hoodrat", "turrible", "indestructible", "sexy", "invisible", "barf-inducing", "rustic", 
		"slimy", "hairy", "new", "sparkly", "ugly", "pretty", "perfect", "impeccable", "scratched", "moldy", "wet",
		"ancient", "modern", "cheap", "expensive", "strong", "weak", "boring", "best", "worst", "good", 
		"bad", "large", "fast", "strange", "sleek", "smooth", "bumpy", "rough", "jagged", "spikey", "warped", "pointy",
        "tiny", "microscopic", "giant"
		}; 
	private final static String[] COLORS = {"white", "blue", "green", "red", "yellow", "black", "brown", 
		"purple", "teal", "orange", "beige", "tan", "maroon", "burgundy", "crimson", "amber", "bronze", 
		"fuchsia", "ruby", "aqua", "grey", "pink", "olive", "violet", "chartreuse", "chestnut", "salmon", 
		"khaki", "coral", "magenta", "lavender", "indigo", "navy", "rose", "silver", "bronze", "gold", 
		"copper", "plum", "turquoise", "periwinkle"};

    private final static String[] STATES = {"AL", "AK", "AZ",
"AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};

    private static int getDaysInMonth(int m)
    {
        assert(m >= 1 && m <= 12);

        if(m == 1 || m == 3 || m == 5 || m == 7 || m == 9 || m == 10 || m == 12) return 31;
        else if (m == 4 || m == 6 || m == 8 || m == 11) return 30;
        else return 28;
    }

    private static void getNames(String path, Vector<String> vec)
    {
        try{
            Scanner reader = new Scanner(new File(path));
            while(reader.hasNextLine())
            {
                vec.add((String) reader.nextLine().trim());
            }
        } 
        catch(Exception e)
        {
            System.out.println("Couldn't getNames for file: " + path);
        }
    }
    
    private static void createCategoriesTable(PrintWriter pw)
    {
        pw.println("CREATE TABLE Categories (id SERIAL PRIMARY KEY, name TEXT NOT NULL, description TEXT);");
        for(int i = 0; i < CATEGORIES.length; i++)
        {
            pw.println("INSERT INTO Categories (name, description) VALUES ('" + CATEGORIES[i] + "', '" + CATEGORIES[i] + " description');");
        }
    }

    private static Object[] createProductsTable(PrintWriter pw)
    {
        final int NUM_PRODUCTS = 10000;
        final int MAX_PRICE = 100000; // 1000 dollars

        pw.println("-- price is given as product's price * 100 so that it will always be an int");
        pw.println("CREATE TABLE Products (sku SERIAL PRIMARY KEY, name TEXT NOT NULL UNIQUE, cat_id INT REFERENCES Categories (id) NOT NULL, price INT NOT NULL);");
    
        Set<Product> ps = new HashSet();
        while(ps.size() < NUM_PRODUCTS)
        {
            int catId = (int)(Math.random() * CATEGORIES.length);
            String cat = CATEGORIES[catId];
            String adj = ADJECTIVES[(int)(Math.random() * ADJECTIVES.length)];
            String color = COLORS[(int)(Math.random() * COLORS.length)];
            String name = adj + " " + color + " " + cat;
            int price = (int)(Math.random() * MAX_PRICE);
            Product p = new Product(name, catId + 1, price); // + 1 because database will have indexing starting from 1

            int suffix = 1;
            while(ps.contains(p))
            {
                p.name = p.name + " " + Integer.toString(suffix++);
            }
            
            ps.add(p);
        }

        Object[] pArray = ps.toArray();
        for(int i = 0; i < pArray.length; i++)
        {
            Product p = (Product) pArray[i];
            pw.println("INSERT INTO Products (name, cat_id, price) VALUES ('" + p.name + "', " + p.catId + ", " + p.price + ");");
        }

        return pArray;
    }

    private static void createCustomersTable(PrintWriter pw, Vector<String> firstNames,
        Vector<String> lastNames, int N)
    {
        pw.println("CREATE TABLE Customers (id SERIAL PRIMARY KEY, name TEXT NOT NULL, age INT NOT NULL, state CHARACTER(2) NOT NULL);");
    
        int totalCustomers = 1000000 * N;
        final int MAX_AGE = 120;
        Set<Customer> customers = new HashSet();

        while(customers.size() < totalCustomers)
        {
            // create a customer
            String fname = firstNames.get((int) (Math.random() * firstNames.size()));
            String mname = lastNames.get((int) (Math.random() * lastNames.size()));
            String lname = lastNames.get((int) (Math.random() * lastNames.size()));
            String name = fname + " " + mname + " " + lname;
            String st = STATES[(int) (Math.random() * STATES.length)];
            int age = (int) (Math.random() * MAX_AGE);

            Customer c = new Customer(name, st, age);
            int suffix = 1;
            while (customers.contains(c))
            {
                c.name = c.name + " " + Integer.toString(suffix++);// change name to have a unique number
            }            

            customers.add(c);
        }

        Iterator<Customer> iter = customers.iterator();
        while(iter.hasNext())
        {
            Customer c = iter.next();
            pw.println("INSERT INTO Customers (name, age, state) VALUES ('" + c.name + "', " + c.age + ", '" + c.state + "');");
        }
        pw.flush();
    }

    // needs productsLength, needs customersLength 
    private static void createSalesTable(PrintWriter pw, Object[] pArr, int N)
    {
        final int MAX_QUANT = 10;
        final int NUM_MONTHS = 12;
        final int NUM_SALES = N * 100000000;
        final int NUM_PRODUCTS_PER_CUSTOMER = 20;
        final int NUM_SALES_PER_PRODUCT_PER_CUSTOMER = 5;
        int ns = 0;
        
        pw.println("CREATE TABLE Sales (id SERIAL PRIMARY KEY, product_id INT REFERENCES Products (sku) NOT NULL, customer_id INT REFERENCES Customers (id) NOT NULL, day INT NOT NULL, month INT NOT NULL, quantity INT NOT NULL, total_cost INT NOT NULL);");

        int numQueries = 0;
        for(int curCustomer = 1; curCustomer <= N*1000000; curCustomer++)
        {
            for(int i = 0; i < NUM_PRODUCTS_PER_CUSTOMER; i++)
            {
                int pId = (int)(Math.random() * pArr.length);
                Product product = (Product) pArr[pId];

                for(int j = 0; j < NUM_SALES_PER_PRODUCT_PER_CUSTOMER; j++)
                {
                    int month = (int)(Math.random() * 12) + 1;
                    int day = (int)(Math.random() * getDaysInMonth(month)) + 1;
                    int quantity = (int)(Math.random() * MAX_QUANT) + 1;

                    int totalPrice = product.price * quantity; 

                    pw.println("INSERT INTO Sales (product_id, customer_id, day, month, quantity, total_cost) VALUES " + 
                        "(" + (pId + 1) + ", " + curCustomer + ", " + day + ", " + month + ", " + quantity + ", " + totalPrice +");");
                    
                    numQueries++;
                }
            }
        }
        assert(numQueries == NUM_SALES);
    }
	
// Math.random() * 5
/*
* a customer table with N * 1M tuples with uniform and independent distribution over age groups and states
* 50 product categories
* a product table with 10K products
* a sales table with N * 100M columns (Product, Customer, Date, Quantity), with dates uniformly distributed over last year, quantity uniformly distributed over 1-10.
Each customer purchases only 20 randomly selected products and makes 5*20 purchases during the year (5*20 = 100M / 1M)
*/
	public static void main(String[] args)
	{
        Vector<String> firstNames = new Vector();
        Vector<String> lastNames = new Vector();
        String outDir = new String("");
        
        getNames("FirstNames.txt", firstNames);
        getNames("LastNames.txt", lastNames);

        for(String first : firstNames)
        {
            System.out.println("\t" + first);
        }

		int n = -1;
		int m = -1;

        System.out.println("size of CATEGORIES: " + CATEGORIES.length);
        System.out.println("size of ADJECTIVES: " + ADJECTIVES.length);
		System.out.println("size of COLORS:" + COLORS.length);
		System.out.println("size of firstNames:" + firstNames.size());

		Scanner reader = new Scanner(System.in);
		while(n < 0)
		{
			System.out.println("Please specify an N value, where #Customers = N * 1000000 and #Sales = N * 100000000:");
			try
			{
				n = Integer.parseInt(reader.nextLine().trim());
			}
			catch(Exception e)
			{
				System.out.println("Try again.");
			}
		}
		System.out.println("n was chosen to be: " + n);
		
        while(outDir.length() == 0)
        {
            System.out.println("Please specify an output directory");
            try
            {
                outDir = reader.nextLine().trim();
            }
            catch(Exception e)
            {
                System.out.println("Try again.");
            }
        }
		System.out.println("outDir was chosen to be: " + outDir);

        try
        {
            PrintWriter pw = null;
            
            pw = new PrintWriter(new FileWriter(outDir + "/Customers.sql"));  
            createCustomersTable(pw, firstNames, lastNames, n);         
            pw.close();

            pw = new PrintWriter(new FileWriter(outDir + "/Categories.sql"));  
            createCategoriesTable(pw);
            pw.close();

            Object[] ps = null;
            pw = new PrintWriter(new FileWriter(outDir + "/Products.sql"));  
            ps = createProductsTable(pw);
            pw.close();
            assert(ps != null);

            pw = new PrintWriter(new FileWriter(outDir + "/Sales.sql"));  
            createSalesTable(pw, ps, n);
            pw.close();

            System.out.println("Success...?");
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Could not write to file: " + outDir);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Failed.");
        }
	}
}
