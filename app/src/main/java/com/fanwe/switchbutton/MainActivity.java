package com.fanwe.switchbutton;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;

import com.fanwe.lib.selectmanager.SDSelectManager;
import com.fanwe.lib.switchbutton.FISwitchButton;
import com.fanwe.lib.switchbutton.FSwitchButton;
import com.fanwe.library.activity.SDBaseActivity;
import com.fanwe.library.adapter.SDSimpleRecyclerAdapter;
import com.fanwe.library.adapter.viewholder.SDRecyclerViewHolder;
import com.fanwe.library.utils.LogUtil;
import com.fanwe.library.view.SDRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SDBaseActivity
{
    private FSwitchButton sb_rect;

    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
        sb_rect = findViewById(R.id.sb_rect);

        testRecyclerView();

        sb_rect.setOnCheckedChangedCallback(new FSwitchButton.OnCheckedChangedCallback()
        {
            @Override
            public void onCheckedChanged(boolean checked, FSwitchButton view)
            {
                LogUtil.i("onCheckedChanged:" + checked);
            }
        });
        sb_rect.setOnViewPositionChangedCallback(new FISwitchButton.OnViewPositionChangedCallback()
        {
            @Override
            public void onViewPositionChanged(FSwitchButton view)
            {
                LogUtil.i("percent:" + view.getScrollPercent());
                float percent = view.getScrollPercent() * 0.8f + 0.2f;
                ViewCompat.setScaleY(view.getViewNormal(), percent);
                ViewCompat.setScaleY(view.getViewChecked(), percent);
            }
        });
    }


    private SDRecyclerView rv_content;

    private void testRecyclerView()
    {
        rv_content = findViewById(R.id.rv_content);

        List<SDSelectManager.SelectableModel> listModel = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            SDSelectManager.SelectableModel model = new SDSelectManager.SelectableModel();
            listModel.add(model);
        }

        SDSimpleRecyclerAdapter<SDSelectManager.SelectableModel> adapter = new SDSimpleRecyclerAdapter<SDSelectManager.SelectableModel>(this)
        {
            @Override
            public void onBindData(SDRecyclerViewHolder<SDSelectManager.SelectableModel> holder, int position, final SDSelectManager.SelectableModel model)
            {
                FSwitchButton sb = holder.get(R.id.sb);
                sb.setChecked(model.isSelected(), false, false);
                sb.setOnCheckedChangedCallback(new FISwitchButton.OnCheckedChangedCallback()
                {
                    @Override
                    public void onCheckedChanged(boolean checked, FSwitchButton view)
                    {
                        model.setSelected(checked);
                    }
                });
            }

            @Override
            public int getLayoutId(ViewGroup parent, int viewType)
            {
                return R.layout.item_listview;
            }
        };
        adapter.setData(listModel);

        rv_content.setAdapter(adapter);
    }
}
