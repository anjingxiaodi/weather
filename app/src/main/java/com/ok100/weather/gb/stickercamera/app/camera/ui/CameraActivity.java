package com.ok100.weather.gb.stickercamera.app.camera.ui;


import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.ok100.weather.R;
import com.ok100.weather.gb.customview.CameraGrid;
import com.ok100.weather.gb.stickercamera.App;
import com.ok100.weather.gb.stickercamera.AppConstants;
import com.ok100.weather.gb.stickercamera.app.camera.CameraBaseActivity;
import com.ok100.weather.gb.stickercamera.app.camera.CameraManager;
import com.ok100.weather.gb.stickercamera.app.camera.util.CameraHelper;
import com.ok100.weather.gb.stickercamera.app.model.PhotoItem;
import com.ok100.weather.gb.util.DistanceUtil;
import com.ok100.weather.gb.util.FileUtils;
import com.ok100.weather.gb.util.IOUtil;
import com.ok100.weather.gb.util.ImageLoaderUtils;
import com.ok100.weather.gb.util.ImageUtils;
import com.ok100.weather.gb.util.StringUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.reactivex.functions.Consumer;


import static android.content.ComponentCallbacks2.TRIM_MEMORY_BACKGROUND;

/**
 * 相机界面
 * Created by sky on 15/7/6.
 */
public class CameraActivity extends CameraBaseActivity {

    private CameraHelper mCameraHelper;
    private Camera.Parameters parameters = null;
    private Camera cameraInst = null;
    private Bundle bundle = null;
    private int photoWidth = DistanceUtil.getCameraPhotoWidth();
    private int photoNumber = 4;
    private int photoMargin = App.getApp().dp2px(1);
    private float pointX, pointY;
    static final int FOCUS = 1;            // 聚焦
    static final int ZOOM = 2;            // 缩放
    private int mode;                      //0是聚焦 1是放大
    private float dist;
    private int PHOTO_SIZE = 2000;
    private int mCurrentCameraId = 0;  //1是前置 0是后置
    private Handler handler = new Handler();

