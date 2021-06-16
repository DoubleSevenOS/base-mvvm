package com.example.myapplicationt.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationt.BR;
import com.example.myapplicationt.R;
import com.example.myapplicationt.adapter.TestAdatapter;
import com.example.myapplicationt.base.BaseBindingFragment;
import com.example.myapplicationt.databinding.FragmentActBinding;
import com.example.myapplicationt.viewmodel.ActViewModel;
import com.android.base.base.BaseDataBindingConfig;
import com.android.base.fragment.BaseDataBindFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ActFragment extends BaseBindingFragment<FragmentActBinding, ActViewModel> {

    private TestAdatapter testAdatapter;
    private List<String> itemDatas;

    @NonNull
    @Override
    public BaseDataBindingConfig getDataBindingConfig() {
        itemDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemDatas.add("item:" + i);
        }
        testAdatapter = new TestAdatapter(itemDatas);
        return new BaseDataBindingConfig(R.layout.fragment_act)
                .addBindingParam(BR.adapter, testAdatapter);
    }

    @NonNull
    @Override
    public Class<ActViewModel> initViewModelClazz() {
        return ActViewModel.class;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}