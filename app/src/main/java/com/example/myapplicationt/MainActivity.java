package com.example.myapplicationt;

import com.android.base.activity.BaseDataBindingActivity;
import com.android.base.base.BaseDataBindingConfig;
import com.example.myapplicationt.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;

public class MainActivity extends BaseDataBindingActivity<ActivityMainBinding, MainViewModel> {

    @NonNull
    @Override
    public BaseDataBindingConfig getDataBindingConfig() {
        return new BaseDataBindingConfig(R.layout.activity_main)
                .addBindingParam(BR.vm, mViewModel);
    }

    @NonNull
    @Override
    public Class<MainViewModel> initViewModelClazz() {
        return MainViewModel.class;
    }

    @Override
    public void initView() {
//        NavController navController = Navigation.findNavController(this, R.id.frag_nav);
//        mBinding.navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId() == R.id.home) {
//                    navController.navigate(R.id.home_frag);
//                    return true;
//                } else if (item.getItemId() == R.id.act) {
//                    navController.navigate(R.id.act_frag);
//                    return true;
//                } else if (item.getItemId() == R.id.mine) {
//                    navController.navigate(R.id.mine_frag);
//                    return true;
//                }
//
//                return false;
//            }
//        });
    }

    @Override
    public void initData() {

    }
}