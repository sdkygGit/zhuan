package com.zhuanworld.dev.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.gson.reflect.TypeToken;
import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.adapter.NavItemDragAdapter;
import com.zhuanworld.dev.app.AppOperator;
import com.zhuanworld.dev.bean.SubTab;
import com.zhuanworld.dev.utils.StreamUtil;
import com.zhuanworld.dev.utils.TDevice;
import com.zhuanworld.dev.widget.NavItemDragModifyWrapperView;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class NavItemModifyDialogActivity extends Activity {
    NavItemDragModifyWrapperView active;
    NavItemDragModifyWrapperView also;
    Button save;

    boolean isOnlyShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_navitemmodify);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.TOP;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        init();
    }

    private void init() {
        isOnlyShow = getIntent().getBooleanExtra("isOnlyShow", false);
        active = (NavItemDragModifyWrapperView) findViewById(R.id.recycler_active);

        also = (NavItemDragModifyWrapperView) findViewById(R.id.recycler_also);

        if (isOnlyShow) {
            also.setVisibility(View.GONE);
            active.setIs_drag(false);
        }
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDatas();
            }
        });
        initData();
        active.setOnItemClick(new NavItemDragModifyWrapperView.OnItemClick() {
            @Override
            public void itemClick(RecyclerView.ViewHolder holder) {
                if (holder != null) {
                    if (!isOnlyShow) {
                        also.addItem((SubTab) holder.itemView.getTag());
                        active.removeItem(holder);
                        saveDatas();
                    } else {

                        Intent intent = new Intent();
                        intent.putExtra("index", holder.getAdapterPosition());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }

            @Override
            public void onDataChange() {
                saveDatas();
            }
        });
        if (!isOnlyShow)
            also.setOnItemClick(new NavItemDragModifyWrapperView.OnItemClick() {
                @Override
                public void itemClick(RecyclerView.ViewHolder holder) {
                    if (holder != null) {
                        active.addItem((SubTab) holder.itemView.getTag());
                        also.removeItem(holder);
                        saveDatas();
                    }
                }

                @Override
                public void onDataChange() {
                }
            });
    }

    void initData() {
        InputStreamReader reader = null;
        File file = AppContext.getInstance().getFileStreamPath("sub_tab_active.json");
        List<SubTab> mActiveData = null;
        if (file.exists()) {
            try {
                reader = new FileReader(file);
                mActiveData = AppOperator.getGson().fromJson(reader,
                        new TypeToken<ArrayList<SubTab>>() {
                        }.getType());

                active.setData(mActiveData);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                StreamUtil.close(reader);
            }
        }

        if (!isOnlyShow) {
            try {
                reader = new InputStreamReader(
                        AppContext.getInstance().getAssets().open("sub_tab_original.json")
                        , "UTF-8");
                List<SubTab> mActiveStore = AppOperator.getGson().<ArrayList<SubTab>>fromJson(reader,
                        new TypeToken<ArrayList<SubTab>>() {
                        }.getType());

                if (mActiveData != null && mActiveData.size() > 0)
                    for (SubTab subTab : mActiveData) {
                        mActiveStore.remove(subTab);
                    }
                also.setData(mActiveStore);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamUtil.close(reader);
            }
        }
    }

    void saveDatas() {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(
                    AppContext.getInstance().openFileOutput(
                            "sub_tab_active.json", Context.MODE_PRIVATE)
                    , "UTF-8");
            AppOperator.getGson().toJson(active.getActiveData(), writer);
            AppContext.set("TabsMask", TDevice.getVersionCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StreamUtil.close(writer);
        }
    }


}
