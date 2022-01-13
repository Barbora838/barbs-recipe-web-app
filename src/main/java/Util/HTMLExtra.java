package Util;

import Server.HTTPConstants;

import java.util.ArrayList;

/**
 * HTMLExtra Class
 */
public class HTMLExtra {

    /**
     * Helper method that returns modified titled header.
     *
     * @param name
     * @return
     */
    public static String returnModifiedHeader(String name) {
        return "\n<!DOCTYPE html>\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "  <title>" + name + "</title>\n" +
                "</head>\n" + "\n" +
                "<body>\n"
                + "\n";
    }


    /**
     * Helper Method that returns html with not found recipe.
     *
     * @param bodyValue
     * @return
     */
    public static String notFoundRecipe(String bodyValue) {

        return returnModifiedHeader("NotFoundRecipe") +
                returnColorBackground("ffcccc") +
                "<h1> Recipe " + bodyValue + " was not found</h1>\n" +
                HTMLButtons.HOME_BUTTON +
                HTTPConstants.PAGE_FOOTER;
    }

    /**
     * Helper method that returns the REcipe page.
     *
     * @param result
     * @return
     */
    public static String getRecipePage(ArrayList<String> result) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<title>W3.CSS Template</title>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html\"></meta>" +
                "<link href=\"https://fonts.googleapis.com/css?family=Assistant:300&amp;subset=all\" rel=\"stylesheet\"></link>" +
                "<style>\n" +
                "body {\n" +
                "font-family: \"Times New Roman\", Georgia, Serif;\n" +
                "  font-family: \"Playfair Display\";\n" +
                "  letter-spacing: 5px;\n" +
                "  background-color: #ffcccc;\n" +
                "  font-family: 'Ubuntu', sans-serif;\n" +
                "  vertical-align: middle;" +
                "}\n" +
                "</style>\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "<div class=\"w3-content\" style=\"max-width:1100px\"accept-charset=\"utf-8\">\n" +
                "    <hr>\n" +
                "\n" +
                "    <div class=\"w3-row w3-padding-64\" id=\"menu\">\n" +
                "        <div class=\"w3-col l6 w3-padding-large\">\n" +
                "            <h1 class=\"w3-center\">Barb's Recipe App <img src=\"/files/images/chefBarb.jpg\" class=\"w3-round w3-image w3-opacity-min\" alt=\"chefBarb\" width=\"100\" height=\"120\" style=\"vertical-align:middle\"></img></h1><br/>\n" +
                "            <h4><li>Title</li></h4>\n" +
                "            <p class=\"w3-text-grey\"><h1>" + result.get(0) + "</h1></p><br/>\n" +
                "\n" +
                "            <h4><li>Ingredients</li></h4>\n" +
                "            <p class=\"w3-text-grey\">" + result.get(1) + "</p><br/>\n" +
                "\n" +
                "            <h4><li>Instructions</li></h4>\n" +
                "            <p class=\"w3-text-grey\">" + result.get(2) + "</p><br/>\n" +
                "\n" +
                "           <h4><li>Picture</li></h4>\n" +
                "           <p>\n" +
                "              <img id='img' src=" + result.get(3) + "></img>\n" +
                "           </p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    </hr>\n" +
                "</div>\n" +
                HTMLButtons.HOME_BUTTON +
                HTTPConstants.PAGE_FOOTER;
    }

    public static String getEncodedTitle(ArrayList<String> result) {
        String res = "https://assets.bonappetit.com/photos/57afb79853e63daf11a4e61b/master/w_1280,c_limit/";
        String[] split = result.get(0).split(" ");
        for (int i = 0; i < split.length; i++) {
            res += split[i] + "-";

        }
        res += "646.jpg";
        System.out.println("getEncodedTitle: " + res);
        return res;

    }

    /**
     * Helper method that returns Homepage html.
     *
     * @param username
     * @return
     */
    public static String getHomepage(String username) {

        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<title>W3.CSS Template</title>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"></meta>\n" +
                "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\"></link>\n" +
                "<style>\n" +
                "body {\n" +
                "font-family: \"Times New Roman\", Georgia, Serif;}\n" +
                "h1, h2, h3, h4, h5, h6 {\n" +
                "  font-family: \"Playfair Display\";\n" +
                "  letter-spacing: 4px;\n" +
                "}\n" +
                ".w3-button {width:150px}\n" +
                "</style>\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "<div id=\"page-wrap\">\n" +
                "<header class=\"w3-display-container w3-content w3-wide\" style=\"max-width:1600px;min-width:500px\" id=\"home\">\n" +
                "    <img src=\"/files/images/homepage.jpg\" class=\"center\" alt=\"HomepagePicture\" width=\"600\" height=\"300\"></img>\n" +
                "   <h3 class=\"w3-center\">Welcome back " + username + "!</h3><br/>\n" +
                "</header>\n" +
                "\n" +
                "<div class=\"w3-content\" style=\"max-width:1100px\">\n" +
                "\n" +
                "    <div class=\"w3-row w3-padding-64\" id=\"about\">\n" +
                "        <div class=\"w3-col m6 w3-padding-large w3-hide-small\">\n" +
                "            <img src=\"/files/images/chefBarb.jpg\" class=\"center\" alt=\"ChefBarb\" width=\"200\" height=\"250\"></img>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"w3-col m6 w3-padding-large\">\n" +
                "            <h1 class=\"w3-center\">About Barb's Recipe App</h1><br/>\n" +
                "            <h5 class=\"w3-center\"><span class=\"w3-tag w3-light-grey\">Tradition since 2021</span></h5>\n" +
                "\n" +
                "            <p class=\"w3-large\"><img src=\"/files/images/usFlag.jpg\" class=\"w3-round w3-image w3-opacity-min\" alt=\"usFlag\" width=\"30\" height=\"30\" style=\"vertical-align:middle\"> The App was founded in 2021 by Miss Barbora as an idea for her Side Project at University of San Francisco.</img></p>\n" +
                "            <p class=\"w3-large\"><img src=\"/files/images/czechFlag.jpg\" class=\"w3-round w3-image w3-opacity-min\" alt=\"czechFlag\" width=\"30\" height=\"30\" style=\"vertical-align:middle\"> Aplikace byla zalozena v roce 2021 slecnou Barborou jako napad pro jeji vedlejsi projekt na Universite v San Francisku.</img></p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <br/>\n" +

                "    <div class=\"w3-col m6 w3-padding-large\">\n" +
                "        <h1 class=\"w3-center\">Features:</h1>\n" +
                "    </div>\n" +
                "    <hr>\n" +

                HTMLButtons.FIND_BUTTON +
                HTMLButtons.SEARCH_BUTTON +
                HTMLButtons.LOGOUT_BUTTON +

                "    </hr><br/><br/>\n" +
                "    <div class=\"w3-container w3-padding-64\" id=\"contact\">\n" +
                "        <h1>Follow Us</h1><br/>\n" +
                "\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"></meta>\n" +
                "        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"></link>\n" +
                "</div>\n" +
                "</div>\n" +
                "<style>\n" +
                ".fa {\n" +
                "  padding: 20px;\n" +
                "  font-size: 30px;\n" +
                "  width: 50px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  margin: 5px 2px;\n" +
                "}\n" +
                "\n" +
                ".fa:hover {\n" +
                "    opacity: 0.7;\n" +
                "}\n" +
                "\n" +
                ".fa-facebook {\n" +
                "  background: #3B5998;\n" +
                "  color: white;\n" +
                "}\n" +
                "\n" +
                ".fa-twitter {\n" +
                "  background: #55ACEE;\n" +
                "  color: white;\n" +
                "}\n" +
                "\n" +
                ".fa-google {\n" +
                "  background: #dd4b39;\n" +
                "  color: white;\n" +
                "}\n" +
                "\n" +
                ".fa-linkedin {\n" +
                "  background: #007bb5;\n" +
                "  color: white;\n" +
                "}\n" +
                "\n" +
                ".fa-youtube {\n" +
                "  background: #bb0000;\n" +
                "  color: white;\n" +
                "}\n" +
                "\n" +
                ".fa-instagram {\n" +
                "  background: #125688;\n" +
                "  color: white;\n" +
                "}\n" +
                "\n" +
                ".center {\n" +
                "    display: block;\n" +
                "    margin-left: auto;\n" +
                "    margin-right: auto;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "</style>\n" +
                "    <div>\n" +
                "        <a href=\"http://localhost:1024/commingSoon\" class=\"fa fa-facebook\"></a>\n" +
                "        <a href=\"http://localhost:1024/commingSoon\" class=\"fa fa-twitter\"></a>\n" +
                "        <a href=\"http://localhost:1024/commingSoon\" class=\"fa fa-google\"></a>\n" +
                "        <a href=\"http://localhost:1024/commingSoon\" class=\"fa fa-linkedin\"></a>\n" +
                "        <a href=\"http://localhost:1024/commingSoon\" class=\"fa fa-youtube\"></a>\n" +
                "        <a href=\"http://localhost:1024/commingSoon\" class=\"fa fa-instagram\"></a>\n" +
                "    </div>\n" +
                "\n" +

                "<footer class=\"w3-center w3-light-grey w3-padding-32\">\n" +
                "    <p>Powered by <a href=\"https://www.w3schools.com/w3css/default.asp\" title=\"W3.CSS\" target=\"_blank\" class=\"w3-hover-text-green\">w3.css</a></p>\n" +
                "</footer>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

    }


    // code from: https://speckyboy.com/login-pages-html5-css/
    public static final String LOGIN_HTML = "<html>\n" +
            "\n" +
            "<head>\n" +
            "  <link rel=\"stylesheet\" href=\"css/style.css\"></link>\n" +
            "  <link href=\"https://fonts.googleapis.com/css?family=Ubuntu\" rel=\"stylesheet\"></link>\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
            "  <link rel=\"stylesheet\" href=\"path/to/font-awesome/css/font-awesome.min.css\"></link>\n" +
            "  <title>Welcome to Barb's Recipe App</title>\n" +
            "  <p class=\"welcome\" align=\"center\">Welcome to Barb's Recipe App</p>" +
            "<img src=\"/files/images/chefBarb.jpg\"  class=\"center\" width=\"120\" height=\"150\"></img>" +
            "</head>\n" +
            "\n" +
            "<style>\n" +
            ".center {\n" +
            "  display: block;\n" +
            "  margin-left: auto;\n" +
            "  margin-right: auto;\n" +
            "}" +

            "body {\n" +
            "        background-color: #F5A8C5;\n" +
            "        font-family: 'Ubuntu', sans-serif;\n" +
            "    }\n" +
            "    \n" +
            "    .main {\n" +
            "        background-color: #FCECF2;\n" +
            "        width: 400px;\n" +
            "        height: 400px;\n" +
            "        margin: 7em auto;\n" +
            "        border-radius: 1.5em;\n" +
            "        box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);\n" +
            "    }\n" +
            "    \n" +
            "    .login {\n" +
            "        padding-top: 40px;\n" +
            "        color: #B80347;\n" +
            "        font-family: 'Ubuntu', sans-serif;\n" +
            "        font-weight: bold;\n" +
            "        font-size: 23px;\n" +
            "    }\n" +
            "    .welcome {\n" +
            "        padding-top: 60px;\n" +
            "        color: #A3033F;\n" +
            "        font-family: 'Ubuntu', sans-serif;\n" +
            "        font-weight: bold;\n" +
            "        font-size: 40px;\n" +
            "    }\n" +
            "    \n" +
            "    .un {\n" +
            "    width: 76%;\n" +
            "    color: rgb(38, 50, 56);\n" +
            "    font-weight: 700;\n" +
            "    font-size: 14px;\n" +
            "    letter-spacing: 1px;\n" +
            "    background: rgba(136, 126, 126, 0.04);\n" +
            "    padding: 10px 20px;\n" +
            "    border: none;\n" +
            "    border-radius: 20px;\n" +
            "    outline: none;\n" +
            "    box-sizing: border-box;\n" +
            "    border: 2px solid rgba(0, 0, 0, 0.02);\n" +
            "    margin-bottom: 50px;\n" +
            "    margin-left: 46px;\n" +
            "    text-align: center;\n" +
            "    margin-bottom: 27px;\n" +
            "    font-family: 'Ubuntu', sans-serif;\n" +
            "    }\n" +
            "    \n" +
            "    form.form1 {\n" +
            "        padding-top: 40px;\n" +
            "    }\n" +
            "    \n" +
            "    .pass {\n" +
            "            width: 76%;\n" +
            "    color: rgb(38, 50, 56);\n" +
            "    font-weight: 700;\n" +
            "    font-size: 14px;\n" +
            "    letter-spacing: 1px;\n" +
            "    background: rgba(136, 126, 126, 0.04);\n" +
            "    padding: 10px 20px;\n" +
            "    border: none;\n" +
            "    border-radius: 20px;\n" +
            "    outline: none;\n" +
            "    box-sizing: border-box;\n" +
            "    border: 2px solid rgba(0, 0, 0, 0.02);\n" +
            "    margin-bottom: 50px;\n" +
            "    margin-left: 46px;\n" +
            "    text-align: center;\n" +
            "    margin-bottom: 27px;\n" +
            "    font-family: 'Ubuntu', sans-serif;\n" +
            "    }\n" +
            "    \n" +
            "   \n" +
            "    .un:focus, .pass:focus {\n" +
            "        border: 2px solid rgba(0, 0, 0, 0.18) !important;\n" +
            "        \n" +
            "    }\n" +
            "    \n" +
            "    .submit {\n" +
            "      cursor: pointer;\n" +
            "        border-radius: 5em;\n" +
            "        color: #fff;\n" +
            "        background: linear-gradient(to right, #F62875, #F8357E);\n" +
            "        border: 0;\n" +
            "        padding-left: 40px;\n" +
            "        padding-right: 40px;\n" +
            "        padding-bottom: 10px;\n" +
            "        padding-top: 10px;\n" +
            "        font-family: 'Ubuntu', sans-serif;\n" +
            "        margin-left: 35%;\n" +
            "        font-size: 13px;\n" +
            "        box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);\n" +
            "    }\n" +
            "    \n" +
            "    .forgot {\n" +
            "        text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);\n" +
            "        color: #BB0147;\n" +
            "        padding-top: 15px;\n" +
            "    }\n" +
            "    \n" +
            "    a {\n" +
            "        text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);\n" +
            "        color: #BB0147;\n" +
            "        text-decoration: none\n" +
            "    }\n" +
            "    \n" +
            "    @media (max-width: 600px) {\n" +
            "        .main {\n" +
            "            border-radius: 0px;\n" +
            "        }" +
            "</style>\n"
            +
            "<body>\n" +
            "  <div class=\"main\">\n" +
            "      <h1>Login</h1>\n" +
            "      <form action=\"/login\" method=\"post\">\n" +
            "         <label for=\"username\">Username:</label><br/>\n" +
            "         <input type=\"text\" id=\"username\" name=\"username\"/><br/>\n" +
            "         <label for=\"password\">Password:</label><br/>\n" +
            "         <input type=\"text\" id=\"password\" name=\"password\"/><br/><br/>\n" +
            "         <input type=\"submit\" value=\"Submit\"/>\n" +
            "      </form>" +
            "</div>" +
            "</body>\n" +
            "\n" +
            "</html>\n" +
            "\n" +
            "\n" +
            "\n";


    /**
     * helper method that returns header for the html.
     *
     * @param color
     * @return
     */
    // code from: https://codepen.io/tystrong/pen/EqrCg
    public static String returnHeader(String color) {
        return "\n<!DOCTYPE html>\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "  <title>Title</title>\n" +
                "</head>\n" + "\n" +
                "<style>\n" +
                "body {font-family: \"Times New Roman\", Georgia, Serif;}\n" +
                "h1, h2, h3, h4, h5, h6 {\n" +
                "  font-family: \"Playfair Display\";\n" +
                "  letter-spacing: 4px;\n" +
                "}\n" +
                "body {\n" +
                "  display: flex;\n" +
                "  min-height: 500px;\n" +
                "  justify-content: center;\n" +
                "  align-items: center;\n" +
                "  background: " + color + ";\n" +
                "}\n" +
                "input[type=\"submit\"] {\n" +
                "  display: block;\n" +
                "  font-family: Verdana,\"Rockwell\",Helvetica,Serif;\n" +
                "  font-size: 20px;\n" +
                "  width: 120px;\n" +
                "  height: 120px;\n" +
                "  background: #fff;\n" +
                "\tpadding: 10px;\n" +
                "\tborder: 0;\n" +
                "\toutline: 0;\n" +
                "\tborder-radius: 50%;\n" +
                "  color: #171717;\n" +
                "  transition: all 200ms;\n" +
                "  cursor: pointer;\n" +
                "}\n" +
                "input[type=\"submit\"]:focus {\n" +
                "  background: transparent;\n" +
                "  color: transparent;\n" +
                "  border: none;\n" +
                "  border-top: solid 3px #f8a;\n" +
                "  border-left: solid 3px #fff;\n" +
                "  animation: spin 700ms linear infinite;\n" +
                "}\n" +
                "@keyframes spin {\n" +
                "  to {transform: rotate(360deg);}\n" +
                "}" +
                "</style>" +
                "<body>\n"
                + "\n";
    }

    /**
     * Helper method that adds picture int Html Page.
     *
     * @param name
     * @param width
     * @param height
     * @return
     */
    public static String addingPicture(String name, int width, int height) {
        return "<header class=\"w3-display-container w3-content w3-wide\" style=\"max-width:1600px;min-width:500px\" id=\"home\">\n" +
                "    <div><img src=\"/files/images/" + name + "\" width=\"" + width + "\" height=\"" + height + "\"></img></div>\n" +
                "</header>";
    }

    /**
     * Returns comming soon HTML.
     *
     * @param name
     * @return
     */
    public static String getCommingSoon(String name) {
        return returnModifiedHeader("CommingSoon") +
                returnColorBackground("ffcccc") +
                addingPicture("commingsoon.jpg", 700, 450) +
                ("<h2> Stay tuned <b>" + name + "</b> !</h2><br/>\n") +
                HTMLButtons.HOME_BUTTON +
                HTTPConstants.PAGE_FOOTER;

    }

    /**
     * Returns HTML for landing page
     *
     * @return
     */
    public static String getLandingPage() {
        return "<html>\n" +
                "\n" +
                "<head>\n" +
                "  <link rel=\"stylesheet\" href=\"css/style.css\"></link>\n" +
                "  <link href=\"https://fonts.googleapis.com/css?family=Ubuntu\" rel=\"stylesheet\"></link>\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "  <link rel=\"stylesheet\" href=\"path/to/font-awesome/css/font-awesome.min.css\"></link>\n" +
                "  <title>Welcome to Barb's Recipe App</title>\n" +
                "  <p class=\"welcome\" align=\"center\">Welcome to Barb's Recipe App</p>" +
                "  <img src=\"/files/images/chefBarb.jpg\"  class=\"center\" width=\"120\" height=\"150\"></img>" +
                "</head>\n" +
                "\n" +
                "<style>\n" +
                ".center {\n" +
                "  display: block;\n" +
                "  margin-left: auto;\n" +
                "  margin-right: auto;\n" +
                "}\n" +
                "button{\n" +
                "    margin-left and margin-right + width = 100%*/\n" +
                "    width:50%;\n" +
                "    margin-left:25%;\n" +
                "    margin-right:50%;\n" +
                "}\n" +

                "body {\n" +
                "        background-color: #e8c8f8;\n" +
                "        font-family: 'Ubuntu', sans-serif;\n" +
                "        vertical-align: middle;" +
                "    }\n" +
                "    .welcome {\n" +
                "        padding-top: 80px;\n" +
                "        color: #5e048c;\n" +
                "        font-family: 'Ubuntu', sans-serif;\n" +
                "        font-weight: bold;\n" +
                "        font-size: 40px;\n" +
                "    }\n" +
                "    \n" +
                "    form.form1 {\n" +
                "        padding-top: 20px;\n" +
                "    }\n" +
                "</style>\n"
                +
                "<body>\n" +
                "<p style=\"padding: 20px; border: 3px solid purple;\">One of the simplest ways to improve your health is to prepare more meals at home. Sometimes everyone of us needs last-minute meal inspiration?</p>" +
                "<p style=\"padding: 20px; border: 3px solid purple;\"><b>Barb's Recipe App got your back.</b> From easy family dinner ideas to vegetarian recipes and beyond, every one of these dishes can be on the table in 30 minutes or less.</p>" +
                "  <div class=\"main\">\n" +
                HTMLButtons.LOGIN_BUTTON + "<br/>\n" +
                "   </div>\n" +
                HTMLExtra.addingPicture("landingPage.jpg", 700, 400) +
                HTTPConstants.PAGE_FOOTER;
    }

    /**
     * Helper method that returns background color in HTML format.
     *
     * @param color
     * @return
     */
    public static String returnColorBackground(String color) {
        return "<style>\n" +
                "body {\n" +
                "        background-color: #" + color + ";\n" +
                "        font-family: 'Ubuntu', sans-serif;\n" +
                "        vertical-align: middle;" +
                "    }\n" +
                "</style>";
    }

}
