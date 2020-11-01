package com.itfitness.hotfix.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ProjectName: HotFix
 * @Package: com.itfitness.hotfix.utils
 * @ClassName: ReflectUtils
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/11/1 19:38
 * @UpdateUser: 更新者：itfitness
 * @UpdateDate: 2020/11/1 19:38
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ReflectUtils {
    /**
     * 根据属性名找到对应属性
     * @param instance
     * @param name
     * @return
     */
    public static Field getField(Object instance,String name){
        Class<?> cls = instance.getClass();
        while (cls != Object.class){
            try {
                Field fied = cls.getDeclaredField(name);
                if(fied != null){
                    fied.setAccessible(true);
                }
                return fied;
            }catch (Exception e){
            }
            //假如当前的对象中没有这个属性就从父类中继续寻找，直到找到为止
            cls = cls.getSuperclass();
        }
        return null;
    }

    /**
     * 根据方法名找到对应方法
     * @param instance
     * @param name
     * @param parameterTypes
     * @return
     */
    public static Method getMethod(Object instance, String name,Class<?>... parameterTypes){
        Class<?> cls = instance.getClass();
        while (cls != Object.class){
            try {
                Method method = cls.getDeclaredMethod(name,parameterTypes);
                if(method != null){
                    method.setAccessible(true);
                }
                return method;
            }catch (Exception e){
            }
            cls = cls.getSuperclass();
        }
        return null;
    }
}
