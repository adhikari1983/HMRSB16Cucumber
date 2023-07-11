package utils;

public class Constants {
    public static final String EXCEL_READER_PATH =
            System.getProperty("user.dir") + "/src/test/resources/testdata/batch16.xlsx";

    public static final String CONFIG_READER_PATH =
            System.getProperty("user.dir") + "/src/test/resources/config/config.properties";

    public static final String SCREENSHOT_FILEPATH =
            System.getProperty("user.dir") + "/screenshots/";
    // was like this w/o / at the end    System.getProperty("user.dir") + "/screenshots";
    //after we add / & made it like      System.getProperty("user.dir") + "/screenshots/";
    //e.g. 7 test passed, and  7 test failed screenshots are generated accordingly automatically & will be
    //saved after  System.getProperty("user.dir") + "/screenshots/" + test passed screenshots
    //saved after  System.getProperty("user.dir") + "/screenshots/" + test failed screenshots
}
