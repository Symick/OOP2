package data;

import practicumopdracht.models.Team;

import java.io.*;


public class BinaryTeamDAO extends TeamDAO{
    private final String FILENAME = "resources/teams.dat";
    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try (FileInputStream fin = new FileInputStream(file);
        DataInputStream din = new DataInputStream(fin)) {
            int size = din.readInt();
            for (int i = 0; i < size; i++) {
                String name = din.readUTF();
                int firstYearEntry = din.readInt();
                int championships = din.readInt();
                boolean active = din.readBoolean();
                Team team = new Team(name, firstYearEntry, active, championships);
                objects.add(team);

            }
            return true;
        }catch (FileNotFoundException ex) {
            System.err.println("File not Found");
        } catch (IOException ex) {
            System.err.println("File could not be read");
        }
        return false;
    }

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try (FileOutputStream fout = new FileOutputStream(file);
        DataOutputStream dout = new DataOutputStream(fout)){
            //print size for for-loop in load method
            dout.writeInt(objects.size());

            for (Team team : objects) {
                dout.writeUTF(team.getName());
                dout.writeInt(team.getFirstEntryYear());
                dout.writeInt(team.getTeamChampionships());
                dout.writeBoolean(team.isActive());
            }
            dout.flush();
            return true;

        } catch (FileNotFoundException ex) {
            System.err.println("File not Found");
        } catch (IOException ex) {
            System.err.println("File could not be written to");
        }
        return false;
    }
}
