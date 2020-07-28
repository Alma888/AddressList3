package biz.impl;

import bean.AdminInfo;
import bean.PersonInfo;
import biz.AdminInfoBiz;
import dao.AdminInfoDAO;

import java.util.List;
//实现类，抽取dao包方法

public class AdminInfoBizImpl implements AdminInfoBiz {
    private AdminInfoDAO ad = new AdminInfoDAO();

    //找出所有属于该通讯录的人
    @Override
    public List<PersonInfo> findAllPerson(Integer adminId) {
        return ad.findAllPerson(adminId);
    }

    //判断是否登陆成功
    @Override
    public boolean judgeLogin(String adminName, String adminPassWord) {
        return ad.judgeLogin(adminName, adminPassWord);
    }

    @Override
    public Integer getAdminIdByAdminName(String adminName) {
        return ad.getAdminIdByAdminName(adminName);
    }

    @Override
    public int removeOnePerson(Integer personId) {
        return ad.removeOnePerson(personId);
    }

    @Override
    public Integer changeSomePerson(PersonInfo pi) {
        return ad.changeSomePerson(pi);
    }

    @Override
    public List<PersonInfo> sortPerson(Integer adminId,Object obj) {
        return ad.sortPerson(adminId,obj);
    }

    @Override
    public int addSomePerson(PersonInfo pi) {
        return ad.addSomePerson(pi);
    }
}
