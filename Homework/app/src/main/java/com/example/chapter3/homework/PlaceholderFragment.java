package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.widget.*;

public class PlaceholderFragment extends Fragment {
    ListView list;
    private String[] name= {"AA","BB","CC","DD"};
    private String[] num = {"13999999999","15050000000","18666666666","13666666666"};
    private ArrayList<Map<String,Object>> data = new ArrayList<Map<String,Object>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_placeholder, null);
        list = (ListView)view.findViewById(R.id.listview);
        for(int i = 0; i < num.length; i ++){
            Map<String,Object> item = new HashMap<String, Object>();
            item.put("name", name[i]);
            item.put("num", num[i]);
            data.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),data,android.R.layout.simple_expandable_list_item_2,
                new String[]{"name","num"},new int[]{android.R.id.text1,android.R.id.text2});
        list.setAdapter(adapter);


        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                View lottie = getView().findViewById(R.id.animation_view);
                View listview = getView().findViewById(R.id.listview);
                listview.setVisibility(View.VISIBLE);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(lottie,"alpha",1.0f,0);
                animator1.setDuration(1000);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(listview,"alpha",0f,1.0f);
                animator2.setDuration(1000);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator1,animator2);
                animatorSet.start();





                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
            }
        }, 5000);
    }
}
