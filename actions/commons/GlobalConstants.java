package commons;

public class GlobalConstants {
    
//SERVER URL: Dev/ testing/ stg/ prod
//Database: dev/ test/ stg/ pro
//timeout: short/ long
//username, pass
//third party : Sandbox Paypal
//donwnload, upload folder
//os name
//relative prj path
//cloud testing: browserstack, saucelab, crossbrowsertesting (access token / id)

    public static final long LONG_TIMEOUT = 30;
    public static final long SHORT_TIMEOUT = 5;
    public static final String DEV_ADMIN_URL = "https://admin-demo.nopcommerce.com/";
    public static final String DEV_USER_URL = "https://demo.nopcommerce.com/";
    public static final String DEV_ADMIN_USERNAME = "admin@yourstore.com";
    public static final String DEV_ADMIN_PASSWORD = "admin";
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
    public static final String UPLOAD_FILE_PATH = RELATIVE_PROJECT_PATH + "/uploadFiles/";
    public static final String DOWNLOAD_FILE_PATH = RELATIVE_PROJECT_PATH + "/downloadFiles/";
}
