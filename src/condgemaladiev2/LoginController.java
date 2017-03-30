package condgemaladiev2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;



/**
 * FXML Controller class
 *
 * @author ikerfah
 */
public class LoginController{

    @FXML
    TextField txtUser;
    
    @FXML
    PasswordField txtPass;
    
    @FXML
   public void clickBtnLogIn(ActionEvent e) throws IOException
   {
       
            
        Connection con = ConnectDB.getConnection();
            if(con!=null)
            {
        try {
            
             
            PreparedStatement ps = con.prepareStatement("select * from admin");
            ResultSet rs = ps.executeQuery();
            boolean trouve = false;
            while(rs.next())
            {
                if(rs.getString("user").equalsIgnoreCase(txtUser.getText()) && rs.getString("pass").equals(txtPass.getText()))
                {
                    trouve = true;break;
                }
            }
            if(trouve)
            {
                Window w = txtUser.getScene().getWindow();
                w.hide();
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("ListEmployee.fxml"));
                Scene scene = new Scene(root,700,500);
                primaryStage.setScene(scene);
                primaryStage.setTitle("ListeEmployee");
                primaryStage.show();
                
                
                
            }else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User ou pass error");
                alert.show();
            }
            
           
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.show();
        }
        }else
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error de connection à la base des données");
            alert.show();
         }
   }
   @FXML
   public void clickBtnClear(ActionEvent e)
   {
       txtUser.setText(null);
       txtPass.setText(null);
   }
   
   @FXML
   public void clickConnectBtn(ActionEvent e)
   {
       Connection con = ConnectDB.getConnection();
       
       if(con!=null)
       {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setContentText("Connected!");
                alert.show();
       }else
       {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText("not connected");
                alert.show();
       }
                
       
       
   }
    
}
