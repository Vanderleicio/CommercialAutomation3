module CommercialAutomation3 {
	exports app.model.models;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	
	opens app.controllers to javafx.graphics, javafx.fxml;
	exports app;
}