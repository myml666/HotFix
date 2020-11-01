package com.itfitness.hotfix.utils;

import android.content.Context;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * @ProjectName: HotFix
 * @Package: com.itfitness.hotfix.utils
 * @ClassName: HotFixUtil
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/11/1 19:34
 * @UpdateUser: 更新者：itfitness
 * @UpdateDate: 2020/11/1 19:34
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HotFixUtil {
    /**
     * 热修复
     * @param context
     * @param patchFile 修复后的文件
     */
    public static void init(Context context, File patchFile){
        if(!patchFile.exists()){
            return;
        }
        //拿到ClassLoader
        ClassLoader classLoader = context.getClassLoader();
        try {
            //拿到pathList属性
            Field pathListField = ReflectUtils.getField(classLoader, "pathList");
            //根据属性拿到该属性的对象DexPathList
            Object pathList = pathListField.get(classLoader);

            //根据pathList拿到dexElements属性
            Field dexElementsField = ReflectUtils.getField(pathList, "dexElements");
            Object[] dexElements = (Object[]) dexElementsField.get(pathList);//dexElements是个数组

            //根据补丁包创建dexElements
            Method makeDexElementsMethod = ReflectUtils.getMethod(pathList, "makeDexElements", List.class, File.class, List.class,ClassLoader.class);
            //创建执行方法的第一个参数
            List<File> dexfiles = new ArrayList<>();
            dexfiles.add(patchFile);
            //创建执行方法的第二个参数
            File codeCacheDir = context.getCodeCacheDir();
            //创建执行方法的第三个参数
            ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
            Object[] pathDexElements = (Object[]) makeDexElementsMethod.invoke(null, dexfiles, codeCacheDir, suppressedExceptions,classLoader);

            //根据以前的和补丁包生成的dexElements组合成一个新的Elements
            Object[] newDexElements = (Object[]) Array.newInstance(dexElements[0].getClass(), dexElements.length + pathDexElements.length);

            //将pathDexElements和dexElements的元素拷贝到新的数组中
            System.arraycopy(pathDexElements,0,newDexElements,0,pathDexElements.length);
            System.arraycopy(dexElements,0,newDexElements,pathDexElements.length,dexElements.length);

            //重新将dexElements属性赋值为新产生的数组
            dexElementsField.set(pathList,newDexElements);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
