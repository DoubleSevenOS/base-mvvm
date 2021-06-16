package com.android.base;

import com.android.base.base.BaseViewProxy;

/**
 * @ClassName: com.android.base
 * @Description:
 * @Author: hyy
 * @Date: 2021/6/16
 * @Time: 5:49 PM
 */
public class BaseApplication {
    private static Class<BaseViewProxy> sClazz;

    /**
     * 业务代码可以传入代理类Class配置
     * <p>
     * 通过反射，动态创建BaseViewProxy。可以动态化实现相关操作
     *
     * @param clazz
     * @param <T>
     */
    public static <T extends BaseViewProxy> void registBaseViewProxy(Class<T> clazz) {
        sClazz = (Class<BaseViewProxy>) clazz;
    }

    public static Class<BaseViewProxy> getBaseViewProxyClass() {
        return sClazz;
    }

}
