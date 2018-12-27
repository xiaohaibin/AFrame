package com.xhb.aframelibrary.utils;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;

/**
 * @author: xiaohaibin.
 * @time: 2018/12/27
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 壁纸工具类
 */
public class WallPaperHepler {

    /**
     * 设置壁纸（兼容大部分手机）
     * @param context
     * @param path
     */
    @SuppressLint("MissingPermission")
    private static void intent2SetWallPaper(Context context, String path) {
        Uri uriPath = getUriWithPath(context, path);
        Intent intent;
        if (RoomUtils.isEmui()) {
            try {
                ComponentName componentName = new ComponentName("com.android.gallery3d", "com.android.gallery3d.app.Wallpaper");
                intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(uriPath, "image/*");
                intent.putExtra("mimeType", "image/*");
                intent.setComponent(componentName);
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    WallpaperManager.getInstance(context.getApplicationContext()).setBitmap(ImageUtils.getBitmap(path));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (RoomUtils.isMiui()) {
            try {
                ComponentName componentName = new ComponentName("com.android.thememanager", "com.android.thememanager.activity.WallpaperDetailActivity");
                intent = new Intent("miui.intent.action.START_WALLPAPER_DETAIL");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(uriPath, "image/*");
                intent.putExtra("mimeType", "image/*");
                intent.setComponent(componentName);
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    WallpaperManager.getInstance(context.getApplicationContext()).setBitmap(ImageUtils.getBitmap(path));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                context.startActivity(WallpaperManager.getInstance(context.getApplicationContext())
                        .getCropAndSetWallpaperIntent(getUriWithPath(context, path)));
            } else {
                try {
                    WallpaperManager.getInstance(context.getApplicationContext()).setBitmap(ImageUtils.getBitmap(path));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private static Uri getUriWithPath(Context context, String path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, context.getPackageName() + ".provider", new File(path));
        } else {
            return Uri.fromFile(new File(path));
        }
    }
}
