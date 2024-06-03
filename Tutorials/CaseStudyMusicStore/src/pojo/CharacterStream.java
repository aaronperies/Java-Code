package pojo;

import java.io.*;
import java.util.Scanner;

public class CharacterStream {
    public static void main(String[] args) throws IOException {

        File file = new File("Product.txt");    //writing to the file
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(file, true);    //now I have acquired the append feature
            pw = new PrintWriter(fw, true);     //as well as the autoFlush feature
            pw.println("Aaron P.");
            pw.println(20);

        } catch (IOException e) {
            System.err.println("Error occurred" + e.getMessage());
            throw e;
        } finally {
            try {
                fw.close();
                pw.close();
            } catch (IOException e) {
                System.err.println("Error occurred" + e.getMessage());
                throw e;
            }
        }

        //reading the file
        /*try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String name = sc.nextLine();
                System.out.println("The name is " + name);

                int age = sc.nextInt();
                System.out.println("The age is " + age);
                sc.nextLine();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }*/

        FileReader fr=null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = null;

            while ((line = br.readLine()) != null){
                System.out.println("The name is " + line);
                int age = Integer.parseInt(br.readLine());
                System.out.println("The age is " + age);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}