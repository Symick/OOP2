package data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Driver;
import practicumopdracht.models.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class TextDriverDAO extends DriverDAO {
    private static final String FILENAME = "resources/drivers.txt";
    private TeamDAO teamDAO = MainApplication.getTeamDAO();
    @Override
    public boolean load() {
        File file = new File(FILENAME);
        objects.clear();
        try(Scanner input = new Scanner(file)) {
            int listSize = input.nextInt();
            //clear buffer
            input.nextLine();
            for (int i = 0; i < listSize; i++) {
                String name = input.nextLine();
                String role = input.nextLine();
                Scanner driverInfo = new Scanner(input.nextLine());
                Team belongsTo = teamDAO.getById(driverInfo.nextInt());
                LocalDate birthday = LocalDate.parse(driverInfo.next());
                int championship = driverInfo.nextInt();
                int completedRaces = driverInfo.nextInt();
                double points = driverInfo.nextDouble();
                boolean active = driverInfo.next().equals("true");
                objects.add(new Driver(belongsTo, role, name, birthday, completedRaces, active,points, championship));
                driverInfo.close();
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("File not Found");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            //print size of driver object list for the load for loop
            printWriter.println(objects.size());

            //write each driver in text file
            for (Driver driver: objects) {
                printWriter.println(driver.getName());
                printWriter.println(driver.getRole());
                int belongsTo = teamDAO.getIdFor(driver.getBelongsTo());
                printWriter.printf("%d %s %d %d %.2f %s\n", belongsTo, driver.getBirthday(), driver.getChampionships(),
                        driver.getCompletedRaces(), driver.getPoints(), driver.isActive());
            }
            return true;
        } catch (FileNotFoundException exception) {
            System.err.println("File not Found");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
