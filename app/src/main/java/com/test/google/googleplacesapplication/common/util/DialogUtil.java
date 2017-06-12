package com.test.google.googleplacesapplication.common.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.test.google.googleplacesapplication.R;

public class DialogUtil {

    /**
     * Displays no network alert dialog.
     *
     * @param ctx
     */
    public static void showNoNetworkAlert(Context ctx) {
        try {
            new android.app.AlertDialog.Builder(ctx).setTitle(R.string.app_name).setMessage(R.string.no_internet)
                    .setPositiveButton(R.string.ok_text, null).create().show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }

    /**
     * This returns a dialog with no title.
     * Just send the custom view for the dialog.
     *
     * @param ctx  - Context.
     * @param view - Custom ciew for the dialog
     */
    public static AlertDialog dialogWithNoTitle(Context ctx, View view) {
        if (view == null)
            return null;
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setView(view);
        return dialog;
    }

    /**
     * Display dialog with 1 button
     */
    public static void showAlertDialogWithOneBtn(Context ctx, String msg, String positiveBtnTxt, DialogInterface.OnClickListener positiveBtnListener) {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
            alertDialog.setTitle(R.string.app_name).setMessage(msg).setCancelable(false)
                    .setPositiveButton(positiveBtnTxt, positiveBtnListener);
            alertDialog.create();
            alertDialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }
    /**
     * Display dialog with 2 button
     */
    public static void showAlertDialogWithTwoBtn(Context ctx, String msg, String positiveBtnTxt, DialogInterface.OnClickListener positiveBtnListener,
                                                 String negativeBtnTxt, DialogInterface.OnClickListener negativeListener) {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
            alertDialog.setTitle(R.string.app_name).setMessage(msg).setCancelable(false)
                    .setPositiveButton(positiveBtnTxt, positiveBtnListener).setNegativeButton(negativeBtnTxt, negativeListener);
            alertDialog.create();
            alertDialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }
}
