package com.jusenr.chat.home;

import android.os.Bundle;

import com.jusenr.chat.R;
import com.jusenr.chatlibrary.controller.BaseFragment;

/**
 * Created by T5-Jusenr on 2017/4/4.
 */

public class ConversationFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_conversation;
    }

    @Override
    public void onViewCreatedFinish(Bundle savedInstanceState) {

    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }
}