package gov.sba.utils.integration;

import java.io.File;

public class FixtureUtils {

    /**
     * Return the fixture directory in a system independent way
     *
     * @return String path of the fixture directory only e.g.
     *         ${project_root}/src/main/DataFiles/"
     */
    public static String fixturesDir() {
        return System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
                + "DataFiles" + File.separator;
    }

    public static String fixturesDir_Duns() {
        return System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
                + "resources" + File.separator;
    }

    public static void main(String[] args) {
        System.out.println("fixturesDir" + FixtureUtils.fixturesDir());
    }
}
