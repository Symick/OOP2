package data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * DAO for saving objects into a textfile
 *
 * @author Julian Kruithof
 */
public class TextTeamDAO extends TeamDAO{
    private static final String FILENAME = "resources/teams.txt";

    /**
     * Load in all the data found in text file
     * @return returns true for successfully load otherwise false if something went wrong.
     */
    @Override
    public boolean load() {
        File file = new File(FILENAME);
        //clear the list before inserting the teams in file to make sure no duplicates are loaded
        objects.clear();
        try {
            Scanner input = new Scanner(file);
            int listSize = input.nextInt();
            //clear buffer
            input.nextLine();

            for (int i = 0; i < listSize; i++) {
                String name = input.nextLine();
                Scanner team = new Scanner(input.nextLine());
                int firstYearEntry = team.nextInt();
                boolean active = team.next().equals("true");
                int championships = team.nextInt();

                objects.add(new Team(name, firstYearEntry, active, championships));
                team.close();
            }
            input.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("File not Found");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * save in all the data found in text file
     * @return returns true for successfully save otherwise false if something went wrong.
     */
    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try (PrintWriter printWriter = new PrintWriter(file)) {

            //save the size of list, for the load loop
            printWriter.println(objects.size());
            //write each team in a separate line
            for (Team team : objects) {
                printWriter.println(team.getName());
                printWriter.printf("%d %s %d\n", team.getFirstEntryYear(), team.isActive(), team.getTeamChampionships());
            }
            return true;

        } catch (FileNotFoundException exception) {
            System.err.println("File not Found");
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
}
