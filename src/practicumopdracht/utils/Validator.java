package practicumopdracht.utils;

/**
 * Class with types of input validation
 *
 * @author Julian Kruithof
 */
public class Validator {

    /**
     * All validation methods are static, so you don't want to initialise the validator class,
     * So the constructor is private and throws a run time error
     */
    private Validator() {
        throw new RuntimeException("You don't need to initialise a util class. All methods are static");
    }

    /**
     * check if the given string is a parsable integer
     *
     * @param string - text from an input which should contain an integer
     * @return true if string is an integer, false if not
     */
    public static boolean isInteger(String string) {
        try {
            int num = Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if a string is parsable to a double
     *
     * @param string - text from an input which should contain a double
     * @return true if string is a double, false if not
     */
    public static boolean isDouble(String string) {
        try {
            double num = Double.parseDouble(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
