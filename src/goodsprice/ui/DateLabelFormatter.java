
package goodsprice.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Raitis
 */
public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    
    private String datePattern = "dd-MM-yyyy"; //"yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Date stringToValue(String text) throws ParseException {
        return dateFormatter.parse(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            return dateFormatter.format(value);
        }
        return "";
    }
    
}
