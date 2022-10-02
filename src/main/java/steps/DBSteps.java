package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import db_utils.DBUtils;
import db_utils.ResultSetHandler;
import domains.Food;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBSteps {


    @Then("^the following food should be commited to DB$")
    public void the_following_food_should_be_commited_to_DB(List<Food> expectedFoodInDB) throws SQLException {

        ResultSetHandler rs = DBUtils.query("SELECT * FROM food;");

        List<Food> actualFoodFromDB = rs.toBeans(Food.class);

        Assert.assertEquals(expectedFoodInDB.size() , actualFoodFromDB.size());

        Assert.assertEquals("Invalid description in DB", expectedFoodInDB.get(0).getDescription(), actualFoodFromDB.get(0).getDescription());
        Assert.assertEquals("invalid type in DB", expectedFoodInDB.get(0).getFood_type(), actualFoodFromDB.get(0).getFood_type());
        Assert.assertEquals(expectedFoodInDB.get(0).getName(), actualFoodFromDB.get(0).getName());
        Assert.assertEquals(expectedFoodInDB.get(0).getImage_url(), actualFoodFromDB.get(0).getImage_url());
        Assert.assertEquals(expectedFoodInDB.get(0).getPrice(), actualFoodFromDB.get(0).getPrice(),0);

        DBUtils.close();

    }


    @Given("^\"([^\"]*)\" table is truncated$")
    public void tableIsTruncated(String table) throws Throwable {

        DBUtils.query("SET FOREIGN_KEY_CHECKS = 0;");
        DBUtils.truncateTable(table);

    }
}
