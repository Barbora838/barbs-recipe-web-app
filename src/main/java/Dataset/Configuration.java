package Dataset;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Configuration Class
 */
public class Configuration {

    private String url;
    private String root;
    private String password;


    /**
     * Constructor
     *
     * @param configFile
     */
    public Configuration(String configFile) {
        readFile(configFile);
    }

    /**
     * Return Url.
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set Url.
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Return root.
     *
     * @return
     */
    public String getRoot() {
        return root;
    }

    /**
     * Set root.
     *
     * @param root
     */
    public void setRoot(String root) {
        this.root = root;
    }

    /**
     * Return password.
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Reads fine and set Token and channel properly
     *
     * @param configFile
     */
    public void readFile(String configFile) {
        String dir = System.getProperty("user.dir");
        Path path;

        path = Paths.get(dir + "/" + configFile);
        int count = 1;
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1)) {

            String line = null;
            while ((line = br.readLine()) != null) {

                String[] split = line.split(":");
                if (count == 1) {
                    String url = "";
                    for (int i = 1; i < split.length; i++) {
                        url += split[i];
                        if (i != split.length - 1) {
                            url += ":";
                        }
                    }
                    setUrl(url);

                } else if (count == 2) {
                    setRoot(split[1]);
                } else {
                    setPassword(split[1]);
                }
                count = count + 1;
            }
        } catch (IOException ioe) {
            System.out.println("File " + configFile + " not found. " + ioe.getMessage());
            //System.exit(0);
        }
    }

    /**
     * Check if the url, root & password were assigned approprietly
     */
    public boolean checkConfiguration() {
        if (getUrl() == null || getRoot() == null || getPassword() == null) {
            return false;
        }
        return true;
    }

    /**
     * Exit
     */
    public void exit() {
        System.exit(0);
    }
}