    @BindView(R.id.masking)
    CameraGrid cameraGrid;
    @BindView(R.id.photo_area)
    LinearLayout photoArea;
    @BindView(R.id.panel_take_photo)
    View takePhotoPanel;
    @BindView(R.id.takepicture)
    Button takePicture;
    @BindView(R.id.flashBtn)
    ImageView flashBtn;
    @BindView(R.id.change)
    ImageView changeBtn;
    @BindView(R.id.back)
    ImageView backBtn;
    @BindView(R.id.next)
    ImageView galleryBtn;
    @BindView(R.id.focus_index)
    View focusIndex;
    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;
    @BindView(R.id.root_view_layout)
    LinearLayout root_view_layout;
    @BindView(R.id.sub_img)
    ImageView sub_img;
    private final static int COMPLETED = 0x11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCameraHelper = new CameraHelper(this);
        ButterKnife.bind(this);
        requestLocation();
    }

    @SuppressLint("CheckResult")
    private void requestLocation() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.requestEach(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) {
                        if (permission.granted) {
                            initView();
                            initEvent();
                        } else {

                        }
                    }
                });
    }

    private void initView() {
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.setKeepScreenOn(true);
        surfaceView.setFocusable(true);
        surfaceView.setBackgroundColor(TRIM_MEMORY_BACKGROUND);
        surfaceView.getHolder().addCallback(new SurfaceCallback());//为SurfaceView的句柄添加一个回调函数

        //设置相机界面,照片列表,以及拍照布局的高度(保证相机预览为正方形)
        ViewGroup.LayoutParams layout = cameraGrid.getLayoutParams();
        layout.height = App.getApp().getScreenWidth();
        layout = photoArea.getLayoutParams();
        layout.height = DistanceUtil.getCameraPhotoAreaHeight();
        layout = takePhotoPanel.getLayoutParams();
        layout.height = (App.getApp().getScreenHeight()
                - App.getApp().getScreenWidth()
                - DistanceUtil.getCameraPhotoAreaHeight()) / 3;

        //添加系统相册内的图片
        ArrayList<PhotoItem> sysPhotos = FileUtils.getInst().findPicsInDir(
                FileUtils.getInst().getSystemPhotoPath());
        int showNumber = sysPhotos.size() > photoNumber ? photoNumber
                : sysPhotos.size();
        for (int i = 0; i < showNumber; i++) {
            addPhoto(sysPhotos.get(showNumber - 1 - i));
        }
    }

    private void addPhoto(PhotoItem photoItem) {
        ImageView photo = new ImageView(CameraActivity.this);
        if (StringUtils.isNotBlank(photoItem.getImageUri())) {
            ImageLoaderUtils.displayLocalImage(photoItem.getImageUri(), photo, null);
        } else {
            photo.setImageResource(R.drawable.default_img);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                photoWidth, photoWidth);
        params.leftMargin = photoMargin;
        params.rightMargin = photoMargin;
        params.gravity = Gravity.CENTER;
        photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
        photo.setTag(photoItem.getImageUri());

        if (photoArea.getChildCount() >= photoNumber) {
            photoArea.removeViewAt(photoArea.getChildCount() - 1);
            photoArea.addView(photo, 0, params);
        } else {
            photoArea.addView(photo, 0, params);
        }
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof ImageView && v.getTag() instanceof String) {
                    CameraManager.getInst().processPhotoItem(CameraActivity.this,
                            new PhotoItem((String) v.getTag(), System.currentTimeMillis()));
                }
            }
        });
    }

    private void initEvent() {
        //拍照
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              Bitmap bitmap = createViewBitmap(frameLayout);
//
//              if(bitmap!=null){
//                  root_view_layout.setVisibility(View.GONE);
//                  surfaceView.setVisibility(View.GONE);
//                  sub_img.setVisibility(View.VISIBLE);
//                  sub_img.setImageBitmap(bitmap);
//              }

                try {
                    cameraInst.takePicture(null, null, new MyPictureCallback());
                } catch (Throwable t) {
                    t.printStackTrace();
                    toast("拍照失败，请重试！", Toast.LENGTH_LONG);
                    try {
                        cameraInst.startPreview();
                    } catch (Throwable e) {

                    }
                }
            }
        });
        //闪光灯
        flashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnLight(cameraInst);
            }
        });
        //前后置摄像头切换
        boolean canSwitch = false;
        try {
            canSwitch = mCameraHelper.hasFrontCamera() && mCameraHelper.hasBackCamera();
        } catch (Exception e) {
            //获取相机信息失败
        }
        if (!canSwitch) {
            changeBtn.setVisibility(View.GONE);
        } else {
            changeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchCamera();
                }
            });
        }
        //跳转相册
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("暂未开通", 1000);
//                startActivity(new Intent(CameraActivity.this, AlbumActivity.class));
            }
        });
        //返回按钮
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    // 主点按下
                    case MotionEvent.ACTION_DOWN:
                        pointX = event.getX();
                        pointY = event.getY();
                        mode = FOCUS;
                        break;
                    // 副点按下
                    case MotionEvent.ACTION_POINTER_DOWN:
                        dist = spacing(event);
                        // 如果连续两点距离大于10，则判定为多点模式
                        if (spacing(event) > 10f) {
                            mode = ZOOM;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = FOCUS;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == FOCUS) {
                            //pointFocus((int) event.getRawX(), (int) event.getRawY());
                        } else if (mode == ZOOM) {
                            float newDist = spacing(event);
                            if (newDist > 10f) {
                                float tScale = (newDist - dist) / dist;
                                if (tScale < 0) {
                                    tScale = tScale * 10;
                                }
                                addZoomIn((int) tScale);
                            }
                        }
                        break;
                }
                return false;
            }
        });


        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    pointFocus((int) pointX, (int) pointY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(focusIndex.getLayoutParams());
                layout.setMargins((int) pointX - 60, (int) pointY - 60, 0, 0);
                focusIndex.setLayoutParams(layout);
                focusIndex.setVisibility(View.VISIBLE);
                ScaleAnimation sa = new ScaleAnimation(3f, 1f, 3f, 1f,
                        ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                sa.setDuration(800);
                focusIndex.startAnimation(sa);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        focusIndex.setVisibility(View.INVISIBLE);
                    }
                }, 800);
            }

