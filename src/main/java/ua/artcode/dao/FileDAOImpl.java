package ua.artcode.dao;

import java.io.*;
import java.util.List;

/**
 *
 */
public class FileDAOImpl<T> implements FileDAO <T>{

    private File location;


    public FileDAOImpl(String path) {
        this.location = new File(path);
    }

    @Override
    public void saveAll(List<T> values) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(location))) {
            oos.writeObject(values);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> loadAll() {
        List<T> list = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(location))) {
            list = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
