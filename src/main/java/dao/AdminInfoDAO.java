package dao;

import bean.PersonInfo;
import db.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//具体操作实现，比如查找，修改，删除，与数据库交互
public class AdminInfoDAO {
    private Connection conn;//数据库连接对象
    private PreparedStatement ps;//邮差对象，负责执行sql语句
    private ResultSet rs;//查询结果集对象，得到查询的结果集

    //查找所有用户
    public List<PersonInfo> findAllPerson(Integer adminId){
         conn = DBManager.getConnection();
         String sql = "select * from personinfo where adminid = ?";
        List<PersonInfo> list = new ArrayList<>();//声明一个集合，用于查询得到的用户信息
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,adminId);//给?占位符赋值
            rs = ps.executeQuery();
            while (rs.next()){
                PersonInfo pi = new PersonInfo();//产生一个PersonInfo对象并给其属性赋值
                pi.setPersonId(rs.getInt(1));
                pi.setPersonName(rs.getString(2));
                pi.setPersonSex(rs.getString(3));
                pi.setPersonPhone(rs.getString(4));
                pi.setPersonAddress(rs.getString(5));
                pi.setPersonEmail(rs.getString(6));
                pi.setHomePhone(rs.getInt(7));
                pi.setPostCode(rs.getInt(8));
                pi.setAdminId(rs.getInt(9));
                list.add(pi);//将其添加到list集合中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //判断是否登陆成功
    public boolean judgeLogin(String adminName,String adminPassWord){
        conn = DBManager.getConnection();
        String sql = "select * from admininfo where adminname = ? and adminpassword = md5(?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,adminName);
            ps.setString(2,adminPassWord);
            rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //根据通讯录adminName得到其对应的adminId值，用于得到对应的人的所有通讯录信息
    public Integer getAdminIdByAdminName(String adminName){
        conn = DBManager.getConnection();
        String sql = "select adminid from admininfo where adminname = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,adminName);
            rs = ps.executeQuery();
            if (rs.next()){
             return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;//表示没有这个名字的人 即admin
    }

    //删除通讯录的某个人
    public int removeOnePerson(Integer personId){
        conn = DBManager.getConnection();
        String sql = "delete from personinfo where personid = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,personId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;//表示删除失败
    }

    //修改某人信息
    public Integer changeSomePerson(PersonInfo pi){
        conn = DBManager.getConnection();
        String sql = "update personinfo set personname = ?,personsex = ?,personphone = ?,personaddress = ?,personemail = ?,homephone = ?,postcode = ? where personid = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,pi.getPersonName());
            ps.setString(2,pi.getPersonSex());
            ps.setString(3,pi.getPersonPhone());
            ps.setString(4,pi.getPersonAddress());
            ps.setString(5,pi.getPersonEmail());
            ps.setInt(6,pi.getHomePhone());
            ps.setInt(7,pi.getPostCode());
            ps.setInt(8,pi.getPersonId());
            return ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    //根据要求进行排序
    public List<PersonInfo> sortPerson(Integer adminId,Object obj){
        conn = DBManager.getConnection();
        String sql = "select * from personinfo where adminId = ? order by "+obj;
        List<PersonInfo> list = new ArrayList<>();
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,adminId);
            rs = ps.executeQuery();
            while (rs.next()){
                PersonInfo pi = new PersonInfo();
                pi.setPersonId(rs.getInt(1));
                pi.setPersonName(rs.getString(2));
                pi.setPersonSex(rs.getString(3));
                pi.setPersonPhone(rs.getString(4));
                pi.setPersonAddress(rs.getString(5));
                pi.setPersonEmail(rs.getString(6));
                pi.setHomePhone(rs.getInt(7));
                pi.setPostCode(rs.getInt(8));
                pi.setAdminId(rs.getInt(9));
                list.add(pi);//将其添加到list集合中
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    //添加用户
    public int addSomePerson(PersonInfo pi){
        conn = DBManager.getConnection();
        String sql = "insert into personinfo values(default,?,?,?,?,?,?,?,?)";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,pi.getPersonName());
            ps.setString(2,pi.getPersonSex());
            ps.setString(3,pi.getPersonPhone());
            ps.setString(4,pi.getPersonAddress());
            ps.setString(5,pi.getPersonEmail());
            ps.setInt(6,pi.getHomePhone());
            ps.setInt(7,pi.getPostCode());
            ps.setInt(8,pi.getAdminId());
            return ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
