package periodictable;

/**
 * Example uses of classes in java
 * This program takes data from a text file and reads in the data.
 * @author Kevin
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

class Element // Contents of th element to store in the array
{
        int atomicNumber;
        String elementName;
        String elementAbbrev;
        float atomicMass;
}
public class PeriodicTable
{
        public static Element[] loadTable() throws FileNotFoundException // loads the data from text file
        {
                File file = new File("PeriodicTable.txt");
                Scanner inputFile = new Scanner(file);
                ArrayList<Element> arrayListPT = new ArrayList<Element>();
                while(inputFile.hasNext())
                {
                    Element e = new Element();
                    e.atomicNumber = inputFile.nextInt();
                    e.elementAbbrev = inputFile.next();
                    e.atomicMass = inputFile.nextFloat();
                    e.elementName = inputFile.next();
                    arrayListPT.add(e);
                }
                inputFile.close();
                Element[] arrayPT = new Element[arrayListPT.size()];

                for (int index = 0; index < arrayPT.length; index++)
                {
                        arrayPT[index] = arrayListPT.get(index);
                }
                return arrayPT;
        }
        public static void printTable(Element[] pTable)//Displaying the table that has been loaded using loadTable
        {
                float sum = 0;
                float avg;
                String aNumber = "ANo"; // Note to self - Occupies 3 spaces
                String eName = "Name"; // Occupies 21 spaces
                String eAbbrev = "Abr"; // Occupies 3 spaces
                String aMass = "Mass"; // Occupies 7 Spaces
                System.out.println("Number of elements: " + pTable.length + "\n");
                System.out.printf("%3s %-21s %-3s %7s\n", aNumber, eName, eAbbrev, aMass); //Note to self - formatting the STRINGS being displayed is left justified
                System.out.println("--- --------------------- --- -------");
                for(int index = 0; index < pTable.length; index++)
                {
                        printElement(pTable, index);
                        sum += pTable[index].atomicMass;
                }
                avg = sum / pTable.length;
                System.out.printf("The average mass: %.2f\n", avg);
        }
        public static void printElement(Element[] pTable, int index)
        {
                System.out.printf("%3d %-21s %-3s %7.2f\n", pTable[index].atomicNumber, pTable[index].elementName, pTable[index].elementAbbrev, pTable[index].atomicMass);
        }
        public static int findAtomicNumber(Element[] pTable, int an)
        {
                boolean numFound = false;
                int index = 0;
                while(!numFound && index < pTable.length)
                {
                        if(pTable[index].atomicNumber == an)
                        {
                                numFound = true;
                        }
                        else
                        {
                                index++;
                        }
                }
                if(numFound == true)
                {
                        return index;
                }
                else
                {
                        return -1;
                }
        }
        public static int findAbbreviation(Element[] pTable, String ab)
        {
                boolean abbrevFound = false;
                int index = 0;
                while(!abbrevFound && index < pTable.length)
                {
                        if(ab.equalsIgnoreCase(pTable[index].elementAbbrev))
                        {
                                abbrevFound = true;
                        }
                        else
                        {
                                index++;
                        }
                }
                if(abbrevFound == true)
                {
                        return index;
                }
                else
                {
                        return -1;
                }
        }
        public static int findName(Element[] pTable, String n)
        {
                boolean nameFound = false;
                int index = 0;
                while(!nameFound && index < pTable.length)
                {
                        if(n.equalsIgnoreCase(pTable[index].elementName))
                        {
                                nameFound = true;
                        }
                        else
                        {
                                index++;
                        }
                }
                if(nameFound == true)
                {
                        return index;
                }
                else
                {
                        return -1;
                }
        }
        public static void main(String[] args) throws FileNotFoundException
        {
                int index = 0;
                Element[] pTable = loadTable();
                if(args.length != 1)
                {
                        System.out.println("Periodic Table by K. Ly\n");
                }
                else if(args[0].equalsIgnoreCase("print") && index < pTable.length){
                        System.out.println("Periodic Table by K. Ly\n");
                        printTable(pTable);
                }
                else
                {
                        try     // Self-note: Validating command and to catch any exception error
                        {
                                int an = Integer.parseInt(args[0]);
                                index = findAtomicNumber(pTable, an);
                                if(index == -1)
                                {
                                        System.out.println("Error: \"" + args[0] + "\" unknown");
                                }
                                if(index != -1)
                                {
                                        printElement(pTable, index);
                                }
                        }
                        catch(NumberFormatException e)
                        {
                                index = findName(pTable, args[0]);
                                if(index != -1)
                                {
                                        printElement(pTable, index);
                                }
                                if(index == -1)
                                {
                                        index = findAbbreviation(pTable, args[0]);
                                        if(index == -1)
                                        {
                                                System.out.println("Error:\"" + args[0] + "\" unknown");
                                        }
                                        if(index != -1)
                                        {
                                                printElement(pTable, index);
                                        }
                                }
                        }
                }
        }
}
