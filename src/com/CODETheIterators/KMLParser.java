package com.CODETheIterators;

import com.google.android.gms.maps.model.LatLng;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: Markus
 * Date: 3/1/14
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class KMLParser {
    //Declarations
    private File kmlFile;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private DataInputStream dis;

    private DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder dBuilder;

    {
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private ArrayList<LatLng> coords;

    //Constructor
    public KMLParser(String address){
    //Definitions
        kmlFile = new File(address);
        coords = new ArrayList<LatLng>();
        fis = null;
        bis = null;
        dis = null;
        MakeCoords();
    }
    public ArrayList<LatLng> GetCoords(){
        return coords;
    }
    public void MakeCoords(){
        if (kmlFile.canRead()){
            try{
                fis = new FileInputStream(kmlFile);

                // Here BufferedInputStream is Added for fast reading.
                bis = new BufferedInputStream(fis);
                dis = new DataInputStream(bis);

                while (dis.available() != 0){
                    String newline;
                    int count = 0;
                    double numberlist[] = new double[2];
                    StringTokenizer st;
                 /// DO WORK HERE
                    //example: System.out.println(dis.readLine());

                    if(dis.readLine().matches("[0-9]+(,[0-9])")){
                        // read the next line into a string object if it matches (double, double), then seperate the string into tokens seperated by commas. Lastly add any doubles to an array to save them for use in coordinates
                        newline = dis.readLine();
                        st = new StringTokenizer(newline, "'");
                        while (st.hasMoreTokens()){
                            numberlist[count] = Double.parseDouble(st.nextToken());
                            count++;
                        }
                        // add the coordinate we've pulled from the kml file to the coords arrayList
                        coords.add(new LatLng(numberlist[0], numberlist[1]));
                    }
                }

                //dispose of all the resources after using them
                fis.close();
                bis.close();
                dis.close();

            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        else
            System.out.println("couldn't read kml file");

    }

}
