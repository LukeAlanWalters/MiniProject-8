/**
 * @author Luke Walters
 */

import java.io.FileNotFoundException;
import java.io.IOException;



public class BrailleASCII {

    /**
     * The main method that takes in two arguments and switches the given text accordingly
     * @param args - takes in two arguments
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {

        BrailleASCIITables runner = new BrailleASCIITables();

        String holder = "";
        String holder2 = "";

        if(args.length != 2){
            System.err.println("Please give two command line prompts in the form of (target char set) and (current char set characters)");
            return;
        }

        else if (args[0].equals("braille")){
            for(int i = 0; i < args[1].length(); i++){
                holder = holder.concat(runner.toBraille(args[1].charAt(i)));
            }
            System.out.println(holder);
            //System.out.println();
        }

        else if(args[0].equals("ascii")){
            for(int i = 0; i < args[1].length(); i=(i+6)){
                holder = holder.concat(runner.toASCII(args[1].substring(i, i + 6)));
            }
            System.out.println(holder);
        }

        else if(args[0].equals("unicode")){
            if(!(Character.isDigit(args[1].charAt(0)))){
                for(int i = 0; i < args[1].length(); i++){
                    holder = holder.concat(runner.toBraille(args[1].charAt(i)));
                }
                for(int i = 0; i < (holder.length()); i=(i+6)){
                    //System.out.println(Character.toChars(Integer.parseInt(runner.toUnicode(args[1].substring(i, i + 6)))));
                    holder2 = holder2.concat(String.valueOf(Character.toChars(Integer.parseInt(runner.toUnicode(holder.substring(i, i + 6))))));
                    System.out.println(holder2.substring(1,holder2.length()));
                }
            } else {
                for(int i = 0; i < args[1].length(); i=(i+6)){
                    holder2 = holder2.concat(String.valueOf(Character.toChars(Integer.parseInt(runner.toUnicode(args[1].substring(i, i + 6))))));
                }
            
            }
            System.out.println(holder2);
        } else {
            System.err.println("Please give a valid input for the desired conversion type");
            return;
        }//else
    }//main()
}
