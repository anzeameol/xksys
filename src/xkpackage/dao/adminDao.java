package xkpackage.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xkpackage.entities.admin;
import xkpackage.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class adminDao {
    public void add(admin adm) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "insert into admin(adminName,adminAcc,adminPwd) values(?,?,?)";
        queryRunner.update(sql,adm.getAdminName(),adm.getAdminAcc(),adm.getAdminPwd());

    }
    public void delete(Integer id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "delete from admin where adminID = ?";
        queryRunner.update(sql,id);
    }
    public void updateBySelf(Integer ID, String pwd) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "update admin set adminPwd = ? where adminID = ?";
        queryRunner.update(sql,pwd,ID);
    }
    public List<admin> list(admin adm) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from admin";
        List<admin> list = queryRunner.query(sql,new BeanListHandler<>(admin.class));
        return list;
    }
    public admin findByID(Integer id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from admin where adminID = ?";
        admin adm = queryRunner.query(sql,new BeanHandler<>(admin.class),id);
        return adm;
    }
    public admin login(String userName,String password) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from admin where adminAcc = ? and adminPwd = ?";
        admin re = queryRunner.query(sql,new BeanHandler<>(admin.class),userName,password);
        return re;
    }

}
