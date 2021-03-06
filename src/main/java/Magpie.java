/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
    /**
     * Get a default greeting
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (findWord(statement, "no") >= 0) {
            response = "Why so negative?";
        }
        else if (findWord(statement, "mother") >= 0
                || findWord(statement, "sister") >= 0
                || findWord(statement, "father") >= 0
                || findWord(statement, "brother") >= 0)
        {
            response = "Tell me more about your family.";
        }
        else if (findWord(statement, "cat") >= 0
                || findWord(statement, "dog") >= 0
                || findWord(statement, "kitten") >= 0
                || findWord(statement, "puppy") >= 0)
        {
            response = "Tell me more about your pets.";
        }
        else if (findWord(statement, "Mr. Finklestein") >= 0
                || findWord(statement, "Kimiko") >= 0
                || findWord(statement, "Nathan") >= 0
                || findWord(statement, "Howard") >= 0)
        {
            response = "They sound like a good teacher.";
        }
        else if (findWord(statement, "soccer") >= 0
                || findWord(statement, "golf") >= 0
                || findWord(statement, "basketball") >= 0
                || findWord(statement, "tennis") >= 0)
        {
            response = "I enjoy that sport too.";
        }
        else if (findWord(statement, "no") >= 0)
        {
            response = "Why not?";
        }
        else if (findWord(statement, "biscuit") >= 0
                || findWord(statement, "lettuce") >= 0
                || findWord(statement, "burger") >= 0
                || findWord(statement, "fries") >= 0)
        {
            response = "That sounds yummy!";
        }
        else if (findWord(statement, "I want") >= 0){
            response = transformIWantStatement(statement);
        }
        else if (findWord(statement, "I want to") >= 0){
            response = transformIWantToStatement(statement);
        }
        else if (findWord(statement, "I like") >= 0){
            response = transformILikeStatement(statement);
        }
        else if (findWord(statement,"I") >= 0 && findWord(statement,"you") >= 0) {
            response = transformIYouStatement(statement);
        }
        else if (findWord(statement,"you") >= 0 && findWord(statement,"me") >= 0) {
            response = transformYouMeStatement(statement);
        }
        else if (statement.trim().equals("")){
            response = "Say something please.";
        }
        else {
            response = getRandomResponse();
        }

        return response;
    }
    
    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    public String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 4;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }
    
        return response;
    }

    // Checks to see if the String word appears as a whole word
    // in the String str (in this case, a "whole word" means that 
    // word is not just a substring of some larger word in str)

    // This method should work regardless of the capitalization 
    // of str or word

    // The method returns the index of the first character in word
    // if it is found, and returns -1 otherwise. 
    public int findWord(String str, String word) {
        str = str.toLowerCase();
        word = word.toLowerCase();
        int x = str.indexOf(word);
        if (x >= 0) {
            if (x + word.length() < str.length() - word.length() && str.charAt(x + word.length()) == ' ') {
                return x;
            } else if (x == 0 && str.charAt(x + word.length()) == ' ') {
                return x;
            } else if (str.charAt(x - 1) == ' ' && str.charAt(x + word.length()-1) == str.charAt(str.length() - 1)) {
                return x;
            }
        }
        return -1;
    }

    
    // We will work on the following methods later!

    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    public String transformIWantStatement(String statement)
    {
        String newstr = "";
        statement = statement.toLowerCase();
        if (findWord(statement,"I want") >= 0) {
            int newstrindex = findWord(statement, "I want");
            newstrindex = newstrindex + 6;
            int endstrindex = statement.length();
            String sub = statement.substring(newstrindex, endstrindex);
            newstr = newstr + sub;
        }
        return ("Would you really be happy if you had" + newstr + "?");
    }

    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    public String transformIYouStatement(String statement)
    {
        String newstr = "";
        statement = statement.toLowerCase();
        if (findWord(statement,"I") >= 0 && findWord(statement,"you") >= 0) {
            int iindex = findWord(statement, "I");
            iindex = iindex + 2;
            String sub = statement.substring(iindex, (findWord(statement, "you")-1));
            newstr = newstr + sub;
        }
        return ("Why do you " + newstr + " me?");
    }

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    public String transformIWantToStatement(String statement)
    {
        String newstr = "";
        statement = statement.toLowerCase();
        if (findWord(statement,"I want to") >= 0) {
            int newstrindex = findWord(statement, "I want to");
            newstrindex = newstrindex + 6;
            int endstrindex = statement.length();
            String sub = statement.substring(newstrindex, endstrindex);
            newstr = newstr + sub;
        }
        return ("What would it mean" + newstr + "?");
    }




    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    public String transformYouMeStatement(String statement)
    {
        String newstr = "";
        statement = statement.toLowerCase();
        if (findWord(statement,"you") >= 0 && findWord(statement,"me") >= 0) {
            int iindex = findWord(statement, "you");
            iindex = iindex + 4;
            String sub = statement.substring(iindex, (findWord(statement, "me")-1));
            newstr = newstr + sub;
        }
        return ("What makes you think that I " + newstr + " you?");
    }

    public String transformILikeStatement(String statement)
    {
        String newstr = "";
        statement = statement.toLowerCase();
        if (findWord(statement,"I like") >= 0) {
            int newstrindex = findWord(statement, "I like");
            newstrindex = newstrindex + 6;
            int endstrindex = statement.length();
            String sub = statement.substring(newstrindex, endstrindex);
            newstr = newstr + sub;
        }
        return ("Why do you like" + newstr + "?");
    }
}
