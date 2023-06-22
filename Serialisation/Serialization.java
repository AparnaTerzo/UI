package Assessment;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Serialization{
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the State of Sofa whether old or new : ");
            String sofa = sc.next();

            System.out.print("Enter the chair count: ");
            int chair = sc.nextInt();

            System.out.print("Enter the State of fridge whether old or new: ");
            String fridge = sc.next();

            System.out.print("Enter the State of tv whether old or new: ");
            String tv = sc.next();

            System.out.println( "Enter the State of bed whether old or new: ");
            String bed = sc.next();

            Home home = new Home(chair,fridge,tv,bed,sofa);

            FileOutputStream fout = new FileOutputStream("output.ser");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(home);
            out.flush();

            out.close();
            System.out.println("Serialization and deserialization has been done successfullly executed");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
