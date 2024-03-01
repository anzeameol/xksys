package xkpackage.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xkpackage.entities.sc;
import xkpackage.entities.take;
import xkpackage.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class takeDao {
    public void add(take t) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "insert into take(stuID,cID) values(?,?)";
        queryRunner.update(sql,t.getStuID(),t.getcID());
    }
    public void delete(Integer stuID,Integer cID) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "delete from take where stuID = ? and cID = ?";
        queryRunner.update(sql,stuID,cID);
    }
    public void update(take t) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "update take set grade = ? where takeID = ?";
        queryRunner.update(sql,t.getGrade(),t.getTakeID());
    }
    public List<take> list(take t) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from take";
        List<take> list = queryRunner.query(sql,new BeanListHandler<>(take.class));
        return list;
    }
    public take findByID(Integer id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select * from take where takeID = ?";
        return queryRunner.query(sql,new BeanHandler<>(take.class),id);
    }
    public List<sc> listScSelected(Integer id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select tID,tName,stuID,stuName,takeID,grade,cID,cName from student natural join take natural join course natural join teacher where stuID = ? and grade is null";
        return queryRunner.query(sql, new BeanListHandler<>(sc.class), id);
    }
    public List<sc> listScHistory(Integer id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSourse());
        String sql = "select tID,tName,stuID,stuName,takeID,grade,cID,cName from student natural join take natural join course natural join teacher where stuID = ? and grade is not null";
        return queryRunner.query(sql, new BeanListHandler<>(sc.class), id);
    }
}
