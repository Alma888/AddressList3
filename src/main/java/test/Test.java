package test;

import biz.AdminInfoBiz;
import biz.impl.AdminInfoBizImpl;
import dao.AdminInfoDAO;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Test {
    private static AdminInfoBiz ab = new AdminInfoBizImpl();
    public static void main(String[] args) {
        System.out.println(ab.getAdminIdByAdminName("无敌"));
    }
}
