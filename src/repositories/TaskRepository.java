package repositories;

import dtos.TaskDTO;
import enums.TaskStatus;
import utils.DatabaceUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TaskRepository {
    public static boolean create(TaskDTO taskDTO) {

        try {
            Connection con = DatabaceUtil.getConnection();
            Statement statement = con.createStatement();
            String sql = "insert into task (title,content,status,created_date) values('%s','%s','%s','%s')";
            sql = String.format(sql, taskDTO.getTitle(), taskDTO.getContent(), taskDTO.getStatus().name(),
                    taskDTO.getCreated_date().toString());
            int effectedRows = statement.executeUpdate(sql); // <4>
            con.close();
            return effectedRows == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<TaskDTO> getAll(TaskStatus status) {
        List<TaskDTO> list = new LinkedList<>();
        try {
            Connection con = DatabaceUtil.getConnection();
            Statement statement = con.createStatement();
            String sql = "select * from task where status = '" + status.name() + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                TaskDTO dto = new TaskDTO();
                dto.setId(resultSet.getInt("id"));
                dto.setTitle(resultSet.getString("title"));
                dto.setContent(resultSet.getString("content"));
                dto.setStatus(TaskStatus.valueOf(resultSet.getString("status")));
                dto.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                Timestamp timestamp = resultSet.getTimestamp("finished_date");
                if (timestamp != null) {
                    dto.setFinished_date(timestamp.toLocalDateTime());
                }
                list.add(dto);
            }   con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public boolean markAsDone(int id) {
        try {
            Connection con = DatabaceUtil.getConnection();
            Statement statement = con.createStatement(); // <3>
            String sql = "update task set status='DONE', finished_date=now() where id = " + id;
            int effectedRows = statement.executeUpdate(sql); // <4>
            con.close();
            return effectedRows == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<TaskDTO> search(String title){
        List<TaskDTO> dtoList = new LinkedList<>();
        Connection con = DatabaceUtil.getConnection();
        try {
            Statement statement = con.createStatement();
            String sql = "select * from task where lower(title) like '%"+title+"%'";
            ResultSet res = statement.executeQuery(sql);
            while (res.next()){
                TaskDTO dto = new TaskDTO();
                dto.setId(res.getInt("id"));
                dto.setTitle(res.getString("title"));
                dto.setContent(res.getString("content"));
                dto.setStatus(TaskStatus.valueOf(res.getString("status")));
                Timestamp timestamp = res.getTimestamp("finished_date");
                if (timestamp!=null){
                    dto.setFinished_date(timestamp.toLocalDateTime());
                }
                dtoList.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dtoList;
    }
    public static boolean delete(Integer id){
        Connection con = DatabaceUtil.getConnection();
        try {
            Statement statement = con.createStatement();
            String sql = "delete from task where id = " + id;
            int effectedRows = statement.executeUpdate(sql);
            con.close();
            return effectedRows !=0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean mark(Integer id){
        Connection con = DatabaceUtil.getConnection();
        try {
            Statement statement = con.createStatement();
            String sql = "update task set status='DONE, finished_date = now() where id = "+ id;
            int effectedRows = statement.executeUpdate(sql);
            con.close();
            return effectedRows == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Boolean update(TaskDTO taskDTO, int id) {

        boolean t = false;
        try {
            Connection connection = DatabaceUtil.getConnection();
            Statement statement  = connection.createStatement();
            String sql = "update  Task set title = '%s',content = '%s' where id = %d ";
            sql = String.format(sql,taskDTO.getTitle(),taskDTO.getContent(),id);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
