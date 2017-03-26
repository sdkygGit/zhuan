package com.zhuanworld.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.ImageFolder;
import com.zhuanworld.dev.widget.NavItemDragModifyWrapperView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18 0018.
 */
public class ImageFolderPopuWindowAdapter extends RecyclerView.Adapter<FolderViewHolder> {
    List<ImageFolder> mImageFolders;
    Context context;
    RequestManager mRequestManger;

    public ImageFolderPopuWindowAdapter(Context context, RequestManager mRequestManger) {
        this.mRequestManger = mRequestManger;
        this.context = context;
    }

    public void setDatas(List<ImageFolder> folders) {
        if (this.mImageFolders == null) {
            this.mImageFolders = new ArrayList<>();
        } else if (mImageFolders.size() > 0) {
            mImageFolders.clear();
        }
        mImageFolders.addAll(folders);
    }

    @Override
    public FolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.folder_item, null);
        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FolderViewHolder holder, final int position) {
        mRequestManger.load(mImageFolders.get(position).getAlbumPath()).error(R.mipmap.ic_launcher).into(holder.folderIv);
        holder.folderPath.setText("/" + mImageFolders.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(mImageFolders.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mImageFolders == null)
            return 0;
        return mImageFolders.size();
    }


    OnItemClick listener;

    public void setOnItemClick(OnItemClick listener) {
        this.listener = listener;
    }

    public interface OnItemClick {
        void onItemClick(ImageFolder imageFolder);
    }
}

class FolderViewHolder extends RecyclerView.ViewHolder {
    public ImageView folderIv;
    public TextView folderPath;
    public View root;

    public FolderViewHolder(View itemView) {
        super(itemView);
        folderIv = (ImageView) itemView.findViewById(R.id.folder_icon);
        folderPath = (TextView) itemView.findViewById(R.id.folder_path);
        root = itemView.findViewById(R.id.root);
    }
}

