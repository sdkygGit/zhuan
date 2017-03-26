package com.zhuanworld.dev.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.FileProvider;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.SelectImageActivity;
import com.zhuanworld.dev.adapter.ImageAdapter;
import com.zhuanworld.dev.adapter.ImageFolderPopuWindowAdapter;
import com.zhuanworld.dev.bean.Folder;
import com.zhuanworld.dev.bean.Image;
import com.zhuanworld.dev.bean.ImageFolder;
import com.zhuanworld.dev.bean.SelectImageContract;
import com.zhuanworld.dev.bean.SelectOptions;
import com.zhuanworld.dev.callback.PermissionGrantedCallBack;
import com.zhuanworld.dev.config.ImageLoaderListener;
import com.zhuanworld.dev.media.SpaceGridItemDecoration;
import com.zhuanworld.dev.utils.TDevice;
import com.zhuanworld.dev.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/11 0011.
 */
public class SelectFragment extends BaseFragment implements View.OnClickListener, ImageLoaderListener, ImageAdapter.OnItemClickListener {

    @Bind(R.id.rv_image)
    RecyclerView mContentView;
    @Bind(R.id.btn_title_select)
    Button mSelectFolderView;
    @Bind(R.id.iv_title_select)
    ImageView mSelectFolderIcon;
    @Bind(R.id.toolbar)
    View mToolbar;
    @Bind(R.id.btn_done)
    Button mDoneView;
    @Bind(R.id.btn_preview)
    Button mPreviewView;

    private final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.MINI_THUMB_MAGIC,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

    private List<Image> mSelectedImage = null;
    List<Folder> mFolders;
    private ImageAdapter mImageAdapter;
    private ImageFolderPopuWindowAdapter mImageFolderAdapter;

    AsyncQueryHandler asyncQueryHandler = null;

    LoaderListener mCursorLoader = new LoaderListener();

//    @Bind(R.id.error_layout)
//    private View mErrorLayout;

    private String mCamImageName;

    private static SelectOptions mOption;


    public static SelectFragment newInstance(SelectOptions options) {
        SelectFragment fragment = new SelectFragment();
        mOption = options;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_select_image;
    }

