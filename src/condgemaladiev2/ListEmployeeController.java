package condgemaladiev2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ikerfah
 */
public class ListEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtPosition;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextArea textAreaObs;
    @FXML
    private TextField txtNb;
    @FXML
    private TableView<FullEmployee> tableE;
    @FXML
    private TableColumn<FullEmployee,Integer> id;
    @FXML
    private TableColumn<FullEmployee,String> nom;
    @FXML
    private TableColumn<FullEmployee,String> prenom;
    @FXML
    private TableColumn<FullEmployee,String> position;
    @FXML
    private TableColumn<FullEmployee,String> dateD;
    @FXML
    private TableColumn<FullEmployee,Integer> nb;
    @FXML
    private TableColumn<FullEmployee,String> dateF;
    @FXML
    private TableColumn<FullEmployee,String> obs;
    @FXML
    private TableColumn<FullEmployee,String> conge;
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "عطلة سنوية",
        "عطلة مرضية",
        "عطلة مرضية طويلة المدى",
        "الإذن بالغياب",
        "الإحالة على الإستداع",
        "عطلة الأمومة",
        "عطلة إستثنائية"
    );
    @FXML
    private  ComboBox typeConge = new ComboBox();
     
    public ObservableList<FullEmployee> dataE = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        typeConge.setItems(options);
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from `employee` where 1");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                FullEmployee tmp = new FullEmployee();
                tmp.setId(rs.getInt(1));               
                tmp.setNomFr(rs.getString(2));
                tmp.setPrenomFr(rs.getString(3));               
                tmp.setPoste(rs.getString(5));              
                tmp.setObs(rs.getString(6));
                tmp.setDateDebut((rs.getString(9).equalsIgnoreCase("01-01-1900")) ? "":rs.getString(9));
                tmp.setDateFin((rs.getString(10).equalsIgnoreCase("01-01-1900")) ? "":rs.getString(10));
                tmp.setNbJours(rs.getInt(11));
                tmp.setTypeConge((rs.getString(12).equalsIgnoreCase("non")) ? "":rs.getString(12));
                dataE.add(tmp);
            }
        } catch (SQLException ex) {
            
        }
        
        id.setCellValueFactory(new PropertyValueFactory("id"));
        nom.setCellValueFactory(new PropertyValueFactory("nomFr"));
        prenom.setCellValueFactory(new PropertyValueFactory("prenomFr"));
        position.setCellValueFactory(new PropertyValueFactory("poste"));
        dateD.setCellValueFactory(new PropertyValueFactory("dateDebut"));
        nb.setCellValueFactory(new PropertyValueFactory("nbJours"));       
        dateF.setCellValueFactory(new PropertyValueFactory("dateFin"));
        obs.setCellValueFactory(new PropertyValueFactory("obs"));       
        conge.setCellValueFactory(new PropertyValueFactory("typeConge"));
        tableE.setItems(dataE);    
    }    

    @FXML
    public void tableRowClick()
    {
        try
        {
        txtId.setText(String.valueOf(tableE.getSelectionModel().getSelectedItem().getId()));
        txtNom.setText(tableE.getSelectionModel().getSelectedItem().getNomFr());
        txtPrenom.setText(tableE.getSelectionModel().getSelectedItem().getPrenomFr());
        txtPosition.setText(tableE.getSelectionModel().getSelectedItem().getPoste());
        if(!tableE.getSelectionModel().getSelectedItem().getDateDebut().equalsIgnoreCase(""))
            
            dateDebut.setValue(LocalDate.of(Integer.parseInt(tableE.getSelectionModel().getSelectedItem().getDateDebut().substring(6, 10)), Integer.parseInt(tableE.getSelectionModel().getSelectedItem().getDateDebut().substring(3, 5)), Integer.parseInt(tableE.getSelectionModel().getSelectedItem().getDateDebut().substring(0, 2))));
        if(!tableE.getSelectionModel().getSelectedItem().getDateFin().equals(""))
            
            dateFin.setValue(LocalDate.of(Integer.parseInt(tableE.getSelectionModel().getSelectedItem().getDateFin().substring(6, 10)), Integer.parseInt(tableE.getSelectionModel().getSelectedItem().getDateFin().substring(3, 5)), Integer.parseInt(tableE.getSelectionModel().getSelectedItem().getDateFin().substring(0, 2))));
        textAreaObs.setText(tableE.getSelectionModel().getSelectedItem().getObs());     
        txtNb.setText(String.valueOf(tableE.getSelectionModel().getSelectedItem().getNbJours()));   
        }catch(Exception e)
        {
            
        }
        
    }
    
    
    
    @FXML
    public void modifier()
    {
        if(tableE.getSelectionModel().getSelectedIndex()<0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("pas d'item selectionné");
            alert.show();
        }else
        {
            
            try 
            {
                   String msg = "Remplez les champs suivant pour ajouter : \n";
                   

                   if(txtNom.getText().equals(""))
                       msg+= "- Nom \n";

                   if(txtPrenom.getText().equals(""))
                       msg+= "- Prenom \n";

                   if(txtPosition.getText().equals(""))
                       msg+= "- Poste \n";

                   if(dateDebut.getValue()!=null && dateFin.getValue()!=null)
                   if(dateFin.getValue().compareTo(dateDebut.getValue()) <0)
                       msg+= "- Date de fin doit être > date de début";
                   if(!msg.equals("Remplez les champs suivant pour ajouter : \n"))
                   {
                       Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.setContentText(msg);
                       alert.show();
                   }else
                {
                    Connection con = ConnectDB.getConnection();
                    String d="01-01-1900";
                    String f="01-01-1900";
                    if(dateDebut.getValue() != null)
                        d = dateDebut.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    
                        
                    if(dateFin.getValue() != null)
                        f = dateFin.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    
                    String c;
                    if(typeConge.getSelectionModel().getSelectedItem() != null)
                        c=typeConge.getSelectionModel().getSelectedItem().toString();
                    else
                        c ="non";
                    PreparedStatement ps = con.prepareStatement("UPDATE employee SET `Column_4`='"+txtNom.getText()+"',`Column_5`='"+txtPrenom.getText()+"',`Column_12`='"+txtPosition.getText()+"',`nbJourVac`='"+txtNb.getText()+"',`dateDebutVac`='"+d+"',`dateFinVac`='"+f+"',`obs`='"+textAreaObs.getText()+"' ,`typeConge`='"+c+"' WHERE `Column_1`="+txtId.getText());                    
                    
                    ps.executeUpdate();
                    FullEmployee tmp = new FullEmployee();
                    tmp.setId(Integer.parseInt(txtId.getText()));
                    tmp.setNomFr(txtNom.getText());
                    tmp.setPrenomFr(txtPrenom.getText());
                    tmp.setPoste(txtPosition.getText());
                    tmp.setObs(textAreaObs.getText());
                    
                    if(dateDebut.getValue() != null)
                        tmp.setDateDebut(dateDebut.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    else
                        tmp.setDateDebut("");
                    if(dateFin.getValue() != null)
                         tmp.setDateFin(dateFin.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    else
                        tmp.setDateFin("");
                    tmp.setNbJours(Integer.parseInt(txtNb.getText()));
                    if(typeConge.getSelectionModel().getSelectedIndex()>=0)
                        tmp.setTypeConge(typeConge.getSelectionModel().getSelectedItem().toString());
                    else
                        tmp.setTypeConge("");
                    typeConge.getSelectionModel().clearSelection();                                       
                    dataE.set(tableE.getSelectionModel().getSelectedIndex(), tmp);
                }
            } catch (SQLException ex) 
            { 
                System.out.println(ex.getMessage());
            }
                  
        }
    }
    
    @FXML
    public void supprimer()
    {
        if(tableE.getSelectionModel().getSelectedIndex()<0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("pas d'item selectionné");
            alert.show();
        }else
        {
            
            try {
                Connection con = ConnectDB.getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE `employee` SET `typeConge` = 'non',`nbJourVac`=0,`dateDebutVac`='01-01-1900',`dateFinVac`='01-01-1900' WHERE `Column_1`="+txtId.getText());
                ps.executeUpdate();
                
                FullEmployee tmp = new FullEmployee();
                tmp.setId(Integer.parseInt(txtId.getText()));
                tmp.setNomFr(txtNom.getText());
                tmp.setPrenomFr(txtPrenom.getText());
                tmp.setPoste(txtPosition.getText());
                tmp.setObs(textAreaObs.getText());
                typeConge.getSelectionModel().clearSelection();
                tmp.setTypeConge("");
                txtNb.setText("0");
                tmp.setNbJours(0);
                dateDebut.setValue(null);
                tmp.setDateDebut("");
                dateFin.setValue(null);
                tmp.setDateFin("");
                tmp.setObs(textAreaObs.getText());
                dataE.set(tableE.getSelectionModel().getSelectedIndex(), tmp);
            } catch (SQLException ex) {
               System.out.println(ex.getMessage());
            }
            
            
        }
    }
    
}
