package Util;

/**
 * HTMLButtons Class
 */
public class HTMLButtons {

    public static final String HOME_BUTTON =
            "<div>\n" +
                    "        <br/><br/><br/><br/>\n" +
                    "        <h3 class=\"w3-center\">Click here if you want to go back to you Homepage</h3><br/>\n" +
                    "        <button onclick=\"home()\"><img id=\"myImg\" src=\"https://i.pinimg.com/564x/28/da/5a/28da5aa7f2e38b6428d584b37ddf237b.jpg\" width=\"100\" height=\"100\"></img></button>\n" +
                    "\n" +
                    "        <script>\n" +
                    "        function home() {\n" +
                    "            location.replace(\"http://localhost:1024/home\")\n" +
                    "        }\n" +
                    "        </script>\n" +
                    "</div>\n";


    public static final String LOGIN_BUTTON =
            "<div>" +
                    "<br/>" +
                    "        <h3 class=\"w3-center\">Click here if you want to Login</h3><br/>\n" +
                    "        <button onclick=\"login()\"><img id=\"myImg\" src=\"http://www.clker.com/cliparts/T/k/O/v/f/t/pink-plus-login-button-md.png\" width=\"120\" height=\"100\"></img></button>\n" +
                    "\n" +
                    "        <script>\n" +
                    "        function login() {\n" +
                    "            location.replace(\"http://localhost:1024/login\")\n" +
                    "        }\n" +
                    "        </script>" +
                    "</div>";

    public static final String FIND_BUTTON =
            "        <h3 class=\"w3-center\">Click here if you have specific Recipe in your mind</h3><br/>\n" +
                    "        <button onclick=\"find()\">Find</button>\n" +
                    "        </hr><hr>\n" +
                    "        <script>\n" +
                    "        function find() {\n" +
                    "            location.replace(\"http://localhost:1024/find\")\n" +
                    "        }\n" +
                    "        </script>\n";

    public static final String SEARCH_BUTTON =
            "        <h3 class=\"w3-center\">Click here if you have no particular Recipe in your mind</h3><br/>\n" +
                    "        <button onclick=\"search()\">Search</button>\n" +
                    "        </hr><hr>\n" +
                    "        <script>\n" +
                    "        function search() {\n" +
                    "            location.replace(\"http://localhost:1024/search\")\n" +
                    "        }\n" +
                    "        </script>\n";

    public static final String LOGOUT_BUTTON =
            "        <h3 class=\"w3-center\">Click here if you want Logout</h3><br/>\n" +
                    "        <button onclick=\"logout()\"><img id=\"myImg\" src=\"https://previews.123rf.com/images/faysalfarhan/faysalfarhan1710/faysalfarhan171015411/89009698-logout-isolated-on-special-orange-round-button-abstract-illustration.jpg\" width=\"100\" height=\"100\"></img></button>\n" +
                    "        </hr><hr>\n" +
                    "        <script>\n" +
                    "        function logout() {\n" +
                    "            location.replace(\"http://localhost:1024/logout\")\n" +
                    "        }\n" +
                    "        </script>\n";

}
