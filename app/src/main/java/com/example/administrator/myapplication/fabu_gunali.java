package com.example.administrator.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.model.shangpin;
import com.example.administrator.model.yonghu;
import com.example.administrator.util.DBService;
import com.example.administrator.util.RealPathFromUriUtils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class fabu_gunali extends AppCompatActivity {
    yonghu yonghu;
    private int Green = 0xFF45C01A;
    ProgressDialog pd;
    private TextView sale_text;
    private Bitmap headImage = null;
    private ImageView sale_image;
    ImageView image;
    Spinner type, hownew;
    EditText title, price, oprice, detail, address;
    RadioButton song, qu;
    //两个Spinner的适配器
    private List<String> type_list;
    private List<String> hownew_list;
    private ArrayAdapter<String> type_adapter;
    private ArrayAdapter<String> hownew_adapter;
    //选择添加照片方式
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int SELECT_PIC = 0;
    private CharSequence[] its = {"拍照", "从相册选择"};
    private File currentImageFile = null;
    private String filename; //图片名称
    private Uri imageUri; //图片路径
    private shangpin oldshangpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpin_guanli);
        Intent intent = getIntent();
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");
        oldshangpin = (shangpin) intent.getSerializableExtra("chakanshangping");
        initview();
    }

    public void initview()//初始化UI
    {
        pd = new ProgressDialog(fabu_gunali.this);
        pd.setMessage("发布中...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);


        image = findViewById(R.id.image);
        type = findViewById(R.id.type);
        hownew = findViewById(R.id.hownew);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        oprice = findViewById(R.id.oprice);
        detail = findViewById(R.id.detail);
        song = findViewById(R.id.song);
        qu = findViewById(R.id.qu);


        //新数据
        hownew_list = new ArrayList<String>();
        hownew_list.add("10");
        hownew_list.add("9.5");
        hownew_list.add("9");
        hownew_list.add("8.5");
        hownew_list.add("7");
        hownew_list.add("6");
        hownew_list.add("5");
        // 类型数据
        type_list = new ArrayList<String>();
        type_list.add("生活百货");
        type_list.add("数码产品");
        type_list.add("服装");
        type_list.add("房屋租赁");
        type_list.add("化妆品");
        type_list.add("游戏交易");
        type_list.add("学习用具");
        type_list.add("玩具");
        type_list.add("运动器材");
        //适配器
        type_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type_list);
        hownew_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hownew_list);
        //设置样式
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hownew_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        type.setAdapter(type_adapter);
        hownew.setAdapter(hownew_adapter);

        for (int i = 0; i < type_list.size(); i++) {
            if (type_list.get(i).equals(oldshangpin.getLeibie()))
                type.setSelection(i);
        }

        for (int i = 0; i < hownew_list.size(); i++) {
            if ( Double.parseDouble(hownew_list.get(i))==oldshangpin.getXingjiuchengdu())
                hownew.setSelection(i);
        }

