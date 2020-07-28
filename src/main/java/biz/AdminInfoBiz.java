package biz;

import bean.PersonInfo;

import java.util.List;
//接口类，用于抽取dao包的方法
public interface AdminInfoBiz {
    List<PersonInfo> findAllPerson(Integer adminId);//得到所有人信息
    boolean judgeLogin(String adminName, String adminPassWord);//判断是否登录成功
    Integer getAdminIdByAdminName(String adminName);//得到已经登录人的id，用于后序功能
    int removeOnePerson(Integer personId);//删除某人
    Integer changeSomePerson(PersonInfo pi);//修改某人信息
    List<PersonInfo> sortPerson(Integer adminId, Object obj);//按照要求进行排序
    int addSomePerson(PersonInfo pi);//添加用户
}
