module CommercialAutomation3 {
	exports app.model.models;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires itextpdf;
	requires java.desktop;
	requires org.junit.jupiter.api;
	requires org.junit.platform.suite.api;
	
	opens app.controllers to javafx.graphics, javafx.fxml;
	opens app.controllers.providerControllers to javafx.graphics, javafx.fxml;
	opens app.controllers.userControllers to javafx.graphics, javafx.fxml;
	opens app.controllers.productControllers to javafx.graphics, javafx.fxml;
	opens app.controllers.menuControllers to javafx.graphics, javafx.fxml;
	opens app.controllers.reportControllers to javafx.graphics, javafx.fxml;
	opens app.controllers.clientControllers to javafx.graphics, javafx.fxml;
	opens app.controllers.saleControllers to javafx.graphics, javafx.fxml;
	exports app;
}