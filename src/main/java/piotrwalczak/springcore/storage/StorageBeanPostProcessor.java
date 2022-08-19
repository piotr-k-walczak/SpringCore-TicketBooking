package piotrwalczak.springcore.storage;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class StorageBeanPostProcessor implements BeanPostProcessor {

    private String storageFilePath;

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(StorageImpl.class)) {
            ((StorageImpl) bean).loadFromFile(storageFilePath);
        }
        return bean;
    }

    public String getStorageFilePath() {
        return storageFilePath;
    }

    public void setStorageFilePath(String storageFilePath) {
        this.storageFilePath = storageFilePath;
    }
}
