package com.example.coinmarket.ui.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.thorus.coinmarket.R;

public class DialogsUtil {
    /**
     * Alert dialog showing that Network connection is not available.
     */
    public static void showDialogNetworkFailed(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);
        builder.setTitle(R.string.network_connection_failed_dialog_title);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setMessage(context.getString(R.string.network_connection_failed_dialog_message));
        AlertDialog alert = builder.create();
        alert.show();
        TextView txtMessage = (TextView) alert.findViewById(android.R.id.message);
    }
}
