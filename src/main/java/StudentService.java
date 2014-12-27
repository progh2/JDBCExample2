import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by progh2 on 2014-12-27.
 */
public class StudentService {
    public Student findStudentById(int studId){
        Student student = null;
        Connection conn = null;
        try{
            // get connection
            conn = getDatabaseConnection();
            String sql = "select * from students where stud_id=?";

            //create preparedStatement object
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set input parameter
            pstmt.setInt(1, studId);
            ResultSet rs = pstmt.executeQuery();

            // Create java Object from database result
            if(rs.next()){
                student = new Student();
                student.setStudId(rs.getInt("stud_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob"));
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally{
            // close connection;
            if(conn != null){
                try{
                    conn.close();;
                }catch (SQLException e){

                }
            }
        }
        return student;
    }
}
