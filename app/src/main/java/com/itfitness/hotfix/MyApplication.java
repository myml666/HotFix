package com.itfitness.hotfix;

import android.app.Application;
import android.os.Environment;

import com.itfitness.hotfix.utils.HotFixUtil;

import java.io.File;


/**
 * @ProjectName: HotFix
 * @Package: com.itfitness.hotfix
 * @ClassName: MyApplication
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/11/1 19:07
 * @UpdateUser: 更新者：itfitness
 * @UpdateDate: 2020/11/1 19:07
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HotFixUtil.init(this,new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"patch.jar"));
    }
}
