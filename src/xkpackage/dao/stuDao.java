package xkpackage.dao;

import com.mysql.cj.util.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xkpackage.entities.student;
import xkpackage.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class stuDao {
    public void add(student s) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "insert into student(stuName,stuNum,stuPwd) values(?,?,?)";
        queryRunner.update(sql,s.getStuName(),s.getStuNum(),s.getStuPwd());

    }
    public void delete(Integer id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "delete from student where stuID = ?";
        queryRunner.update(sql,id);
    }
    public void updateByAdmin(student s) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "update student set stuNum = ?, stuName = ? where stuID = ?";
        queryRunner.update(sql,s.getStuNum(),s.getStuName(),s.getStuID());
    }
    public void updateBySelf(Integer ID, String pwd) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "update student set stuPwd = ? where stuID = ?";
        queryRunner.update(sql,pwd,ID);
    }
    public List<student> list(student s) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from student where 1=1";
        List<Object> limit = new ArrayList<Object>();
        if(s.getStuID() != null)
        {
            sql += " and stuID = ?";
            limit.add(s.getStuID());
        }
        if(!StringUtils.isNullOrEmpty(s.getStuNum()))
        {
            sql += " and stuNum = ?";
            limit.add(s.getStuNum());
        }
        if(!StringUtils.isNullOrEmpty(s.getStuName()))
        {
            sql += " and stuName = ?";
            limit.add(s.getStuName());
        }
        Object[] arr = new Object[limit.size()];
        for(int  i=0;i< limit.size();i++)
        {
            arr[i] = limit.get(i);
        }
        return queryRunner.query(sql,new BeanListHandler<>(student.class),arr);
    }
    public student findByID(Integer id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from student where stuID = ?";
        return queryRunner.query(sql,new BeanHandler<>(student.class),id);
    }
    public student login(String userName,String password) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from student where stuNum = ? and stuPwd = ?";
        return queryRunner.query(sql,new BeanHandler<>(student.class),userName,password);
    }
}
