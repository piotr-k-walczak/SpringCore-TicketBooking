package piotrwalczak.springcore.storage;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import piotrwalczak.springcore.model.EventImpl;
import piotrwalczak.springcore.model.TicketImpl;
import piotrwalczak.springcore.model.UserImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StorageImpl implements Storage {

    private final static Logger logger = LoggerFactory.getLogger(StorageImpl.class);

    private final Map<String, Object> storageMap;

    StorageImpl() {
        storageMap = new HashMap<>();
    }

    @Override
    public void loadFromFile(String filepath) {
        logger.info("Called StorageImpl.loadFromFile(" + filepath + ")");
        File f = new File(filepath);
        if (f.exists()) {
            try {
                InputStream is = new FileInputStream(filepath);
                String jsonTxt = IOUtils.toString(is, "UTF-8");
                logger.info("File " + filepath + "contains = " + jsonTxt);

                JSONObject json = new JSONObject(jsonTxt);
                Gson gson = new Gson();
                for (EventImpl e : gson.fromJson(json.getJSONArray("events").toString(), EventImpl[].class)) {
                    this.put("event:" + e.getId(), e);
                }

                for (TicketImpl t : gson.fromJson(json.getJSONArray("tickets").toString(), TicketImpl[].class)) {
                    this.put("ticket:" + t.getId(), t);
                }

                for (UserImpl u : gson.fromJson(json.getJSONArray("users").toString(), UserImpl[].class)) {
                    this.put("user:" + u.getId(), u);
                }
            } catch (IOException e) {
                logger.error("Couldn't open file on given path: " + filepath);
            }
        } else {
            logger.warn("Couldn't find file on given path: " + f.getAbsolutePath());
        }
    }

    @Override
    public Optional<Object> get(String key) {
        logger.info("Requested data from storage for key: " + key);
        return storageMap.containsKey(key) ? Optional.of(storageMap.get(key)) : Optional.empty();
    }

    @Override
    public List<Object> getAllForType(String type) {
        logger.info("Requested all data from storage for type: " + type);
        return storageMap.entrySet().stream()
                .filter(entry -> entry.getKey().contains(type.toLowerCase()))
                .map(Map.Entry::getValue)
                .toList();
    }

    @Override
    public boolean delete(String key) {
        logger.info("Requested deletion from storage for key: " + key);
        return storageMap.remove(key) != null;
    }

    @Override
    public Object put(String key, Object object) {
        logger.info("Requested put to storage for key: " + key);
        return storageMap.put(key, object);
    }
}
