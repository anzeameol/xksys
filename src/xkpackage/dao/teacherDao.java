package xkpackage.dao;

import com.mysql.cj.util.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xkpackage.entities.teacher;
import xkpackage.entities.sc;
import xkpackage.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class teacherDao {
    public void add(teacher t) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "insert into teacher(tName,tNum,tPwd) values(?,?,?)";
        queryRunner.update(sql,t.gettName(),t.gettNum(),t.gettPwd());

    }
    public void delete(Integer id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "delete from teacher where tID = ?";
        queryRunner.update(sql,id);
    }
    public void updateByAdmin(teacher t) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "update teacher set tName = ?,tNum = ? where tID = ?";
        queryRunner.update(sql,t.gettName(),t.gettNum(),t.gettID());
    }
    public void updateBySelf(Integer ID, String pwd) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "update teacher set tPwd = ? where tID = ?";
        queryRunner.update(sql,pwd,ID);
    }
    public List<teacher> list(teacher t) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from teacher where 1=1";
        List<Object> limit = new ArrayList<Object>();
        if(t.gettID() != null)
        {
            sql += " and tID = ?";
            limit.add(t.gettID());
        }
        if(!StringUtils.isNullOrEmpty(t.gettNum()))
        {
            sql += " and tNum = ?";
            limit.add(t.gettNum());
        }
        if(!StringUtils.isNullOrEmpty(t.gettName()))
        {
            sql += " and tName = ?";
            limit.add(t.gettName());
        }
        Object[] arr = new Object[limit.size()];
        for(int  i=0;i< limit.size();i++)
        {
            arr[i] = limit.get(i);
        }
        return queryRunner.query(sql,new BeanListHandler<>(teacher.class),arr);
    }
    public teacher findByID(Integer id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from teacher where tID = ?";
        return queryRunner.query(sql,new BeanHandler<>(teacher.class),id);
    }
    public teacher login(String userName,String password) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from teacher where tNum = ? and tPwd = ?";
        return queryRunner.query(sql,new BeanHandler<>(teacher.class),userName,password);
    }
    public List<sc> grade(Integer id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select tID,tName,stuID,stuName,takeID,grade,cID,cName from student natural join take natural join course natural join teacher where tID = ?";
        return queryRunner.query(sql, new BeanListHandler<>(sc.class), id);
    }
}
