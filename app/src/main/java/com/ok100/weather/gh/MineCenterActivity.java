package com.ok100.weather.gh;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.ok100.weather.R;
import com.ok100.weather.activity.ZhutiImgeActivity;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.utils.ActivityBarSettingUtils;
import com.ok100.weather.utils.ToastUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: gh
 * @description:
 * @date: 2019/11/9.
 * @from:
 */
public class MineCenterActivity extends BaseActivity {

    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.ll_img)
    LinearLayout llImg;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.ll_nick)
    LinearLayout llNick;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.ll_birth)
    LinearLayout llBirth;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.ll_wechat)
    LinearLayout llWechat;
    @BindView(R.id.tv_logout)
    TextView tvLogout;

    private TimePickerView pvTime;
    private OptionsPickerView pvNoLinkOptions;

    private Button btn_photo_cancel;
    private Button btn_camera;
    private Button btn_local;

    private String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getPath();
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private File savePhoto;

    public static void access(Context context) {
        Intent intent = new Intent(context, MineCenterActivity.class);

        context.startActivity(intent);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_minecenter;
    }

    @Override
    public void InitView() {
        setTitle("个人中心", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        initTimePicker();
        initNoLinkOptionsPicker();
        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(MineCenterActivity.this,true);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_img, R.id.ll_nick, R.id.ll_birth, R.id.ll_sex, R.id.ll_phone, R.id.ll_wechat, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_img:
                showPopupWindow();
                break;
            case R.id.ll_nick:
                CustomerDialog dialog = new CustomerDialog(this, R.layout.dialog_nickname);
                dialog.setDlgIfClick(true);
                dialog.setOnCustomerViewCreated(new CustomerDialog.CustomerViewInterface() {
                    @Override
                    public void getCustomerView(Window window, AlertDialog dlg) {
                        EditText dialog_et = window.findViewById(R.id.dialog_et);
                        window.findViewById(R.id.dialog_cancle).setOnClickListener(v -> dialog.dismissDlg());
                        window.findViewById(R.id.dialog_ok).setOnClickListener(v -> {
                            tvNick.setText(dialog_et.getText());
                            dialog.dismissDlg();
                        });
                    }
                });
                dialog.showDlg();
                dialog.getDlg().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                break;
            case R.id.ll_birth:
                pvTime.show();
                break;
            case R.id.ll_sex:
                pvNoLinkOptions.show();
                break;
            case R.id.ll_phone:
                ToastUtil.makeShortText(getContext(),"绑定手机号");
                break;
            case R.id.ll_wechat:
                ToastUtil.makeShortText(getContext(),"绑定微信号");
                break;
            case R.id.tv_logout:
                ToastUtil.makeShortText(getContext(),"退出登录");
                break;
        }
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvBirth.setText(getTime(date));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                })
                .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        ArrayList<String> sexList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");

        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvSex.setText(sexList.get(options1));
            }
        })
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                    }
                })
                .setItemVisibleCount(5)
                // .setSelectOptions(0, 1, 1)
                .build();
        pvNoLinkOptions.setNPicker(sexList, null, null);
        pvNoLinkOptions.setSelectOptions(0, 1, 1);

    }

    /**
     * 展示popupWindow页面(头像选取方式）
     */
    private void showPopupWindow() {
        CustomerDialog dialog = new CustomerDialog(this, R.layout.personalinfophoto);
        dialog.setDlgIfClick(true);
        dialog.setOnCustomerViewCreated(new CustomerDialog.CustomerViewInterface() {
            @Override
            public void getCustomerView(Window window, AlertDialog dlg) {
                btn_camera = window.findViewById(R.id.btn_camera);
                btn_local = window.findViewById(R.id.btn_local);
                btn_photo_cancel = window.findViewById(R.id.btn_photo_cancel);
                btn_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismissDlg();
                        camera();
                    }
                });
                btn_local.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismissDlg();
                        gallery();
                    }
                });
                btn_photo_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismissDlg();
                    }
                });
            }
        });
        dialog.showDlg();
        dialog.getDlg().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //设置alterdialog全屏
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getDlg().getWindow().getAttributes();
        lp.height = (int) (display.getHeight()); //设置宽度
        lp.width = (int) (display.getWidth()); //设置宽度
        dialog.getDlg().getWindow().setAttributes(lp);

    }

    /**
     * 从相机获取
     */
    public void camera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        savePhoto = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"/test/"+System.currentTimeMillis() + ".png");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(savePhoto));
        Uri data;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // "net.csdn.blog.ruancoder.fileprovider"即是在清单文件中配置的authorities
            data = FileProvider.getUriForFile(MineCenterActivity.this,"com.ok100.weather.provider", savePhoto);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(savePhoto);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, data);
        startActivityForResult(intent,PHOTO_REQUEST_CAMERA);
    }

    /**
     * 从相册获取
     */
    public void gallery() {
        // 激活系统图库，选择一张图片
        if (hasSdcard()) {
            if (!new File(DOWNLOAD_PATH + "/stores/").exists()) {
                new File(DOWNLOAD_PATH + "/stores/").mkdirs();
            }
        }
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /**
     * 检查是否有SD卡
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case PHOTO_REQUEST_GALLERY:
                Uri uri1 = intent.getData();
                Glide.with(getContext()).load(uri1).transform(new GlideCircleTransform(getContext())).into(ivImg);
                break;
            // 从相机中获取照片
            case PHOTO_REQUEST_CAMERA:
                Glide.with(getContext()).load(savePhoto).transform(new GlideCircleTransform(getContext())).into(ivImg);
                break;
        }
    }
}