//        takePhotoPanel.setOnClickListener(v -> {
//            //doNothing 防止聚焦框出现在拍照区域
//        });

        });


    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent result) {
        if (requestCode == AppConstants.REQUEST_PICK && resultCode == RESULT_OK) {
            CameraManager.getInst().processPhotoItem(
                    CameraActivity.this,
                    new PhotoItem(result.getData().getPath(), System
                            .currentTimeMillis()));
        } else if (requestCode == AppConstants.REQUEST_CROP && resultCode == RESULT_OK) {
            Intent newIntent = new Intent(this, PhotoProcessActivity.class);
            newIntent.setData(result.getData());
            startActivity(newIntent);
        }
    }

    /**
     * 两点的距离
     */
    private float spacing(MotionEvent event) {
        if (event == null) {
            return 0;
        }
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    //放大缩小
    int curZoomValue = 0;

    private void addZoomIn(int delta) {

        try {
            Camera.Parameters params = cameraInst.getParameters();
            Log.d("Camera", "Is support Zoom " + params.isZoomSupported());
            if (!params.isZoomSupported()) {
                return;
            }
            curZoomValue += delta;
            if (curZoomValue < 0) {
                curZoomValue = 0;
            } else if (curZoomValue > params.getMaxZoom()) {
                curZoomValue = params.getMaxZoom();
            }

            if (!params.isSmoothZoomSupported()) {
                params.setZoom(curZoomValue);
                cameraInst.setParameters(params);
                return;
            } else {
                cameraInst.startSmoothZoom(curZoomValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //定点对焦的代码
    private void pointFocus(int x, int y) {
        cameraInst.cancelAutoFocus();
        parameters = cameraInst.getParameters();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            showPoint(x, y);
        }
        cameraInst.setParameters(parameters);
        autoFocus();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void showPoint(int x, int y) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            List<Camera.Area> areas = new ArrayList<Camera.Area>();
            //xy变换了
            int rectY = -x * 2000 / App.getApp().getScreenWidth() + 1000;
            int rectX = y * 2000 / App.getApp().getScreenHeight() - 1000;

            int left = rectX < -900 ? -1000 : rectX - 100;
            int top = rectY < -900 ? -1000 : rectY - 100;
            int right = rectX > 900 ? 1000 : rectX + 100;
            int bottom = rectY > 900 ? 1000 : rectY + 100;
            Rect area1 = new Rect(left, top, right, bottom);
            areas.add(new Camera.Area(area1, 800));
            parameters.setMeteringAreas(areas);
        }

        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
    }

    Bitmap takePitmap = null;
    Bitmap viewBitmap = null;
    Bitmap combineBitmap = null;

    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETED) {
                Bitmap bit = (Bitmap) msg.obj;
                if (bit != null) {
                    Log.e("bst==", "---------------------------------333333333333333333");
                    root_view_layout.setVisibility(View.GONE);
                    surfaceView.setVisibility(View.GONE);
                    sub_img.setVisibility(View.VISIBLE);
                    sub_img.setImageBitmap(bit);
                    EventBus.getDefault().post(bit);
                    CameraActivity.this.finish();
                }
            }
        }
    };

    private final class MyPictureCallback implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(final byte[] data, final Camera camera) {
            bundle = new Bundle();
            bundle.putByteArray("bytes", data); //将图片字节数据保存在bundle当中，实现数据交换
            takePitmap = null;
            viewBitmap = null;
            combineBitmap = null;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    takePitmap = getBitmap(data);
                    viewBitmap = getViewBitmap(root_view_layout);
                    combineBitmap = combineBitmap(takePitmap, viewBitmap);
                    Message msg = new Message();
                    msg.what = COMPLETED;
                    msg.obj = combineBitmap;
                    handler1.sendMessage(msg);
                }
            }).start();


