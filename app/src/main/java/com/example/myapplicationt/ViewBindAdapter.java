package com.example.myapplicationt;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/22 2:31 PM
 * 描述：
 */
public class ViewBindAdapter {


    private static final String TAG = "ViewBindAdapter";

//    @BindingAdapter(value = {"android:text"}, requireAll = true)
//    public static void setTextContext(TextView textView, String content) {
//        Log.i("checkBox", "content: " + content);
//        textView.setText("BindingAdapter:" + content);
//    }


    @BindingAdapter(value = {"isCheck"}, requireAll = true)
    public static void setCheckeds(CheckBox checkBox, boolean isCheck) {
        Log.i("checkBox", "checkBox: " + isCheck);
        checkBox.setChecked(isCheck);
    }

    //["txtColor", "txtSize"]
    @BindingAdapter(value = {"txtColor", "txtSize"}, requireAll = false)
    public static void setTextSytle(TextView textView, int txtColor, float txtSize) {
        Log.i("checkBox", "txtColor: " + txtColor);
        if (txtColor != 0) {
            textView.setTextColor(txtColor);
        }

        if (txtSize != 0) {
            textView.setTextSize(txtSize);
        }
    }


    @BindingAdapter(value = {"progressNum"})
    public static void setProgressNum(SeekBar seekBar, int progress) {
        Log.i(TAG, "setProgressNum: " + progress);
        if (seekBar.getProgress() != progress) {
            seekBar.setProgress(progress);
        }
    }

    @InverseBindingAdapter(attribute = "progressNum")
    public static int getProgress(SeekBar seekBar) {
        Log.i(TAG, "getProgress: " + seekBar.getProgress());
        return seekBar.getProgress();
    }

    @BindingAdapter(value = "progressNumAttrChanged")
    public static void setProgressNumAttrChanged(SeekBar seekBar, InverseBindingListener inverseBindingListener) {
        if (inverseBindingListener != null) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Log.i(TAG, "inverseBindingListener: 通知去获取 ");
                    inverseBindingListener.onChange();
                }
            });
        }

    }
}
