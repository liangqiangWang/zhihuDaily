package com.example.ll.zhihudaily.Activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ll.zhihudaily.R;
import com.example.ll.zhihudaily.Util.Constant;
import com.example.ll.zhihudaily.Util.HttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StartUP extends AppCompatActivity {
    private ImageView start_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_up);
        start_image=(ImageView)findViewById(R.id.start_image);
        initImage();
    }

    private void initImage(){
        File dir=getFilesDir();
        final File imageFile=new File(dir,"startUP.jpg");
        if(imageFile.exists()){
            start_image.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        }else{
            start_image.setImageResource(R.mipmap.start);
        }
        final ScaleAnimation scaleAnim = new ScaleAnimation(1.2f, 1.5f, 1.2f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);

        scaleAnim.setAnimationListener(new AnListener(imageFile));
        start_image.setAnimation(scaleAnim);

    }

    /**
    启动界面动画监听器
     */
    class AnListener implements Animation.AnimationListener {
        File file;
        AnListener(File file){
            this.file=file;
        }

        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (HttpUtils.isNetworkConnected(StartUP.this)) {
                HttpUtils.get(Constant.START, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            JSONObject jsonObject = new JSONObject(new String(bytes));
                            String url = jsonObject.getString("img");
                            HttpUtils.getImage(url, new BinaryHttpResponseHandler() {
                                @Override
                                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                    saveImage(file, bytes);
                                    start_Activity();
                                }

                                @Override
                                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                                    start_Activity();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        start_Activity();
                    }
                });
            } else {
                Toast.makeText(StartUP.this, "没有网络连接!", Toast.LENGTH_LONG).show();
                start_Activity();
            }
        }

        public void onAnimationRepeat(Animation animation) {

        }

    }

    private void start_Activity(){
        Intent intent=new Intent(StartUP.this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }

    private void saveImage(File file,byte[] bytes){
        try{
            if(file.exists()){
                file.delete();
            }
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }







}
