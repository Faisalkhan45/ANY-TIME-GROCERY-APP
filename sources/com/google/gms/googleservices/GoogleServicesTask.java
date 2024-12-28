package com.google.gms.googleservices;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.file.Directory;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.FileSystemLocation;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

public abstract class GoogleServicesTask extends DefaultTask {
    public static final String JSON_FILE_NAME = "google-services.json";
    private static final String OAUTH_CLIENT_TYPE_WEB = "3";
    private static final String STATUS_DISABLED = "1";
    private static final String STATUS_ENABLED = "2";
    @InputFiles
    final FileCollection googleServicesJsonFiles = getProject().getObjects().fileCollection();

    @Input
    public abstract Property<String> getApplicationId();

    @OutputDirectory
    public abstract DirectoryProperty getOutputDirectory();

    @Internal
    public File getIntermediateDir() {
        return (File) getOutputDirectory().getAsFile().get();
    }

    public FileCollection getGoogleServicesJsonFiles() {
        return this.googleServicesJsonFiles;
    }

    @TaskAction
    public void action() throws IOException {
        File quickstartFile = null;
        String searchedLocation = System.lineSeparator();
        Iterator it = ((Set) this.googleServicesJsonFiles.getElements().get()).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            File jsonFile = ((FileSystemLocation) it.next()).getAsFile();
            searchedLocation = searchedLocation + jsonFile.getPath() + System.lineSeparator();
            if (jsonFile.isFile()) {
                quickstartFile = jsonFile;
                break;
            }
        }
        if (quickstartFile == null || !quickstartFile.isFile()) {
            throw new GradleException(String.format("File %s is missing. The Google Services Plugin cannot function without it. %n Searched Location: %s", new Object[]{JSON_FILE_NAME, searchedLocation}));
        }
        getLogger().info("Parsing json file: " + quickstartFile.getPath());
        File intermediateDir = ((Directory) getOutputDirectory().get()).getAsFile();
        deleteFolder(intermediateDir);
        if (intermediateDir.mkdirs()) {
            JsonElement root = new JsonParser().parse((Reader) Files.newReader(quickstartFile, Charsets.UTF_8));
            if (root.isJsonObject()) {
                JsonObject rootObject = root.getAsJsonObject();
                Map<String, String> resValues = new TreeMap<>();
                Map<String, Map<String, String>> resAttributes = new TreeMap<>();
                handleProjectNumberAndProjectId(rootObject, resValues);
                handleFirebaseUrl(rootObject, resValues);
                JsonObject clientObject = getClientForPackageName(rootObject);
                if (clientObject != null) {
                    handleAnalytics(clientObject, resValues);
                    handleMapsService(clientObject, resValues);
                    handleGoogleApiKey(clientObject, resValues);
                    handleGoogleAppId(clientObject, resValues);
                    handleWebClientId(clientObject, resValues);
                    File values = new File(intermediateDir, "values");
                    if (values.exists() || values.mkdirs()) {
                        Files.asCharSink(new File(values, "values.xml"), Charsets.UTF_8, new FileWriteMode[0]).write(getValuesContent(resValues, resAttributes));
                        return;
                    }
                    throw new GradleException("Failed to create folder: " + values);
                }
                throw new GradleException("No matching client found for package name '" + ((String) getApplicationId().get()) + "'");
            }
            throw new GradleException("Malformed root json");
        }
        throw new GradleException("Failed to create folder: " + intermediateDir);
    }

    private void handleFirebaseUrl(JsonObject rootObject, Map<String, String> resValues) throws IOException {
        JsonObject projectInfo = rootObject.getAsJsonObject("project_info");
        if (projectInfo != null) {
            JsonPrimitive firebaseUrl = projectInfo.getAsJsonPrimitive("firebase_url");
            if (firebaseUrl != null) {
                resValues.put("firebase_database_url", firebaseUrl.getAsString());
                return;
            }
            return;
        }
        throw new GradleException("Missing project_info object");
    }

    private void handleProjectNumberAndProjectId(JsonObject rootObject, Map<String, String> resValues) throws IOException {
        JsonObject projectInfo = rootObject.getAsJsonObject("project_info");
        if (projectInfo != null) {
            JsonPrimitive projectNumber = projectInfo.getAsJsonPrimitive("project_number");
            if (projectNumber != null) {
                resValues.put("gcm_defaultSenderId", projectNumber.getAsString());
                JsonPrimitive projectId = projectInfo.getAsJsonPrimitive("project_id");
                if (projectId != null) {
                    resValues.put("project_id", projectId.getAsString());
                    JsonPrimitive bucketName = projectInfo.getAsJsonPrimitive("storage_bucket");
                    if (bucketName != null) {
                        resValues.put("google_storage_bucket", bucketName.getAsString());
                        return;
                    }
                    return;
                }
                throw new GradleException("Missing project_info/project_id object");
            }
            throw new GradleException("Missing project_info/project_number object");
        }
        throw new GradleException("Missing project_info object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        r4 = r3.getAsJsonObject();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleWebClientId(com.google.gson.JsonObject r11, java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
            r10 = this;
            java.lang.String r0 = "oauth_client"
            com.google.gson.JsonArray r0 = r11.getAsJsonArray(r0)
            if (r0 == 0) goto L_0x004c
            int r1 = r0.size()
            r2 = 0
        L_0x000d:
            if (r2 >= r1) goto L_0x004c
            com.google.gson.JsonElement r3 = r0.get(r2)
            if (r3 == 0) goto L_0x0049
            boolean r4 = r3.isJsonObject()
            if (r4 != 0) goto L_0x001c
            goto L_0x0049
        L_0x001c:
            com.google.gson.JsonObject r4 = r3.getAsJsonObject()
            java.lang.String r5 = "client_type"
            com.google.gson.JsonPrimitive r5 = r4.getAsJsonPrimitive(r5)
            if (r5 != 0) goto L_0x0029
            goto L_0x0049
        L_0x0029:
            java.lang.String r6 = r5.getAsString()
            java.lang.String r7 = "3"
            boolean r7 = r7.equals(r6)
            if (r7 != 0) goto L_0x0036
            goto L_0x0049
        L_0x0036:
            java.lang.String r7 = "client_id"
            com.google.gson.JsonPrimitive r7 = r4.getAsJsonPrimitive(r7)
            if (r7 != 0) goto L_0x003f
            goto L_0x0049
        L_0x003f:
            java.lang.String r8 = r7.getAsString()
            java.lang.String r9 = "default_web_client_id"
            r12.put(r9, r8)
            return
        L_0x0049:
            int r2 = r2 + 1
            goto L_0x000d
        L_0x004c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesTask.handleWebClientId(com.google.gson.JsonObject, java.util.Map):void");
    }

    private void handleAnalytics(JsonObject clientObject, Map<String, String> resValues) throws IOException {
        JsonObject analyticsProp;
        JsonPrimitive trackingId;
        JsonObject analyticsService = getServiceByName(clientObject, "analytics_service");
        if (analyticsService != null && (analyticsProp = analyticsService.getAsJsonObject("analytics_property")) != null && (trackingId = analyticsProp.getAsJsonPrimitive("tracking_id")) != null) {
            resValues.put("ga_trackingId", trackingId.getAsString());
            File xml = new File(((Directory) getOutputDirectory().get()).getAsFile(), "xml");
            if (xml.exists() || xml.mkdirs()) {
                Files.asCharSink(new File(xml, "global_tracker.xml"), Charsets.UTF_8, new FileWriteMode[0]).write(getGlobalTrackerContent(trackingId.getAsString()));
                return;
            }
            throw new GradleException("Failed to create folder: " + xml);
        }
    }

    private void handleMapsService(JsonObject clientObject, Map<String, String> resValues) throws IOException {
        if (getServiceByName(clientObject, "maps_service") != null) {
            String apiKey = getAndroidApiKey(clientObject);
            if (apiKey != null) {
                resValues.put("google_maps_key", apiKey);
                return;
            }
            throw new GradleException("Missing api_key/current_key object");
        }
    }

    private void handleGoogleApiKey(JsonObject clientObject, Map<String, String> resValues) {
        String apiKey = getAndroidApiKey(clientObject);
        if (apiKey != null) {
            resValues.put("google_api_key", apiKey);
            resValues.put("google_crash_reporting_api_key", apiKey);
            return;
        }
        throw new GradleException("Missing api_key/current_key object");
    }

    private String getAndroidApiKey(JsonObject clientObject) {
        JsonPrimitive currentKey;
        JsonArray array = clientObject.getAsJsonArray("api_key");
        if (array == null) {
            return null;
        }
        int count = array.size();
        for (int i = 0; i < count; i++) {
            JsonElement apiKeyElement = array.get(i);
            if (apiKeyElement != null && apiKeyElement.isJsonObject() && (currentKey = apiKeyElement.getAsJsonObject().getAsJsonPrimitive("current_key")) != null) {
                return currentKey.getAsString();
            }
        }
        return null;
    }

    private static void findStringByName(JsonObject jsonObject, String stringName, Map<String, String> resValues) {
        JsonPrimitive id = jsonObject.getAsJsonPrimitive(stringName);
        if (id != null) {
            resValues.put(stringName, id.getAsString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        r4 = r3.getAsJsonObject();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.gson.JsonObject getClientForPackageName(com.google.gson.JsonObject r11) {
        /*
            r10 = this;
            java.lang.String r0 = "client"
            com.google.gson.JsonArray r0 = r11.getAsJsonArray(r0)
            if (r0 == 0) goto L_0x0053
            int r1 = r0.size()
            r2 = 0
        L_0x000d:
            if (r2 >= r1) goto L_0x0053
            com.google.gson.JsonElement r3 = r0.get(r2)
            if (r3 == 0) goto L_0x0050
            boolean r4 = r3.isJsonObject()
            if (r4 != 0) goto L_0x001c
            goto L_0x0050
        L_0x001c:
            com.google.gson.JsonObject r4 = r3.getAsJsonObject()
            java.lang.String r5 = "client_info"
            com.google.gson.JsonObject r5 = r4.getAsJsonObject(r5)
            if (r5 != 0) goto L_0x0029
            goto L_0x0050
        L_0x0029:
            java.lang.String r6 = "android_client_info"
            com.google.gson.JsonObject r6 = r5.getAsJsonObject(r6)
            if (r6 != 0) goto L_0x0032
            goto L_0x0050
        L_0x0032:
            java.lang.String r7 = "package_name"
            com.google.gson.JsonPrimitive r7 = r6.getAsJsonPrimitive(r7)
            if (r7 != 0) goto L_0x003b
            goto L_0x0050
        L_0x003b:
            org.gradle.api.provider.Property r8 = r10.getApplicationId()
            java.lang.Object r8 = r8.get()
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r9 = r7.getAsString()
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0050
            return r4
        L_0x0050:
            int r2 = r2 + 1
            goto L_0x000d
        L_0x0053:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesTask.getClientForPackageName(com.google.gson.JsonObject):com.google.gson.JsonObject");
    }

    private void handleGoogleAppId(JsonObject clientObject, Map<String, String> resValues) throws IOException {
        JsonObject clientInfo = clientObject.getAsJsonObject("client_info");
        if (clientInfo != null) {
            JsonPrimitive googleAppId = clientInfo.getAsJsonPrimitive("mobilesdk_app_id");
            String googleAppIdStr = googleAppId == null ? null : googleAppId.getAsString();
            if (!Strings.isNullOrEmpty(googleAppIdStr)) {
                resValues.put("google_app_id", googleAppIdStr);
                return;
            }
            throw new GradleException("Missing Google App Id. Please follow instructions on https://firebase.google.com/ to get a valid config file that contains a Google App Id");
        }
        throw new GradleException("Client does not have client info");
    }

    private JsonObject getServiceByName(JsonObject clientObject, String serviceName) {
        JsonObject service;
        JsonPrimitive status;
        JsonObject services = clientObject.getAsJsonObject("services");
        if (services == null || (service = services.getAsJsonObject(serviceName)) == null || (status = service.getAsJsonPrimitive(NotificationCompat.CATEGORY_STATUS)) == null) {
            return null;
        }
        String statusStr = status.getAsString();
        if (STATUS_DISABLED.equals(statusStr)) {
            return null;
        }
        if ("2".equals(statusStr)) {
            return service;
        }
        getLogger().warn(String.format("Status with value '%1$s' for service '%2$s' is unknown", new Object[]{statusStr, serviceName}));
        return null;
    }

    private static String getGlobalTrackerContent(String ga_trackingId) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n    <string name=\"ga_trackingId\" translatable=\"false\">" + ga_trackingId + "</string>\n</resources>\n";
    }

    private static String getValuesContent(Map<String, String> values, Map<String, Map<String, String>> attributes) {
        StringBuilder sb = new StringBuilder(256);
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String name = entry.getKey();
            sb.append("    <string name=\"").append(name).append("\" translatable=\"false\"");
            if (attributes.containsKey(name)) {
                for (Map.Entry<String, String> attr : attributes.get(name).entrySet()) {
                    sb.append(" ").append(attr.getKey()).append("=\"").append(attr.getValue()).append("\"");
                }
            }
            sb.append(">").append(entry.getValue()).append("</string>\n");
        }
        sb.append("</resources>\n");
        return sb.toString();
    }

    private static void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else if (!file.delete()) {
                        throw new GradleException("Failed to delete: " + file);
                    }
                }
            }
            if (!folder.delete()) {
                throw new GradleException("Failed to delete: " + folder);
            }
        }
    }

    /* access modifiers changed from: private */
    public static long countSlashes(String input) {
        return input.codePoints().filter(new GoogleServicesTask$$ExternalSyntheticLambda3()).count();
    }

    static /* synthetic */ boolean lambda$countSlashes$0(int x) {
        return x == 47;
    }

    static List<String> getJsonLocations(String buildType, List<String> flavorNames) {
        List<String> fileLocations = new ArrayList<>();
        String flavorName = (String) flavorNames.stream().reduce("", new GoogleServicesTask$$ExternalSyntheticLambda0());
        fileLocations.add("");
        fileLocations.add("src/" + flavorName + "/" + buildType);
        fileLocations.add("src/" + buildType + "/" + flavorName);
        fileLocations.add("src/" + flavorName);
        fileLocations.add("src/" + buildType);
        fileLocations.add("src/" + flavorName + capitalize(buildType));
        fileLocations.add("src/" + buildType);
        String fileLocation = "src";
        for (String flavor : flavorNames) {
            fileLocation = fileLocation + "/" + flavor;
            fileLocations.add(fileLocation);
            fileLocations.add(fileLocation + "/" + buildType);
            fileLocations.add(fileLocation + capitalize(buildType));
        }
        return (List) fileLocations.stream().distinct().sorted(Comparator.comparing(new GoogleServicesTask$$ExternalSyntheticLambda1()).reversed()).map(new GoogleServicesTask$$ExternalSyntheticLambda2()).collect(Collectors.toList());
    }

    static /* synthetic */ String lambda$getJsonLocations$1(String a, String b) {
        return a + (a.length() == 0 ? b : capitalize(b));
    }

    static /* synthetic */ String lambda$getJsonLocations$2(String location) {
        return (location.isEmpty() ? new StringBuilder().append(location) : new StringBuilder().append(location).append('/')).append(JSON_FILE_NAME).toString();
    }

    public static String capitalize(String s) {
        if (s.length() == 0) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
