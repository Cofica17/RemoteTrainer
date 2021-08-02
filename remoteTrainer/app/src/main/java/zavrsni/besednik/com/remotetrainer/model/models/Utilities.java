package zavrsni.besednik.com.remotetrainer.model.models;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Date;

public class Utilities {

    public static void createAndShowAlertDialog(Context context, String title, String message)
    {
        androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static CharSequence getCurrentDate(String format)
    {
        Date d = new Date();
        CharSequence s  = DateFormat.format(format, d.getTime());
        return s;
    }

    public static CharSequence getCurrentDate(String format, long milliseconds)
    {
        Date d = new Date();
        CharSequence s  = DateFormat.format(format, milliseconds);
        return s;
    }

}
