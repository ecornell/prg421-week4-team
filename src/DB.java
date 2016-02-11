/**
 * Title:          Week 4 - Program Improvement III
 * Author:         Team B : ( Elijah Cornell / Eric Landeis / Gordon Doskas / James Rippon /
 *                            Joseph Hart / Keith Green / Lance Branford )
 * Creation Date:  2016-02-11
 * Class:          PRG/421 - Roland Morales
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private static final DB SINGLETON = new DB();

    public static DB getInstance() {
        return SINGLETON;
    }

    private Connection conn;

    private DB() {
    }

    public void initDB() throws SQLException {

        DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());

        conn = DriverManager.getConnection("jdbc:derby:db/animal;create=true");

        try (Statement s = conn.createStatement()) {

            String createString = "CREATE TABLE animal  "
                    + "(animal_id INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
                    + " name VARCHAR(32) NOT NULL, "
                    + " color VARCHAR(32) NOT NULL, "
                    + " habitat VARCHAR(32) NOT NULL, "
                    + " sound VARCHAR(32) NOT NULL, "
                    + " skeleton BOOLEAN NOT NULL) ";

            s.execute(createString);

            //
            
            List<Animal> animalList = new ArrayList<>();
            animalList.add(new Animal("Lion",true, "Roar", "Savannah", "Yellow"));
            animalList.add(new Animal("Tiger",true, "Roar", "Forest", "Orange"));
            animalList.add(new Animal("Leech",true, "none", "Swamp", "Black"));
            animalList.add(new Animal("Octopus",true, "none", "Ocean", "Multi-Chromatic"));

            for (Animal animal: animalList) {
                insertAnimal(animal);
            }

        } catch (SQLException e) {

            if (e.getSQLState().equals("X0Y32")) {
                // Do nothing - animal table already exists
            } else {
                System.out.println("ERROR: " + getSQLException(e));
            }
        }

    }

    public void insertAnimal(Animal animal) {

        String pQuery = "INSERT INTO animal (name, color, habitat, sound, skeleton) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(pQuery)) {

            pstmt.setString(1, animal.getName());
            pstmt.setString(2, animal.getColor());
            pstmt.setString(3, animal.getHabitat());
            pstmt.setString(4, animal.getSound());
            pstmt.setBoolean(5, animal.getSkeletonPresent());

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("ERROR: " + getSQLException(e));
        }

    }

    public void updateAnimal(Animal animal) {

        String pQuery = "UPDATE animal SET name=?, color=?, habitat=?, sound=?, skeleton=? WHERE animal_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(pQuery)) {

            pstmt.setString(1, animal.getName());
            pstmt.setString(2, animal.getColor());
            pstmt.setString(3, animal.getHabitat());
            pstmt.setString(4, animal.getSound());
            pstmt.setBoolean(5, animal.getSkeletonPresent());
            pstmt.setInt(6, animal.getId());

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("ERROR: " + getSQLException(e));
        }

    }

    public void deleteAnimal(Animal animal) {

        String pQuery = "DELETE from animal where animal_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(pQuery)) {

            pstmt.setInt(1, animal.getId());
            pstmt.execute();

        } catch (SQLException e) {
            System.out.println("ERROR: " + getSQLException(e));
        }

    }

    public List<Animal> readAllAnimals() {

        List<Animal> animalList = new ArrayList<>();

        String createString = "select animal_id, name, color, habitat, sound, skeleton from animal order by name";

        try (Statement s = conn.createStatement()) {

            ResultSet rs = s.executeQuery(createString);

            while (rs.next()) {

                Animal a = new Animal();

                a.setId(rs.getInt("animal_id"));
                a.setName(rs.getString("name"));
                a.setColor(rs.getString("color"));
                a.setHabitat(rs.getString("habitat"));
                a.setSound(rs.getString("sound"));
                a.setSkeletonPresent(rs.getBoolean("skeleton"));

                animalList.add(a);

            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("ERROR: " + getSQLException(e));
        }

        return animalList;

    }


    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("ERROR: Failed to close database - " + e.getErrorCode() + " " + e.getSQLState() + " " + e.getMessage());
            }
        }
    }

    private String getSQLException(SQLException e) {
        return e.getErrorCode() + " " + e.getSQLState() + " " + e.getMessage();
    }

}
