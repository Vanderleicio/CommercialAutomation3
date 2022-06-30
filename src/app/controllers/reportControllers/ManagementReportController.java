package app.controllers.reportControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.ProductFacade;
import app.model.facades.ProviderFacade;
import app.model.reports.ManagementReportProvider;

public class ManagementReportController {

    @FXML
    private Button buttonAddUser;

    @FXML
    private Button buttonEditUser;

    @FXML
    private Button buttonRemoveUser;
    
    static ProviderFacade pf;
    static ProductFacade prf;
    
    @FXML
    void generateReportProvider(MouseEvent event) throws IdDoesntExist, EntitiesNotRegistred {
    	ManagementReportProvider managP = new ManagementReportProvider();
    	managP.generatePDF(pf, prf);
    }

    @FXML
    void generateReportSales(MouseEvent event) {

    }

    @FXML
    void generateReportStock(MouseEvent event) {

    }

}