//                    Bitmap samllPitmap= getBitmapFromView(root_view_layout);
//                    Log.e("bast==","-----------samllPitmap-------------"+samllPitmap.getHeight());
//                    Log.e("bast==","-----------takePitmap-------------"+takePitmap.getHeight());
//            new SavePicTask(data).execute();  //保存在本地的方法
//            camera.startPreview(); // 拍完照后，重新开始预览
        }
    }

    /**
     * 更具view生成bitmap
     */

    private Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    public static Bitmap combineBitmap(Bitmap background, Bitmap foreground) {
        if (background == null) {
            return null;
        }
        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();
        int fgWidth = foreground.getWidth();
        int fgHeight = foreground.getHeight();
        Bitmap newmap = Bitmap
                .createBitmap(bgWidth, bgHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newmap);
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(foreground, (bgWidth - fgWidth) / 2,
                (bgHeight - fgHeight) / 2, null);
        canvas.save();
        canvas.restore();
        return newmap;
    }

    private static Bitmap getBitmap(byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        return bitmap;
    }

    private class SavePicTask extends AsyncTask<Void, Void, String> {
        private byte[] data;

        protected void onPreExecute() {
            showProgressDialog("处理中");
        }

        ;

        SavePicTask(byte[] data) {
            this.data = data;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                return saveToSDCard(data);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (StringUtils.isNotEmpty(result)) {
                dismissProgressDialog();

                Log.e("bst==", "-----------------------" + result);
//                    CameraManager.getInst().processPhotoItem(CameraActivity.this,
//                            new PhotoItem(result, System.currentTimeMillis()));
            } else {
                toast("拍照失败，请稍后重试！", Toast.LENGTH_LONG);
            }
        }
    }


    /*SurfaceCallback*/
    private final class SurfaceCallback implements SurfaceHolder.Callback {

        public void surfaceDestroyed(SurfaceHolder holder) {
            try {
                if (cameraInst != null) {
                    cameraInst.stopPreview();
                    cameraInst.release();
                    cameraInst = null;
                }
            } catch (Exception e) {
                //相机已经关了
            }

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (null == cameraInst) {
                try {
                    cameraInst = Camera.open();
                    cameraInst.setPreviewDisplay(holder);
                    initCamera();
                    cameraInst.startPreview();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            autoFocus();
        }
    }

    //实现自动对焦
    private void autoFocus() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (cameraInst == null) {
                    return;
                }
                cameraInst.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            initCamera();//实现相机的参数初始化
                        }
                    }
                });
            }
        };
    }

    private Camera.Size adapterSize = null;
    private Camera.Size previewSize = null;

    private void initCamera() {
        parameters = cameraInst.getParameters();
        parameters.setPictureFormat(PixelFormat.JPEG);
        //if (adapterSize == null) {
        setUpPicSize(parameters);
        setUpPreviewSize(parameters);
        //}
        if (adapterSize != null) {
            parameters.setPictureSize(adapterSize.width, adapterSize.height);
        }
        if (previewSize != null) {
            parameters.setPreviewSize(previewSize.width, previewSize.height);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//1连续对焦
        } else {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }
        setDispaly(parameters, cameraInst);
        try {
            cameraInst.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cameraInst.startPreview();
        cameraInst.cancelAutoFocus();// 2如果要实现连续的自动对焦，这一句必须加上
    }

    private void setUpPicSize(Camera.Parameters parameters) {

        if (adapterSize != null) {
            return;
        } else {
            adapterSize = findBestPictureResolution();
            return;
        }
    }

    private void setUpPreviewSize(Camera.Parameters parameters) {

        if (previewSize != null) {
            return;
        } else {
            previewSize = findBestPreviewResolution();
        }
    }

    /**
     * 最小预览界面的分辨率
     */
    private static final int MIN_PREVIEW_PIXELS = 480 * 320;
    /**
     * 最大宽高比差
     */
    private static final double MAX_ASPECT_DISTORTION = 0.15;
    private static final String TAG = "Camera";

    /**
     * 找出最适合的预览界面分辨率
     *
     * @return
     */
    private Camera.Size findBestPreviewResolution() {
        Camera.Parameters cameraParameters = cameraInst.getParameters();
        Camera.Size defaultPreviewResolution = cameraParameters.getPreviewSize();

        List<Camera.Size> rawSupportedSizes = cameraParameters.getSupportedPreviewSizes();
        if (rawSupportedSizes == null) {
            return defaultPreviewResolution;
        }

        // 按照分辨率从大到小排序
        List<Camera.Size> supportedPreviewResolutions = new ArrayList<Camera.Size>(rawSupportedSizes);
        Collections.sort(supportedPreviewResolutions, new Comparator<Camera.Size>() {
            @Override
            public int compare(Camera.Size a, Camera.Size b) {
                int aPixels = a.height * a.width;
                int bPixels = b.height * b.width;
                if (bPixels < aPixels) {
                    return -1;
                }
                if (bPixels > aPixels) {
                    return 1;
                }
                return 0;
            }
        });

        StringBuilder previewResolutionSb = new StringBuilder();
        for (Camera.Size supportedPreviewResolution : supportedPreviewResolutions) {
            previewResolutionSb.append(supportedPreviewResolution.width).append('x').append(supportedPreviewResolution.height)
                    .append(' ');
        }
        Log.v(TAG, "Supported preview resolutions: " + previewResolutionSb);


        // 移除不符合条件的分辨率
        double screenAspectRatio = (double) App.getApp().getScreenWidth()
                / (double) App.getApp().getScreenHeight();
        Iterator<Camera.Size> it = supportedPreviewResolutions.iterator();
        while (it.hasNext()) {
            Camera.Size supportedPreviewResolution = it.next();
            int width = supportedPreviewResolution.width;
            int height = supportedPreviewResolution.height;

            // 移除低于下限的分辨率，尽可能取高分辨率
            if (width * height < MIN_PREVIEW_PIXELS) {
                it.remove();
                continue;
            }

            // 在camera分辨率与屏幕分辨率宽高比不相等的情况下，找出差距最小的一组分辨率
            // 由于camera的分辨率是width>height，我们设置的portrait模式中，width<height
            // 因此这里要先交换然preview宽高比后在比较
            boolean isCandidatePortrait = width > height;
            int maybeFlippedWidth = isCandidatePortrait ? height : width;
            int maybeFlippedHeight = isCandidatePortrait ? width : height;
            double aspectRatio = (double) maybeFlippedWidth / (double) maybeFlippedHeight;
            double distortion = Math.abs(aspectRatio - screenAspectRatio);
            if (distortion > MAX_ASPECT_DISTORTION) {
                it.remove();
                continue;
            }

            // 找到与屏幕分辨率完全匹配的预览界面分辨率直接返回
            if (maybeFlippedWidth == App.getApp().getScreenWidth()
                    && maybeFlippedHeight == App.getApp().getScreenHeight()) {
                return supportedPreviewResolution;
            }
        }

        // 如果没有找到合适的，并且还有候选的像素，则设置其中最大比例的，对于配置比较低的机器不太合适
        if (!supportedPreviewResolutions.isEmpty()) {
            Camera.Size largestPreview = supportedPreviewResolutions.get(0);
            return largestPreview;
        }

        // 没有找到合适的，就返回默认的

        return defaultPreviewResolution;
    }

    private Camera.Size findBestPictureResolution() {
        Camera.Parameters cameraParameters = cameraInst.getParameters();
        List<Camera.Size> supportedPicResolutions = cameraParameters.getSupportedPictureSizes(); // 至少会返回一个值

        StringBuilder picResolutionSb = new StringBuilder();
        for (Camera.Size supportedPicResolution : supportedPicResolutions) {
            picResolutionSb.append(supportedPicResolution.width).append('x')
                    .append(supportedPicResolution.height).append(" ");
        }
        Log.d(TAG, "Supported picture resolutions: " + picResolutionSb);

        Camera.Size defaultPictureResolution = cameraParameters.getPictureSize();
        Log.d(TAG, "default picture resolution " + defaultPictureResolution.width + "x"
                + defaultPictureResolution.height);

        // 排序
        List<Camera.Size> sortedSupportedPicResolutions = new ArrayList<Camera.Size>(
                supportedPicResolutions);
        Collections.sort(sortedSupportedPicResolutions, new Comparator<Camera.Size>() {
            @Override
            public int compare(Camera.Size a, Camera.Size b) {
                int aPixels = a.height * a.width;
                int bPixels = b.height * b.width;
                if (bPixels < aPixels) {
                    return -1;
                }
                if (bPixels > aPixels) {
                    return 1;
                }
                return 0;
            }
        });

        // 移除不符合条件的分辨率
        double screenAspectRatio = (double) App.getApp().getScreenWidth()
                / (double) App.getApp().getScreenHeight();
        Iterator<Camera.Size> it = sortedSupportedPicResolutions.iterator();
        while (it.hasNext()) {
            Camera.Size supportedPreviewResolution = it.next();
            int width = supportedPreviewResolution.width;
            int height = supportedPreviewResolution.height;

            // 在camera分辨率与屏幕分辨率宽高比不相等的情况下，找出差距最小的一组分辨率
            // 由于camera的分辨率是width>height，我们设置的portrait模式中，width<height
            // 因此这里要先交换然后在比较宽高比
            boolean isCandidatePortrait = width > height;
            int maybeFlippedWidth = isCandidatePortrait ? height : width;
            int maybeFlippedHeight = isCandidatePortrait ? width : height;
            double aspectRatio = (double) maybeFlippedWidth / (double) maybeFlippedHeight;
            double distortion = Math.abs(aspectRatio - screenAspectRatio);
            if (distortion > MAX_ASPECT_DISTORTION) {
                it.remove();
                continue;
            }
        }

        // 如果没有找到合适的，并且还有候选的像素，对于照片，则取其中最大比例的，而不是选择与屏幕分辨率相同的
        if (!sortedSupportedPicResolutions.isEmpty()) {
            return sortedSupportedPicResolutions.get(0);
        }

        // 没有找到合适的，就返回默认的
        return defaultPictureResolution;
    }


    //控制图像的正确显示方向
    private void setDispaly(Camera.Parameters parameters, Camera camera) {
        if (Build.VERSION.SDK_INT >= 8) {
            setDisplayOrientation(camera, 90);
        } else {
            parameters.setRotation(90);
        }
    }

    //实现的图像的正确显示
    private void setDisplayOrientation(Camera camera, int i) {
        Method downPolymorphic;
        try {
            downPolymorphic = camera.getClass().getMethod("setDisplayOrientation",
                    new Class[]{int.class});
            if (downPolymorphic != null) {
                downPolymorphic.invoke(camera, new Object[]{i});
            }
        } catch (Exception e) {
            Log.e("Came_e", "图像出错");
        }
    }


    /**
     * 将拍下来的照片存放在SD卡中
     *
     * @param data
     * @throws IOException
     */
    public String saveToSDCard(byte[] data) throws IOException {
        Bitmap croppedImage;

        //获得图片大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        PHOTO_SIZE = options.outHeight > options.outWidth ? options.outWidth : options.outHeight;
        int height = options.outHeight > options.outWidth ? options.outHeight : options.outWidth;
        options.inJustDecodeBounds = false;
        Rect r;
        if (mCurrentCameraId == 1) {
            r = new Rect(height - PHOTO_SIZE, 0, height, PHOTO_SIZE);
        } else {
            r = new Rect(0, 0, PHOTO_SIZE, PHOTO_SIZE);
        }
        try {
            croppedImage = decodeRegionCrop(data, r);
        } catch (Exception e) {
            return null;
        }
        String imagePath = ImageUtils.saveToFile(FileUtils.getInst().getSystemPhotoPath(), true,
                croppedImage);
        croppedImage.recycle();
        return imagePath;
    }

    private Bitmap decodeRegionCrop(byte[] data, Rect rect) {

        InputStream is = null;
        System.gc();
        Bitmap croppedImage = null;
        try {
            is = new ByteArrayInputStream(data);
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(is, false);

            try {
                croppedImage = decoder.decodeRegion(rect, new BitmapFactory.Options());
            } catch (IllegalArgumentException e) {
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeStream(is);
        }
        Matrix m = new Matrix();
        m.setRotate(90, PHOTO_SIZE / 2, PHOTO_SIZE / 2);
        if (mCurrentCameraId == 1) {
            m.postScale(1, -1);
        }
        Bitmap rotatedImage = Bitmap.createBitmap(croppedImage, 0, 0, PHOTO_SIZE, PHOTO_SIZE, m, true);
        if (rotatedImage != croppedImage)
            croppedImage.recycle();
        return rotatedImage;
    }

    /**
     * 闪光灯开关   开->关->自动
     *
     * @param mCamera
     */
    private void turnLight(Camera mCamera) {
        if (mCamera == null || mCamera.getParameters() == null
                || mCamera.getParameters().getSupportedFlashModes() == null) {
            return;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        String flashMode = mCamera.getParameters().getFlashMode();
        List<String> supportedModes = mCamera.getParameters().getSupportedFlashModes();
        if (Camera.Parameters.FLASH_MODE_OFF.equals(flashMode)
                && supportedModes.contains(Camera.Parameters.FLASH_MODE_ON)) {//关闭状态
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            mCamera.setParameters(parameters);
            flashBtn.setImageResource(R.drawable.camera_flash_on);
        } else if (Camera.Parameters.FLASH_MODE_ON.equals(flashMode)) {//开启状态
            if (supportedModes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                flashBtn.setImageResource(R.drawable.camera_flash_auto);
                mCamera.setParameters(parameters);
            } else if (supportedModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                flashBtn.setImageResource(R.drawable.camera_flash_off);
                mCamera.setParameters(parameters);
            }
        } else if (Camera.Parameters.FLASH_MODE_AUTO.equals(flashMode)
                && supportedModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(parameters);
            flashBtn.setImageResource(R.drawable.camera_flash_off);
        }
    }


    //切换前后置摄像头
    private void switchCamera() {
        mCurrentCameraId = (mCurrentCameraId + 1) % mCameraHelper.getNumberOfCameras();
        releaseCamera();
        Log.d("DDDD", "DDDD----mCurrentCameraId" + mCurrentCameraId);
        setUpCamera(mCurrentCameraId);
    }

    private void releaseCamera() {
        if (cameraInst != null) {
            cameraInst.setPreviewCallback(null);
            cameraInst.release();
            cameraInst = null;
        }
        adapterSize = null;
        previewSize = null;
    }

    /**
     * @param mCurrentCameraId2
     */
    private void setUpCamera(int mCurrentCameraId2) {
        cameraInst = getCameraInstance(mCurrentCameraId2);
        if (cameraInst != null) {
            try {
                cameraInst.setPreviewDisplay(surfaceView.getHolder());
                initCamera();
                cameraInst.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            toast("切换失败，请重试！", Toast.LENGTH_LONG);

        }
    }

    private Camera getCameraInstance(final int id) {
        Camera c = null;
        try {
            c = mCameraHelper.openCamera(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