    @OnClick({R.id.btn_preview, R.id.icon_back, R.id.btn_title_select, R.id.btn_done})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                getActivity().finish();
                break;
            case R.id.btn_preview:
//                if (mSelectedImage.size() > 0) {
//                    ImageGalleryActivity.show(getActivity(), Util.toArray(mSelectedImage), 0, false);
//                }
                break;
            case R.id.btn_title_select:
                showPopupFolderList();
                break;
            case R.id.btn_done:
                onSelectComplete();
                break;
        }
    }

    PopupWindow mFolderPopuWindow;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showPopupFolderList() {
        if (mFolderPopuWindow == null) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.popuwindow_folder_dirs, null);
            view.findViewById(R.id.empty).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFolderPopuWindow.dismiss();
                }
            });
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.folder_recycleview);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(mImageFolderAdapter);
            mFolderPopuWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mFolderPopuWindow.showAsDropDown(mSelectFolderView, 0, 0, Gravity.BOTTOM);
        } else {
            if (!mFolderPopuWindow.isShowing()) {
                mFolderPopuWindow.showAsDropDown(mSelectFolderView, 0, 0, Gravity.BOTTOM);
            } else {
                mFolderPopuWindow.dismiss();
            }
        }
    }

    private void onSelectComplete() {
        handleResult();
    }

    @Override
    protected void initWidget(View view) {
        mContentView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mContentView.addItemDecoration(new SpaceGridItemDecoration((int) TDevice.dipToPx(getResources(), 1)));
        mImageAdapter = new ImageAdapter(getContext(), this);
//        mImageFolderAdapter = new ImageFolderAdapter(getActivity());
//        mImageFolderAdapter.setLoader(this);
        mContentView.setAdapter(mImageAdapter);
        mContentView.setItemAnimator(null);
        mImageAdapter.setOnItemClickListener(this);
//        mErrorLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getLoaderManager().initLoader(0, null, mCursorLoader);
//            }
//        });

        mImageFolderAdapter = new ImageFolderPopuWindowAdapter(mContext, getImgLoader());
        mImageFolderAdapter.setOnItemClick(new ImageFolderPopuWindowAdapter.OnItemClick() {
            @Override
            public void onItemClick(ImageFolder imageFolder) {
                List<Image> list = imageFolder.getImages();
                if (mOption.isHasCam() && list.get(0).getId() != 0) {
                    Image cam = new Image();
                    list.add(0, cam);
                }
                mSelectFolderView.setText(imageFolder.getName());
                mImageAdapter.updateAll(list);
                if (mFolderPopuWindow.isShowing())
                    mFolderPopuWindow.dismiss();
            }
        });
        asyncQueryHandler = new AsyncQueryHandler(mContext.getContentResolver()) {
            @Override
            protected void onQueryComplete(int token, Object cookie, Cursor data) {
                if (data != null) {
                    final ArrayList<Image> images = new ArrayList<>();
                    final List<ImageFolder> imageFolders = new ArrayList<>();
                    final ImageFolder defaultFolder = new ImageFolder();
                    defaultFolder.setName("全部照片");
                    defaultFolder.setPath("");
                    imageFolders.add(defaultFolder);

                    int count = data.getCount();
                    if (count > 0) {
                        data.moveToFirst();
                        do {
                            String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                            String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                            long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                            int id = data.getInt(data.getColumnIndexOrThrow(IMAGE_PROJECTION[3]));
                            String thumbPath = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[4]));
                            String bucket = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[5]));

                            Image image = new Image();
                            image.setPath(path);
                            image.setName(name);
                            image.setDate(dateTime);
                            image.setId(id);
                            image.setThumbPath(thumbPath);
                            image.setFolderName(bucket);

                            images.add(image);

                            //如果是新拍的照片
                            if (mCamImageName != null && mCamImageName.equals(image.getName())) {
                                image.setSelect(true);
                                mSelectedImage.add(image);
                            }

                            //如果是被选中的图片
                            if (mSelectedImage.size() > 0) {
                                for (Image i : mSelectedImage) {
                                    if (i.getPath().equals(image.getPath())) {
                                        image.setSelect(true);
                                    }
                                }
                            }

                            File imageFile = new File(path);
                            File folderFile = imageFile.getParentFile();
                            ImageFolder folder = new ImageFolder();
                            folder.setName(folderFile.getName());
                            folder.setPath(folderFile.getAbsolutePath());
                            if (!imageFolders.contains(folder)) {
                                folder.getImages().add(image);
                                folder.setAlbumPath(image.getPath());//默认相册封面
                                imageFolders.add(folder);
                            } else {
                                // 更新
                                ImageFolder f = imageFolders.get(imageFolders.indexOf(folder));
                                f.getImages().add(image);
                            }


                        } while (data.moveToNext());
                    }
                    addImagesToAdapter(images);
                    defaultFolder.getImages().addAll(images);

                    if (mOption.isHasCam()) {
                        defaultFolder.setAlbumPath(images.size() > 1 ? images.get(1).getPath() : null);
                    } else {
                        defaultFolder.setAlbumPath(images.size() > 0 ? images.get(0).getPath() : null);
                    }
                    mImageFolderAdapter.setDatas(imageFolders);

                    //删除掉不存在的，在于用户选择了相片，又去相册删除
                    if (mSelectedImage.size() > 0) {
                        List<Image> rs = new ArrayList<>();
                        for (Image i : mSelectedImage) {
                            File f = new File(i.getPath());
                            if (!f.exists()) {
                                rs.add(i);
                            }
                        }
                        mSelectedImage.removeAll(rs);
                    }

                    // If add new mCamera picture, and we only need one picture, we result it.
//                if (mOption.getSelectCount() == 1 && mCamImageName != null) {
//                    handleResult();
//                }

                    handleSelectSizeChange(mSelectedImage.size());
