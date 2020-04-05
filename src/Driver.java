/*
Noel Romero
Nxr170030
CS 3345.004
 */
import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.*;

/**
 * Driver
 * @description Driver class where data is inputted into in the RB tree
 * and results are sent to a .txt file
 */
public class Driver {
    public static void main(String[] args)
    {
        if (args.length!=2) {
            System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
            System.exit(0);

        }

        try{
            List<String> list = Files.readAllLines(new File(args[0]).toPath());

            File output_file = new File(args[1]);
            PrintWriter  out;
            out = new PrintWriter(output_file);
            RedBlackTree tree = new RedBlackTree();
            boolean result;
            if(list.get(0).compareToIgnoreCase("Integer") == 0) // check to see if the data being inserted is to be a integer
            {
                for(String operation: list)
                {
                    if(!(operation.compareToIgnoreCase("Integer") == 0))
                    {
                        operation = operation.replaceAll("\\s+","");
                        String numberOnly = operation.replaceAll("[^0-9]", "");
                        try {
                            if (numberOnly.length() >= 1) {
                                int index = operation.indexOf(":");
                                int negative = operation.indexOf("-");

                                String command = operation.substring(0, index);
                                int value = Integer.parseInt(numberOnly);
                                if(negative >= 0)
                                {
                                    value *= -1;
                                }

                                switch (command) {
                                    case "Insert":
                                        result = tree.insert(value);
                                        out.println(result ? "True" : "False");
                                        break;

                                    case "Contains":
                                        result = tree.contains(value);
                                        out.println(result ? "True" : "False");
                                        break;

                                    case "PrintTree":
                                        out.println(tree.toString());
                                        break;

                                    default:
                                        out.println("ERROR in Line: " + operation);

                                }
                            } else {
                                if(operation.compareToIgnoreCase("PrintTree") == 0)
                                    out.println(tree.toString());
                                else
                                    out.println("Error in line: " + operation);
                            }
                        }
                        catch (NullPointerException e)
                        {
                            out.println("Error in insert:"+e.getMessage());
                        }
                    }

                }
            }
            else if(list.get(0).compareToIgnoreCase("String") ==0) // case where data being entered to RB tree to be a string
            {
                for(String operation : list)
                {
                    if(operation.compareToIgnoreCase("String") != 0)
                    {
                        operation = operation.replaceAll("\\s+","");
                        int index = operation.indexOf(":");


                        if(index > 0) {
                            String command = operation.substring(0,index);
                            String name = operation.substring(index+1);
                            try {


                                switch (command) {
                                    case "Insert":
                                        result = tree.insert(name);
                                        out.println(result ? "True" : "False");
                                        break;

                                    case "Contains":
                                        result = tree.contains(name);
                                        out.println(result ? "True" : "False");
                                        break;


                                    default:
                                        out.println("ERROR in Line: " + operation);

                                }
                            }
                            catch (NullPointerException e)
                            {
                                out.println("Error in insert:"+e.getMessage());
                            }
                        }
                        else
                        {
                            if(operation.compareToIgnoreCase("PrintTree")==0)
                            {
                                out.println(tree.toString());
                            }
                            else
                                out.println("ERROR in Line: " + operation);
                        }


                    }
                }
            }
            else
            {
                out.println("Can't work with the object in your output File program will end");
            }


            out.close();

        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }

    }




}
