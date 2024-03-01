package xkpackage.dao;

import com.mysql.cj.util.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xkpackage.entities.course;
import xkpackage.entities.student;
import xkpackage.entities.tc;
import xkpackage.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class courseDao {
    public void add(course c) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "insert into course(cID,cName,tID) values(?,?,?)";
        queryRunner.update(sql,c.getcID(),c.getcName(),c.gettID());

    }
    public void delete(Integer id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "delete from course where cID = ?";
        queryRunner.update(sql,id);
    }
    public void updateByAdmin(course c) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "update course set cName = ?,tID = ? where cID = ?";
        queryRunner.update(sql,c.getcName(),c.gettID(),c.getcID());
    }
    public List<course> list(course c) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from course where 1=1";
        List<Object> limit = new ArrayList<Object>();
        if(c.getcID() != null)
        {
            sql += " and cID = ?";
            limit.add(c.getcID());
        }
        if(c.gettID() != null)
        {
            sql += " and tID = ?";
            limit.add(c.gettID());
        }
        if(!StringUtils.isNullOrEmpty(c.getcName()))
        {
            sql += " and cName = ?";
            limit.add(c.getcName());
        }
        Object[] arr = new Object[limit.size()];
        for(int  i=0;i< limit.size();i++)
        {
            arr[i] = limit.get(i);
        }
        return queryRunner.query(sql,new BeanListHandler<>(course.class),arr);
    }
    public course findByID(Integer id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from course where cID = ?";
        return queryRunner.query(sql,new BeanHandler<>(course.class),id);
    }
    public List<tc> listTc(tc tc,Integer stuID) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select cID,cName,tID,tName from course natural join teacher where cID not in (select cID from student natural join take where stuID = ?)";
        List<Object> limit = new ArrayList<Object>();
        if(tc.getcID() != null)
        {
            sql += " and cID = ?";
            limit.add(tc.getcID());
        }
        if(!StringUtils.isNullOrEmpty(tc.getcName()))
        {
            sql += " and cName = ?";
            limit.add(tc.getcName());
        }
        if(!StringUtils.isNullOrEmpty(tc.gettName()))
        {
            sql += " and tName = ?";
            limit.add(tc.gettName());
        }
        Object[] arr = new Object[limit.size()+1];
        arr[0] = stuID;
        for(int  i=0;i< limit.size();i++)
        {
            arr[i+1] = limit.get(i);
        }
        return queryRunner.query(sql,new BeanListHandler<>(tc.class),arr);
    }
}
