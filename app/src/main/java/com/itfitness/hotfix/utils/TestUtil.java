package com.itfitness.hotfix.utils;

/**
 * @ProjectName: HotFix
 * @Package: com.itfitness.hotfix.utils
 * @ClassName: TestUtil
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/11/1 19:04
 * @UpdateUser: 更新者：itfitness
 * @UpdateDate: 2020/11/1 19:04
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class TestUtil {
    public static void test(){
        throw new IndexOutOfBoundsException("越界");
    }
}
