package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class PictureUtils {
    public static int imgWidth = 0;
    public static int imgHeight = 0;

    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size = new Point();
        if(imgWidth == 0 || imgHeight == 0) {
            activity.getWindowManager().getDefaultDisplay().getSize(size);
        } else {
            size.x = imgWidth;
            size.y = imgHeight;
        }
        return getScaledBitmap(path, size.x, size.y);
    }

    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight){
        //чтение размеров изображения на диске
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        //Вычисление степени масштабирования
        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth){
            float heightScale = srcHeight / destHeight;
            float widthScale = srcWidth / destWidth;

            inSampleSize = Math.round(heightScale > widthScale ? heightScale : widthScale);
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        //Чтение данных и создание итогового изображения
        return BitmapFactory.decodeFile(path, options);
    }

    public static Bitmap getScaledBitmapFull(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return getScaledBitmap(path, size.x, size.y);
    }
}
