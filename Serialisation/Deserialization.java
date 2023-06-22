package Assessment;


import java.io.*;
class Deserialization{
    public static void main(String[] args) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("output.ser"));

            Home home;
            home=(Home) in.readObject();


            System.out.println(home);

            in.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}