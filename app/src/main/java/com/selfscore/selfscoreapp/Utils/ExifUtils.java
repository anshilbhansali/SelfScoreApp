package com.selfscore.selfscoreapp.Utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by anshilbhansali on 7/29/16.
 */
public class ExifUtils {

    /**
     * inspired from - http://stackoverflow.com/questions/13511356/android-image-selected-from-gallery-orientation-is-always-0-exif-tag
     */

    public static Bitmap rotateBitmap(Uri uri, Bitmap bitmap, Context context) {

        int orientation = getOrientation(context, uri);

        Log.v("ORIENTATION IS : ", String.valueOf(orientation));

        if (orientation == 0) {
            return bitmap;
        }

        //usually, orientation is 90 or 270
        Matrix matrix = new Matrix();
        matrix.postRotate(orientation);


        try {
            Bitmap oriented = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return oriented;

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return bitmap;
        }

    }

    private static int getOrientation(Context context, Uri photoUri) {
        /* it's on the external media. */
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }

        cursor.moveToFirst();
        return cursor.getInt(0);
    }


}
