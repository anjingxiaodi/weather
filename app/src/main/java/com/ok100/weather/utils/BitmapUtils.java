package com.ok100.weather.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

/**
 * Created by qiandd on 2017-2-22.
 */

public class BitmapUtils {

    /**
     * 首先默认个文件保存路径
     */
//    public static final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
//    private static final String SAVE_REAL_PATH = SAVE_PIC_PATH + "/DCIM/Vanlian/DCIM";//保存的确切位置
    // 根据路径获得图片并压缩，返回bitmap用于显示
//    public static File getSmallBitmap(String filePath) {
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(filePath, options);
//
//        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, 480, 800);
//
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;
//
//        return new File(filePath);
//    }

//    //计算图片的缩放值
//    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight) {
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        int inSampleSize = 1;
//
//        if (height > reqHeight || width > reqWidth) {
//            final int heightRatio = Math.round((float) height/ (float) reqHeight);
//            final int widthRatio = Math.round((float) width / (float) reqWidth);
//            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
//        }
//        return inSampleSize;
//    }


    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    //把bitmap转换成String
    public static Bitmap getBitmapImg(String filePath) {

        Bitmap bm = getSmallBitmap(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);

        return bm;
    }

    //保存图片到本地文件夹
    public static File saveFile(File myCaptureFile, String fileName) throws IOException {
//        String subForder = SAVE_REAL_PATH ;

//        myCaptureFile = new File(SAVE_REAL_PATH);
//        if (!myCaptureFile.exists()) {
//            myCaptureFile.mkdirs();
//
//        }


//        Log.e("File", myCaptureFile.getPath());

        Bitmap bitmap1 = BitmapFactory.decodeFile(fileName);
        int height = bitmap1.getHeight();
        int width = bitmap1.getWidth();
        bitmap1.recycle();
        bitmap1 = null;
        if (width > 1080 || height > 1920) {
//            Log.e("bitmap1.getHeight()", bitmap1.getHeight() + "");
//            Log.e(" bitmap1.getWidth();", bitmap1.getWidth() + "");
            myCaptureFile = new File(myCaptureFile.getPath(), System.currentTimeMillis() + ".jpg");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            Bitmap bm = getBitmapImg(fileName);
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } else {
            myCaptureFile = new File(fileName);
        }

        return myCaptureFile;
    }


    /**
     * 图片压缩
     *
     * @param photos
     * @param consumer
     */
    public static void compressWithRx(final Context context, final List<ImageItem> photos, Consumer<List<File>> consumer) {
        Flowable.just(photos)
                .observeOn(Schedulers.io())
                .map(new Function<List<ImageItem>, List<String>>() {
                    @Override
                    public List<String> apply(List<ImageItem> imageItems) throws Exception {
                        List<String> mList = new ArrayList<>();
                        for (ImageItem imageItem : imageItems) {
                            mList.add(imageItem.path);
                        }
                        return mList;
                    }
                })
                .map(new Function<List<String>, List<File>>() {
                    @Override
                    public List<File> apply(@NonNull List<String> list) throws Exception {
                        return Luban.with(context).load(list).get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }
}