//                mErrorLayout.setVisibility(View.GONE);
                }
            }

            @Override
            protected Handler createHandler(Looper looper) {
                return super.createHandler(looper);
            }
        };

    }

    @Override
    protected void initData() {
        mSelectedImage = new ArrayList<>();
        mFolders = new ArrayList<>();
        if (mOption.getSelectCount() > 1 && mOption.getSelectedImages() != null) {
            List<String> images = mOption.getSelectedImages();
            for (String s : images) {
                // checkShare file exists
                if (s != null && new File(s).exists()) {
                    Image image = new Image();
                    image.setSelect(true);
                    image.setPath(s);
                    mSelectedImage.add(image);
                }
            }
        }
//        getLoaderManager().initLoader(0, null, mCursorLoader);
        asyncQueryHandler.startQuery(0, null, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.MINI_THUMB_MAGIC,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME}, null, null, MediaStore.Images.Media.DATE_ADDED + " DESC");

    }

    @Override
    public void displayImage(ImageView iv, String path) {
        getImgLoader().load(path)
                .asBitmap()
                .centerCrop()
                .error(R.mipmap.ic_split_graph)
                .into(iv);
    }

    @Override
    public void onItemClick(int position, long itemId) {
        if (mOption.isHasCam()) {
            if (position != 0) {
                handleSelectChange(position);
            } else {
                if (mSelectedImage.size() < mOption.getSelectCount()) {
                    if (((SelectImageActivity) mContext).requestCamera()) {
                        toOpenCamera();
                    } else {
                        ((SelectImageActivity) mContext).setPermissionCallBack(new PermissionGrantedCallBack() {
                            @Override
                            public void onPermissionsDenied(int requestCode, List<String> perms) {
                                if (requestCode == SelectImageActivity.RC_CAMERA_PERM)
                                    toOpenCamera();
                            }
                        });
                    }
                } else {
                    Toast.makeText(getActivity(), "最多只能选择 " + mOption.getSelectCount() + " 张图片", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            handleSelectChange(position);
        }
    }

    private void handleSelectChange(int position) {
        Image image = mImageAdapter.getItem(position);
        //如果是多选模式
        final int selectCount = mOption.getSelectCount();
        if (selectCount > 1) {
            if (image.isSelect()) {
                image.setSelect(false);
                mSelectedImage.remove(image);
                mImageAdapter.updateItem(position);
            } else {
                if (mSelectedImage.size() == selectCount) {
                    Toast.makeText(getActivity(), "最多只能选择 " + selectCount + " 张照片", Toast.LENGTH_SHORT).show();
                } else {
                    image.setSelect(true);
                    mSelectedImage.add(image);
                    mImageAdapter.updateItem(position);
                }
            }
            handleSelectSizeChange(mSelectedImage.size());
        } else {

            if (!image.isSelect()) {
//                image.setSelect(true);
                mSelectedImage.clear();
                mSelectedImage.add(image);

            } else {
                mSelectedImage.clear();
            }
            mImageAdapter.switchSelectItem(position);
        }
    }

    private void handleSelectSizeChange(int size) {
        if (size > 0) {
            mPreviewView.setEnabled(true);
            mDoneView.setEnabled(true);
            mDoneView.setText(String.format("%s(%s)", "完成", size));
        } else {
            mPreviewView.setEnabled(false);
            mDoneView.setEnabled(false);
            mDoneView.setText("完成");
        }
    }

    private void handleResult() {
        if (mSelectedImage.size() != 0)
            Toast.makeText(mContext, mSelectedImage.get(0).getPath(), Toast.LENGTH_SHORT).show();

//        if (mSelectedImage.size() != 0) {
//            if (mOption.isCrop()) {
//                List<String> selectedImage = mOption.getSelectedImages();
//                selectedImage.clear();
//                selectedImage.add(mSelectedImage.get(0).getPath());
//                mSelectedImage.clear();
//
//                //跳转至裁剪
////                CropActivity.show(this, mOption);
//            } else {
//                mOption.getCallback().doSelected(toArray(mSelectedImage));
//                getActivity().finish();
//            }
//        }
    }

    public String[] toArray(List<Image> images) {
        if (images == null)
            return null;
        int len = images.size();
        if (len == 0)
            return null;

        String[] strings = new String[len];
        int i = 0;
        for (Image image : images) {
            strings[i] = image.getPath();
            i++;
        }
        return strings;
    }

    private class LoaderListener implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            if (id == 0) {
                //数据库光标加载器
                return new CursorLoader(getContext(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        null, null, IMAGE_PROJECTION[2] + " DESC");
            }
            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {
            if (data != null) {
                final ArrayList<Image> images = new ArrayList<>();
                final List<ImageFolder> imageFolders = new ArrayList<>();
                final ImageFolder defaultFolder = new ImageFolder();
                defaultFolder.setName("全部照片");
                defaultFolder.setPath("");
                imageFolders.add(defaultFolder);

                int count = data.getCount();
                if (count > 0) {
                    data.moveToFirst();
                    do {
                        String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                        String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                        long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                        int id = data.getInt(data.getColumnIndexOrThrow(IMAGE_PROJECTION[3]));
                        String thumbPath = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[4]));
                        String bucket = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[5]));

                        Image image = new Image();
                        image.setPath(path);
                        image.setName(name);
                        image.setDate(dateTime);
                        image.setId(id);
                        image.setThumbPath(thumbPath);
                        image.setFolderName(bucket);

                        images.add(image);

                        //如果是新拍的照片
//                        if (mCamImageName != null && mCamImageName.equals(image.getName())) {
//                            image.setSelect(true);
//                            mSelectedImage.add(image);
//                        }

                        //如果是被选中的图片
                        if (mSelectedImage.size() > 0) {
                            for (Image i : mSelectedImage) {
                                if (i.getPath().equals(image.getPath())) {
                                    image.setSelect(true);
                                }
                            }
                        }

                        File imageFile = new File(path);
                        File folderFile = imageFile.getParentFile();
                        ImageFolder folder = new ImageFolder();
                        folder.setName(folderFile.getName());
                        folder.setPath(folderFile.getAbsolutePath());
                        if (!imageFolders.contains(folder)) {
                            folder.getImages().add(image);
                            folder.setAlbumPath(image.getPath());//默认相册封面
                            imageFolders.add(folder);
                        } else {
                            // 更新
                            ImageFolder f = imageFolders.get(imageFolders.indexOf(folder));
                            f.getImages().add(image);
                        }


                    } while (data.moveToNext());
                }
                addImagesToAdapter(images);
                defaultFolder.getImages().addAll(images);

                if (mOption.isHasCam()) {
                    defaultFolder.setAlbumPath(images.size() > 1 ? images.get(1).getPath() : null);
                } else {
                    defaultFolder.setAlbumPath(images.size() > 0 ? images.get(0).getPath() : null);
                }
//                mImageFolderAdapter.resetItem(imageFolders);

                //删除掉不存在的，在于用户选择了相片，又去相册删除
                if (mSelectedImage.size() > 0) {
                    List<Image> rs = new ArrayList<>();
                    for (Image i : mSelectedImage) {
                        File f = new File(i.getPath());
                        if (!f.exists()) {
                            rs.add(i);
                        }
                    }
                    mSelectedImage.removeAll(rs);
                }

                // If add new mCamera picture, and we only need one picture, we result it.
//                if (mOption.getSelectCount() == 1 && mCamImageName != null) {
//                    handleResult();
//                }

                handleSelectSizeChange(mSelectedImage.size());
//                mErrorLayout.setVisibility(View.GONE);
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    }

    private void addImagesToAdapter(ArrayList<Image> images) {
        mImageAdapter.clear();
//        //能否拍照
        if (mOption.isHasCam()) {
            Image cam = new Image();
            mImageAdapter.addItem(cam);
        }
        mImageAdapter.addAll(images);
    }

    @Override
    public void onDestroy() {
        mOption = null;
        super.onDestroy();
    }

    /**
     * 打开相机
     */
    private void toOpenCamera() {
        // 判断是否挂载了SD卡
        mCamImageName = null;
        String savePath = "";
        if (Utils.hasSDCard()) {
            savePath = Utils.getCameraPath();
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
        }

        // 没有挂载SD卡，无法保存文件
        if (TextUtils.isEmpty(savePath)) {
            Toast.makeText(getActivity(), "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_LONG).show();
            return;
        }

        mCamImageName = Utils.getSaveImageFullName();
        File out = new File(savePath, mCamImageName);

        /**
         * android N 系统适配
         */
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(getContext(), "net.oschina.app.provider", out);
        } else {
            uri = Uri.fromFile(out);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent,
                0x03);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            switch (requestCode) {
                case 0x03:
                    if (mCamImageName == null) return;
                    //请求相册更新
                    Uri localUri = Uri.fromFile(new File(Utils.getCameraPath() + mCamImageName));
                    Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
                    getActivity().sendBroadcast(localIntent);

                    ((SelectImageActivity) mContext).selectComplete(mSelectedImage);

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            asyncQueryHandler.startQuery(0, null, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{
                                    MediaStore.Images.Media.DATA,
                                    MediaStore.Images.Media.DISPLAY_NAME,
                                    MediaStore.Images.Media.DATE_ADDED,
                                    MediaStore.Images.Media._ID,
                                    MediaStore.Images.Media.MINI_THUMB_MAGIC,
                                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME}, null, null, MediaStore.Images.Media.DATE_ADDED + " DESC");
                        }
                    }, 200);

                    break;
            }
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };
}
