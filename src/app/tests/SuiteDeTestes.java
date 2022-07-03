package app.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	EntityTest.class,
	UserTest.class,
	ProviderTest.class,
	ProductTest.class,
	ItemTest.class,
	SaleTest.class,
	ClientTest.class,
	UserFacadeTest.class,
	ProviderFacadeTest.class,
	ProductFacadeTest.class,
	MenuFacadeTest.class,
	SaleFacadeTest.class,
	ClientFacadeTest.class,
})
class SuiteDeTestes {
};
