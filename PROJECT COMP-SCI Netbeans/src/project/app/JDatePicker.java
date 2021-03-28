package project.app;
import javax.swing.*;
import java.util.*;
import org.jdatepicker.impl.*;

public class JDatePicker extends JFrame{
    public JDatePickerImpl datepanel()
    {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        return datePicker;
    }
}