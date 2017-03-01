package com.android.tao.fixedsizehashmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.tao.fixedsizehashmap.util.FixedSizeHashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.btn_obtain)
    Button mBtn;
    @BindView(R.id.et_element)
    EditText mEtElement;
    private FixedSizeHashMap<String, String> mHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mHashMap = new FixedSizeHashMap<>();
        mHashMap.put("apple", "苹果");
        mHashMap.put("peach", "桃子");
        /*mHashMap.put("banana", "香蕉");
        mHashMap.put("watermelon", "西瓜");*/
    }


    public void printHashMap(HashMap hashMap) {
        Log.v(TAG, "****************************获取元素*************************");
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Log.w(TAG, entry.getKey() + "=" + entry.getValue());
        }
    }

    @OnClick(R.id.btn_obtain)
    public void onClickButton(View view) {
        printHashMap(mHashMap);
    }

    @OnClick(R.id.btn_add)
    public void onClickAdd() {

        String element = mEtElement.getText().toString().trim();
        if (TextUtils.isEmpty(element)) return;
        Log.e(TAG, "**********************添加元素**********************");
        mHashMap.put(element.substring(0, 1), element);
    }

}
