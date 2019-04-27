package com.example.administrator.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.adapter.CommentExpandAdapter;
import com.example.administrator.model.pinlun;
import com.example.administrator.model.pinlun_Table;
import com.example.administrator.model.pinlunhuifu;
import com.example.administrator.model.pinlunhuifu_Table;
import com.example.administrator.model.shangpin;
import com.example.administrator.model.yonghu;
import com.example.administrator.model.yonghu_Table;
import com.example.administrator.util.AsyncBitmapLoader;
import com.example.administrator.util.DBService;
import com.raizlabs.android.dbflow.sql.language.SQLite;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class shangpin_xiangqing extends AppCompatActivity {
    private AsyncBitmapLoader asyncBitmapLoader;
    yonghu yonghu;
    shangpin shangpin;
    private String tel;
    private int chushipinglunshu = 0;

    private List<pinlun> pinlunsList;
    private ExpandableListView expandableListView;
    private CommentExpandAdapter adapter;

    private OnClickListener listener;
    ImageView touxiang, image;
    private BottomSheetDialog dialog;
    Button commentbtn, buy;
    TextView nickname, time, address, gname, price, oprice, hownew, getway, type, scannum, detail, commentnum;
    EditText commentedt;
    ProgressDialog pd;
    LinearLayout layout_back, pinglun_layout;
    LinearLayout layout_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpin_xiangqing);
        asyncBitmapLoader = new AsyncBitmapLoader();
        Intent intent = getIntent();
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");
        shangpin = (shangpin) intent.getSerializableExtra("chakanshangping");

        initview();
    }

    // 返回按钮键触发的事件
    public void fanhui(android.view.View v) {
        this.finish();
        Intent it = new Intent(shangpin_xiangqing.this, shangpinliebiao.class);
        it.putExtra("dengluuser", yonghu);
        it.putExtra("type", shangpin.getLeibie());
        startActivity(it);

    }

    public void initview() {
        pinglun_layout = findViewById(R.id.pinglun_layout);
        expandableListView = findViewById(R.id.detail_page_lv_comment);
        touxiang = findViewById(R.id.touxiang);
        image = findViewById(R.id.image);
        layout_back = findViewById(R.id.layout_back);
        commentbtn = findViewById(R.id.commentbtn);
        buy = findViewById(R.id.buy);
        nickname = findViewById(R.id.nickname);
        time = findViewById(R.id.time);
        address = findViewById(R.id.address);
        gname = findViewById(R.id.gname);
        price = findViewById(R.id.price);
        oprice = findViewById(R.id.oprice);
        hownew = findViewById(R.id.hownew);
        getway = findViewById(R.id.getway);
        type = findViewById(R.id.type);
        scannum = findViewById(R.id.scannum);
        detail = findViewById(R.id.detail);
        commentnum = findViewById(R.id.commentnum);
        commentedt = findViewById(R.id.commentedt);
        layout_chat = findViewById(R.id.chat);
        listener = new OnClickListener();
        buy.setOnClickListener(listener);
        commentbtn.setOnClickListener(listener);

        pd = new ProgressDialog(shangpin_xiangqing.this);
        pd.setMessage("下单中...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pinlunsList=new ArrayList<>();
        selectComment(shangpin.getShangpinid());

    }

    private void loaddata() {

        Log.i("123", pinlunsList.size() + "");
        if (pinlunsList.size() == 0) {

            commentnum.setText("(" + chushipinglunshu + ")");

        } else {
            layout_back.setVisibility(View.INVISIBLE);
            chushipinglunshu = pinlunsList.size();
            commentnum.setText("(" + chushipinglunshu + ")");
            initExpandableListView(pinlunsList);
        }
        nickname.setText(yonghu.getXingming() != null ? yonghu.getXingming() : "");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time.setText(formatter.format(shangpin.getShijian()));
        Log.i("shangpin", shangpin.getShijian().toString());
        //address.setText(shangpin.date.getString("address"));
        gname.setText(shangpin.getMincheng());
        price.setText("¥:" + shangpin.getJiage());
        oprice.setText("原价:¥" + shangpin.getYuanjia());
        oprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        hownew.setText(shangpin.getXingjiuchengdu() + "成新");
        getway.setText(shangpin.getQuhuofangshi());
        type.setText(shangpin.getLeibie());
        scannum.setText(String.valueOf(shangpin.getLiulanrenshu()));
        detail.setText("  " + shangpin.getXijie());
        Bitmap bitmap = asyncBitmapLoader.loadBitmap(image, shangpin.getTupian().toString(), new AsyncBitmapLoader.ImageCallBack() {

            @Override
            public void imageLoad(ImageView imageView, Bitmap bitmap) {
                // TODO Auto-generated method stub
                imageView.setImageBitmap(bitmap);
            }
        });
        if (bitmap == null) {
            image.setImageResource(R.drawable.main1);
        } else {
            image.setImageBitmap(bitmap);
        }
    }

    /**
     * 查询商品评论
     */
    private void selectComment(int shangpingid) {
        DBService.getDbService().getCommentData(shangpingid)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        compositeDisposable.add(disposable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<pinlun>>() {
                    @Override
                    public void accept(List<pinlun> pinluns) throws Exception {
                        Log.e("ssss",pinluns.size()+"");
                        pinlunsList=pinluns;
                        adapter = new CommentExpandAdapter(shangpin_xiangqing.this, pinlunsList);
                        loaddata();
                    }
                });

    }

    public yonghu selectUser(int userid) {
        yonghu yonghu = SQLite.select()
                .from(yonghu.class)
                .where(yonghu_Table.yonghuid.eq(userid))
                .querySingle();
        return yonghu;
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buy:      //买
                    setBuy();
                    break;
                case R.id.commentbtn:      //评论
                    setCommentbtn();
                    break;
                default:
                    break;
            }
        }
    }

    public void setBuy() {
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        if (yonghu.getYonghuid() == shangpin.getYonghuid())
            Toast.makeText(shangpin_xiangqing.this, "不能买自己的宝贝", Toast.LENGTH_SHORT).show();
        else {
            AlertDialog.Builder normalDialog =
                    new AlertDialog.Builder(shangpin_xiangqing.this);
            normalDialog.setTitle("提示");
            normalDialog.setMessage("你确定要购买吗？");
            normalDialog.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            pd.show();

                            new Thread() {
                                @Override
                                public void run() {
                                    /*Map<String, String> params = new HashMap<String, String>();
                                    params.put("uid",Integer.toString(uid));
                                    params.put("guid",Integer.toString(guid));
                                    params.put("gid",Integer.toString(gid));
                                    String strUrlpath = getResources().getString(R.string.burl) + "TradeAction_Insert.action";
                                    String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                                    System.out.println("结果为：" + Result);
                                    Message message = new Message();
                                    message.what = 3;
                                    message.obj = Result;
                                    handler.sendMessage(message);*/
                                }
                            }.start();
                        }
                    });
            normalDialog.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //...To-do
                        }
                    });
            // 显示
            normalDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }


    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void setCommentbtn() {
        if (commentedt.getText().toString().trim().isEmpty()) {
            Toast.makeText(shangpin_xiangqing.this, "评论不能为空哦！", Toast.LENGTH_SHORT).show();
        } else {
            pd.setMessage("评论中...");
            pd.show();
            saveComment();
//            if(createComment()){
//                pd.cancel();
//                AlertDialog.Builder normalDialog =
//                        new AlertDialog.Builder(shangpin_xiangqing.this);
//                normalDialog.setTitle("提示");
//                normalDialog.setMessage("评论成功");
//                normalDialog.setPositiveButton("确定",null);
//                // 显示
//                normalDialog.show();
//                chushipinglunshu=pinlunsList.size();
//                commentnum.setText("("+chushipinglunshu+")");
//                layout_back.setVisibility(View.INVISIBLE);
//            }
        }
    }

    public void saveComment(){
        final pinlun pinlun = new pinlun();
        pinlun.setMaijiaid(shangpin.getYonghuid());
        pinlun.setNeirong(commentedt.getText().toString().trim());
        pinlun.setPinglunshijian(new Date());
        pinlun.setShangpingid(shangpin.getShangpinid());
        pinlun.setYonghuid(yonghu.getYonghuid());
        pinlun.setYonghu(yonghu);
        pinlun.setZhuangtai(0);
        DBService.getDbService().insertComment(pinlun)
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
                            adapter.addTheCommentData(pinlun);
                            pd.cancel();
                            AlertDialog.Builder normalDialog =
                                    new AlertDialog.Builder(shangpin_xiangqing.this);
                            normalDialog.setTitle("提示");
                            normalDialog.setMessage("评论成功");
                            normalDialog.setPositiveButton("确定", null);
                            // 显示
                            normalDialog.show();
                            chushipinglunshu = pinlunsList.size();
                            commentnum.setText("(" + chushipinglunshu + ")");
                            layout_back.setVisibility(View.INVISIBLE);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(shangpin_xiangqing.this);
                            builder.setTitle("确认");
                            builder.setMessage("评论失败");
                            builder.setPositiveButton("是", null);
                            builder.show();
                        }
                        pd.cancel();
                    }
                });
    }

    /**
     * 创建评论
     *
     * @return
     */
    public boolean createComment() {
        pinlun pinlun = new pinlun();
        pinlun.setMaijiaid(shangpin.getYonghuid());
        pinlun.setNeirong(commentedt.getText().toString().trim());
        pinlun.setPinglunshijian(new Date());
        pinlun.setShangpingid(shangpin.getShangpinid());
        pinlun.setYonghuid(yonghu.getYonghuid());
        pinlun.setYonghu(yonghu);
        pinlun.setZhuangtai(0);
        // 调用评论接口
        Log.i("1", shangpin.getYonghuid() + "");
        Log.i("2", commentedt.getText().toString().trim() + "");
        Log.i("3", shangpin.getShangpinid() + "");
        Log.i("4", yonghu.getYonghuid() + "");
        adapter.addTheCommentData(pinlun);
        return pinlun.save();
    }

    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<pinlun> commentList) {
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(adapter);
        for (int i = 0; i < commentList.size(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);

//                if(isExpanded){
//                    expandableListView.collapseGroup(groupPosition);
//                }else {
//                    expandableListView.expandGroup(groupPosition, true);
//                }
                showReplyDialog(groupPosition);
                return true;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(shangpin_xiangqing.this, "点击了回复", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //toast("展开第"+groupPosition+"个分组");

            }
        });

    }

    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private void showReplyDialog(final int position) {
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout, null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        Log.i("123", position + "");
        commentText.setHint("回复 " + pinlunsList.get(position).getYonghu().getXingming() + " 的评论:");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if (!TextUtils.isEmpty(replyContent)) {

                    dialog.dismiss();
                    // 加入对应的评论，这里没有数据库
                    final pinlunhuifu pinlunhuifu = new pinlunhuifu();
                    pinlunhuifu.setYonghu(yonghu);
                    pinlunhuifu.setNeirong(replyContent);
                    pinlunhuifu.setPinlunid(pinlunsList.get(position).getPinlunid());
                    pinlunhuifu.setYonghu(yonghu);
                    pinlunhuifu.setMaijiaid(shangpin.getYonghuid()); // 商品发布人
                    pinlunhuifu.setYonghuid(yonghu.getYonghuid());// 现在登录的用户id
                    pinlunhuifu.setShangpin(shangpin);
                    pinlunhuifu.setPinglunshijian(new Date());
                    DBService.getDbService().insertReplay(pinlunhuifu)
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
                                        adapter.addTheReplyData(pinlunhuifu, position);
                                        expandableListView.expandGroup(position);
                                        Toast.makeText(shangpin_xiangqing.this, "回复成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(shangpin_xiangqing.this);
                                        builder.setTitle("确认");
                                        builder.setMessage("回复失败");
                                        builder.setPositiveButton("是", null);
                                        builder.show();
                                    }
                                    pd.cancel();
                                }
                            });
                } else {
                    Toast.makeText(shangpin_xiangqing.this, "回复内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    //聊天
    public void chat(View view) {
        if (shangpin.getYonghuid() == yonghu.getYonghuid()) {
            layout_chat.setVisibility(View.INVISIBLE);
        } else {
            Intent intent = new Intent(shangpin_xiangqing.this, chat.class);
            intent.putExtra("shoujianren", shangpin.getYonghuid());
            intent.putExtra("dengluuser", yonghu);
            startActivity(intent);
        }

    }
}
