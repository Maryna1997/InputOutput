package com.chornobai.marina;

import java.io.*;
import java.util.ArrayList;


public class App
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        Hobby hobby1 = new Hobby("Football", 6, true);
        Hobby hobby2 = new Hobby("Running", 8, false);
        Hobby hobby3 = new Hobby("English", 10, true);

        ArrayList<Hobby> listHobby = new ArrayList<>();
        listHobby.add(hobby1);
        listHobby.add(hobby2);
        listHobby.add(hobby3);

        File fileIn = new File(".\\src\\main\\java\\com\\chornobai\\marina\\in.bin");
        if(!fileIn.exists()) {
            fileIn.createNewFile();
        }

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileIn));
        objectOutputStream.writeObject(listHobby);

        File dir = new File(".\\src\\main\\java\\com\\chornobai\\marina\\copy");
        dir.mkdir();

        File fileOut = new File(".\\src\\main\\java\\com\\chornobai\\marina\\copy\\out.bin");
        if(!fileOut.exists()){
            fileOut.createNewFile();
        }

        try (InputStream inst = new FileInputStream(fileIn); OutputStream outst = new FileOutputStream(fileOut)) {
            byte[] buffer = new byte[2048];
            int length;
            while ((length = inst.read(buffer)) > 0) {
                outst.write(buffer, 0, length);
            }
        }

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileOut));
        ArrayList<Hobby> newListHobby = (ArrayList<Hobby>) objectInputStream.readObject();
        System.out.println(newListHobby);
    }
}
