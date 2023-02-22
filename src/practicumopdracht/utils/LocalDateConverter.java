package practicumopdracht.utils;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for a converter of a local date from a datepicker
 *
 * @author Julian Kruithof
 */
public class LocalDateConverter extends StringConverter<LocalDate> {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private boolean hasParseError = false;

    /**
     * @return true if the datepicker has a parse error false if there is no parse error
     */
    public boolean hasParseError() {
        return hasParseError;
    }


    /**
     * Converts the LocalDate provided into its string form using the pattern dd-MM-yyyy.
     * @param localDate LocalDate which needs to be converted
     * @return a string representation of the LocalDate passed in.
     */
    @Override
    public String toString(LocalDate localDate) {
        {
            if (localDate == null)
                return "";
            return dateTimeFormatter.format(localDate);
        }
    }

    /**
     * converts a string into a LocalDate if LocalDate is parsable. If it isn't parsable has ParseError is set to true
     * @param dateString a string containing a LocalDate
     * @return A Localdate object or null depending if the string can be parsed into a LocalDate
     */
    @Override
    public LocalDate fromString(String dateString) {
        try {
            LocalDate date = LocalDate.from(dateTimeFormatter.parse(dateString));
            hasParseError = false;
            return date;
        } catch (DateTimeParseException parseExc) {
            hasParseError = true;
            return null;
        }
    }
}
