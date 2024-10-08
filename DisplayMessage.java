package edu.wgu.d387_sample_code.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayMessage implements Runnable {

    static Locale locale;


    public DisplayMessage(Locale locale) {
        this.locale = locale;
    }

    public static String getDisplayMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("welcomeRB",locale);
        return bundle.getString("welcome");
    }

    @Override
    public void run() {
        System.out.println( "Thread verification: " + getDisplayMessage() + ", ThreadID: " + Thread.currentThread().getId() );
    }
}
