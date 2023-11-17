import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * @author Luke Walters
 * Worked on this file with Kevin Johanson
 */


public class BrailleASCIITables{

    // +--------+------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The bits used to construct the BitTree being Braille and the associated Values being ASCII
     */
    BitTree BrailleToASCII;

    /**
     * The bits used to construct the BitTree being Braille and the associated Values being Unicode
     */
    BitTree BrailleToUnicode;

    /**
     * The bits used to construct the BitTree being ASCII and the associated Values being Braille
     */
    BitTree ASCIIToBraille;
    
    // +--------------+------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Initializes the fields so that they can be traversed and used for character conversion.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    public BrailleASCIITables() throws FileNotFoundException, IOException, Exception{
        

        this.BrailleToASCII = new BitTree(6); 
        this.BrailleToASCII.load(getClass().getResourceAsStream("BrailleToASCII.txt"));

        this.BrailleToUnicode = new BitTree(6);
        this.BrailleToUnicode.load(getClass().getResourceAsStream("BrailleToUnicode.txt"));

        this.ASCIIToBraille = new BitTree(8);
        this.ASCIIToBraille.load(getClass().getResourceAsStream("ASCIIToBraille.txt"));
    }//BrailleASCIITables()



    // +---------+------------------------------------------------
    // | Method |
    // +--------+

    /**
     * Converts a ASCII char to Braille bits
     * @param letter - ASCII char 
     * @return - String
     * @throws Exception
     */
    public String toBraille(char letter) throws Exception{
        String holder = "0";

        holder = holder.concat(Integer.toBinaryString(letter));
        

        return this.ASCIIToBraille.get(holder);
    }//toBraille()


    /**
     * Converts Braille to ASCII
     * @param bits
     * @return
     * @throws Exception
     */
    public String toASCII(String bits) throws Exception{
        return this.BrailleToASCII.get(bits);
    }

    public String toUnicode(String bits) throws Exception{
        return this.BrailleToUnicode.get(bits);
    }




    
}