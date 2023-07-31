package com.example.mobilephoneshop.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mobilephoneshop.R;
import com.example.mobilephoneshop.base.BaseFragment;
import com.example.mobilephoneshop.community.fragment.CommunityFragment;
import com.example.mobilephoneshop.home.fragment.HomeFragment;
import com.example.mobilephoneshop.shoppingcart.fragment.ShoppingCartFragment;
import com.example.mobilephoneshop.type.fragment.TypeFragment;
import com.example.mobilephoneshop.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_type)
    RadioButton rbType;
    @Bind(R.id.rb_community)
    RadioButton rbCommunity;
    @Bind(R.id.rb_cart)
    RadioButton rbCart;
    @Bind(R.id.rb_user)
    RadioButton rbUser;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private  int position=0;

    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
initListener();
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }
 private  BaseFragment getFragment(int position) {
     if (fragments != null && fragments.size() > 0) {
         BaseFragment baseFragment = fragments.get(position);
         return baseFragment;
     }
     return null;
 }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
        }         if (nextFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if (!nextFragment.isAdded()) {

                    if (fromFragment != null) {
                         transaction.hide(fromFragment);
                         }
                transaction.add(R.id.frameLayout, nextFragment).commit();
                     } else {

                    if (fromFragment != null) {
                              transaction.hide(fromFragment);                 }
                         transaction.show(nextFragment).commit();             }
                 }
           }






        private  void initListener(){
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
               switch (i){
                   case R.id.rb_home:
                       position=0;
                   break;
                   case R.id.rb_type:
                       position=1;
                       break;
                   case R.id.rb_community:
                       position=2;
                       break;
                   case R.id.rb_cart:
                       position=3;
                       break;
                   case R.id.rb_user:
                       position=4;
                       break;
                       default:
                      position=0;
                         break;
               }
                BaseFragment baseFragment=getFragment(position);
               switchFragment(tempFragment,baseFragment);
            }
        });


        rgMain.check(R.id.rb_home);
}

}