//        shangpin.setJiage(price);
//        shangpin.setLeibie(type.getSelectedItem().toString());
//        shangpin.setMincheng(title.getText().toString().trim());
//        shangpin.setQuhuofangshi(getway);
//        shangpin.setShijian(new Date());
//        shangpin.setTupian("https://img14.360buyimg.com/n1/jfs/t20695/188/565616716/93445/a0a064d8/5b11163cN2c83f240.jpg");
//        shangpin.setXijie(detail.getText().toString().trim());
//        shangpin.setXingjiuchengdu(Double.parseDouble(hownew.getSelectedItem().toString()));
//        shangpin.setYonghuid(yonghu.getYonghuid());
//        shangpin.setYuanjia(oPrice);
        price.setText(""+oldshangpin.getJiage());
        title.setText(oldshangpin.getMincheng());
        oprice.setText(oldshangpin.getYuanjia()+"");
        File file=new File(oldshangpin.getTupian());
        if (file.exists()){
            image.setImageURI(Uri.fromFile(file));
        }else {
            Glide.with(this).load(oldshangpin.getTupian()).into(image);
        }
        detail.setText(oldshangpin.getXijie());

        song.setChecked(oldshangpin.getQuhuofangshi().equals("送货上门"));
        qu.setChecked(!oldshangpin.getQuhuofangshi().equals("送货上门"));



    }

    public void delete(View view) {
        pd.show();
        DBService.getDbService().delGood(oldshangpin.getShangpinid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        compositeDisposable.add(disposable);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        if (integer == 1) {
                            Toast.makeText(fabu_gunali.this,"删除成功",Toast.LENGTH_SHORT).show();
                           finish();
                        } else {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(fabu_gunali.this);
                            builder.setTitle("提醒");
                            builder.setMessage("删除失败");
                            builder.setPositiveButton("是", null);
                            builder.show();
                        }
                        pd.dismiss();
                    }
                });
    }

    public void publish(View view)//完成（发布）按钮点击事件
    {
        String getway = "";
        //获取时间
        final Date d = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取送货方式
        if (song.isChecked()) getway = "送货上门";
        else getway = "自取来取";

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (oprice.getText().toString().trim().isEmpty() || Double.parseDouble(oprice.getText().toString()) == 0)
            oprice.setText("0");

        /*if(headImage==null)
        {
            Toast.makeText(fabu_shangpin.this,"不传照片怎么卖啊",Toast.LENGTH_SHORT).show();
        }
        else */
        if (title.getText().toString().trim().isEmpty()) {
            Toast.makeText(fabu_gunali.this, "给你的宝贝取个名字啊", Toast.LENGTH_SHORT).show();
        } else if (type.getSelectedItem().toString().trim().length() > 4) {
            Toast.makeText(fabu_gunali.this, "请给宝贝分类呗", Toast.LENGTH_SHORT).show();
        } else if (detail.getText().toString().trim().length() < 6) {
            Toast.makeText(fabu_gunali.this, "描述得不够详细啊", Toast.LENGTH_SHORT).show();
        } else {
            Double Price = Double.parseDouble(price.getText().toString());
            Double Oprice = Double.parseDouble(oprice.getText().toString());
            savaShangpin(Price, Oprice, getway);
        }
    }

    public void savaShangpin(double price, double oPrice, String getway) {
        shangpin shangpin = new shangpin();
        shangpin.setJiage(price);
        shangpin.setShangpinid(oldshangpin.getShangpinid());
        shangpin.setLeibie(type.getSelectedItem().toString());
        shangpin.setMincheng(title.getText().toString().trim());
        shangpin.setQuhuofangshi(getway);
        shangpin.setShijian(new Date());
        shangpin.setTupian(currentImageFile!=null?currentImageFile.getAbsolutePath():oldshangpin.getTupian());
        shangpin.setXijie(detail.getText().toString().trim());
        shangpin.setXingjiuchengdu(Double.parseDouble(hownew.getSelectedItem().toString()));
        shangpin.setYonghuid(yonghu.getYonghuid());
        shangpin.setYuanjia(oPrice);
         DBService.getDbService().updateGoodsData(shangpin)
                .subscribeOn(Schedulers.io())
                 .doOnSubscribe(new Consumer<Disposable>() {
                     @Override
                     public void accept(Disposable disposable) throws Exception {
                         compositeDisposable.add(disposable);
                     }
                 })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        if (integer == 1) {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(fabu_gunali.this);
                            builder.setTitle("提醒");
                            builder.setMessage("修改成功");
                            builder.setPositiveButton("是", null);
                            builder.show();
                        } else {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(fabu_gunali.this);
                            builder.setTitle("提醒");
                            builder.setMessage("修改失败");
                            builder.setPositiveButton("是", null);
                            builder.show();
                        }
                        pd.dismiss();
                    }
                });
//        if(shangpin.save()){
//            android.app.AlertDialog.Builder builder  = new android.app.AlertDialog.Builder(fabu_shangpin.this);
//            builder.setTitle("提醒" ) ;
//            builder.setMessage("发布成功" ) ;
//            builder.setPositiveButton("是" ,  null );
//            builder.show();
//        }else{
//            android.app.AlertDialog.Builder builder  = new android.app.AlertDialog.Builder(fabu_shangpin.this);
//            builder.setTitle("提醒" ) ;
//            builder.setMessage("发布失败" ) ;
//            builder.setPositiveButton("是" ,  null );
//            builder.show();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }


    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void Image(View view)//图片控件点击事件
    {
        new AlertDialog.Builder(fabu_gunali.this)
                .setTitle("上传图片")
                .setItems(its, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0://拍照

                                if (ContextCompat.checkSelfPermission(fabu_gunali.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                  ActivityCompat.requestPermissions((Activity) fabu_gunali.this, new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO);
                                      } else {
                                    //图片名称 时间命名
                                    File dir = new File(Environment.getExternalStorageDirectory(), "pictures");
                                    if (dir.exists()) {
                                        dir.mkdirs();
                                    }
                                    filename = System.currentTimeMillis() + ".jpg";
                                    currentImageFile = new File(dir, filename);
                                    if (!currentImageFile.exists()) {
                                        try {
                                            currentImageFile.createNewFile();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));
                                    startActivityForResult(it, TAKE_PHOTO);
                                }

                                break;
                            case 1://从相册选择
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(intent, SELECT_PIC);
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case SELECT_PIC://相册
                String path = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                currentImageFile = new File(path);
                imageUri = Uri.fromFile(currentImageFile);
                image.setImageURI(imageUri);
                Intent intent1 = new Intent("com.android.camera.action.CROP");
                intent1.setDataAndType(imageUri, "image/*");
                intent1.putExtra("crop", "true");
                intent1.putExtra("aspectX", 1);
                intent1.putExtra("aspectY", 1);
                intent1.putExtra("outputX", 450);
                intent1.putExtra("outputY", 450);
                intent1.putExtra("return-data", false);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                intent1.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                intent1.putExtra("noFaceDetection", true);
                startActivityForResult(intent1, CROP_PHOTO);
                break;
            case TAKE_PHOTO://相机
                try {
                    image.setImageURI(Uri.fromFile(currentImageFile));
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

}
