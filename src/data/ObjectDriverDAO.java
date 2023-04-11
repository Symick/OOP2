package data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Driver;

import java.io.*;

/**
 * Object DAO for the driver model
 *
 * @author Julian Kruithof
 */
public class ObjectDriverDAO extends DriverDAO {
    private final String FILENAME = "resources/drivers.obj";
    TeamDAO teamDAO = MainApplication.getTeamDAO();


    @Override
    public boolean load() {
        File file = new File(FILENAME);
        objects.clear();
        try (FileInputStream fin = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fin)) {
            int size = objectInputStream.readInt();

            for (int i = 0; i < size; i++) {
                int belongsTo = objectInputStream.readInt();
                Driver driver = (Driver) objectInputStream.readObject();
                driver.setBelongsTo(teamDAO.getById(belongsTo));
                objects.add(driver);
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        } catch (IOException ex) {
            System.err.println("Could not read file");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class doesn't exists");
        }
        return false;
    }

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try (FileOutputStream fout = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fout)) {
            objectOutputStream.writeInt(objects.size());

            for (Driver driver : objects) {
                objectOutputStream.writeInt(teamDAO.getIdFor(driver.getBelongsTo()));
                objectOutputStream.writeObject(driver);
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        } catch (IOException ex) {
            System.err.println("Could not write to file");
        } catch (Exception ex) {
            System.err.println("Something went wrong");
            ex.printStackTrace();
        }
        return false;
    }
}
