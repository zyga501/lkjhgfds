package utils;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Map;

public class ProjectSettings {
    static {
        try {
            String projectSettingsPath = PathUtils.getProjectPath() + "project.xml";
            projectSettings_ = XMLParser.Xml2Map(projectSettingsPath);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String getTopPackagePath() {
        String projectPath = PathUtils.getProjectPath();
        String topPackageName = ProjectSettings.class.getPackage().getName();
        return projectPath.concat(topPackageName);
    }

    public static long getId() {
        try {
            if (projectSettings_ != null && projectSettings_.containsKey("id")) {
                return Long.parseLong(projectSettings_.get("id").toString());
            }
        }
        catch (NumberFormatException exception) {

        }

        return 0;
    }

    public static void reloadRes(){
        try {
            String projectSettingsPath = PathUtils.getProjectPath() + "project.xml";
            projectSettings_ = XMLParser.convertMapFromXmlFile(projectSettingsPath);
        }
        catch (Exception exception) {
        }
    }

    public static long getIdWorkerSeed() {
        try {
            if (projectSettings_ != null && projectSettings_.containsKey("idWorkerSeed")) {
                return Long.parseLong(projectSettings_.get("idWorkerSeed").toString());
            }
        }
        catch (NumberFormatException exception) {

        }

        return 0;
    }

    public static String getPicpath() {
        try {
            if (projectSettings_ != null && projectSettings_.get("Picpath") != null) {
                return (projectSettings_.get("Picpath").toString());
            }
        }
        catch (NumberFormatException exception) {

        }

        return "";
    }

    public static Object getData(String key) {
        if (projectSettings_ != null && projectSettings_.containsKey(key)) {
            return projectSettings_.get(key);
        }

        return  null;
    }
    public static boolean setData(String tagName, String val) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        return XMLDo.setElementValue(PathUtils.getProjectPath() + "project.xml",tagName,val)==1;
    }
    public static Map<String, Object> getMapData(String key) {
        return (Map<String, Object>)getData(key);
    }

    private static Map<String, Object> projectSettings_;
}
