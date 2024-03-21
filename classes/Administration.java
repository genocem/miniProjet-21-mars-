package classes;

import java.sql.*;

public class Administration {

    public Administration() {
    }
    // basic methods

    // Create a table
    static void createTable(Connection connection, String createTableSQL) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table created successfully");
        }
    }

    // Create table for Enseignant
    void createTableEnseignant(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS enseignants ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nom TEXT NOT NULL,"
                + "prenom TEXT NOT NULL)";
        createTable(connection, createTableSQL);
    }

    // Create table for Etudiant
    void createTableEtudiant(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS etudiants ("
                + "id_etudiant INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nom TEXT NOT NULL,"
                + "prenom TEXT NOT NULL,"
                + "groupe_id INTEGER,"
                + "affecter BOOLEAN,"
                + "FOREIGN KEY (groupe_id) REFERENCES groupes(id))";
        createTable(connection, createTableSQL);
    }

    void createTableGroupe(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS groupes ("
                + "id_groupe INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nom TEXT NOT NULL)";
        createTable(connection, createTableSQL);
    }

    // Create table for Soutenance
    void createTableSoutenance(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS soutenances ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "projet_id INTEGER,"
                + "jury_id INTEGER,"
                + "date DATE,"
                + "heure TIME,"
                + "locale TEXT,"
                + "validation BOOLEAN,"
                + "note INTEGER,"
                + "FOREIGN KEY (projet_id) REFERENCES projets(id),"
                + "FOREIGN KEY (jury_id) REFERENCES jurys(id))";
        createTable(connection, createTableSQL);
    }

    // Create table for Jury
    void createTableJury(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS jurys ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "president_id INTEGER,"
                + "rapporteur TEXT,"
                + "examinateur TEXT,"
                + "encadreur TEXT,"
                + "FOREIGN KEY (president_id) REFERENCES enseignants(id))";
        createTable(connection, createTableSQL);
    }

    // Create table for Salle
    void createTableSalle(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS salles ("
                + "num_salle INTEGER PRIMARY KEY,"
                + "soutenance_id INTEGER,"
                + "FOREIGN KEY (soutenance_id) REFERENCES soutenances(id))";
        createTable(connection, createTableSQL);
    }

    // Create table for Projet
    void createTableProjet(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS projets ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "etudiant1_id INTEGER,"
                + "etudiant2_id INTEGER,"
                + "encadreur_id INTEGER,"
                + "description TEXT,"
                + "FOREIGN KEY (etudiant1_id) REFERENCES etudiants(id),"
                + "FOREIGN KEY (etudiant2_id) REFERENCES etudiants(id),"
                + "FOREIGN KEY (encadreur_id) REFERENCES enseignants(id))";
        createTable(connection, createTableSQL);
    }

    // Insert data into the tables

    // Insert data into Enseignant table
    void insertDataEnseignant(Connection connection, String nom, String prenom) throws SQLException {
        String insertSQL = "INSERT INTO enseignants (nom, prenom) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");
        }
    }

    // Insert data into Etudiant table
    void insertDataEtudiant(Connection connection, String nom, String prenom) throws SQLException {
        String insertSQL = "INSERT INTO etudiants (nom, prenom) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");
        }
    }

    // Insert data into Soutenance table
    void insertDataSoutenance(Connection connection, int projet_id, int jury_id, Date date, Time heure, String locale,
            boolean validation, int note) throws SQLException {
        String insertSQL = "INSERT INTO soutenances (projet_id, jury_id, date, heure, locale, validation, note) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, projet_id);
            preparedStatement.setInt(2, jury_id);
            preparedStatement.setDate(3, date);
            preparedStatement.setTime(4, heure);
            preparedStatement.setString(5, locale);
            preparedStatement.setBoolean(6, validation);
            preparedStatement.setInt(7, note);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");
        }
    }

    // Insert data into Jury table
    void insertDataJury(Connection connection, int president_id, Enseignant rapporteur, Enseignant examinateur,
            Enseignant encadreur) throws SQLException {
        String insertSQL = "INSERT INTO jurys (president_id, rapporteur, examinateur, encadreur) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, president_id);
            preparedStatement.setInt(2, rapporteur.id);
            preparedStatement.setInt(3, examinateur.id);
            preparedStatement.setInt(4, encadreur.id);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");
        }
    }

    // Insert data into Salle table
    void insertDataSalle(Connection connection, int num_salle, int soutenance_id) throws SQLException {
        String insertSQL = "INSERT INTO salles (num_salle, soutenance_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, num_salle);
            preparedStatement.setInt(2, soutenance_id);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");
        }
    }

    // Insert data into Projet table
    void insertDataProjet(Connection connection, int etudiant1_id, int etudiant2_id, int encadreur_id,
            String description) throws SQLException {
        String insertSQL = "INSERT INTO projets (etudiant1_id, etudiant2_id, encadreur_id, description) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, etudiant1_id);
            preparedStatement.setInt(2, etudiant2_id);
            preparedStatement.setInt(3, encadreur_id);
            preparedStatement.setString(4, description);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");
        }
    }
    void insertDataGroupe(Connection connection, String nom) throws SQLException {
        String insertSQL = "INSERT INTO groupes (nom) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, nom);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");
        }
    }

    // Retrieve data from the table

    // Retrieve data from Enseignant table
    void retrieveDataEnseignant(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM enseignants";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");

                // Process retrieved data
                // ...

                System.out.println("ID: " + id);
                System.out.println("Nom: " + nom);
                System.out.println("Prenom: " + prenom);
            }
        }
    }

    // Retrieve data from Etudiant table
    void retrieveDataEtudiant(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM etudiants";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");

                // Process retrieved data
                // ...

                System.out.println("ID: " + id);
                System.out.println("Nom: " + nom);
                System.out.println("Prenom: " + prenom);
            }
        }
    }

    // Retrieve data from Soutenance table
    void retrieveDataSoutenance(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM soutenances";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int projet_id = resultSet.getInt("projet_id");
                int jury_id = resultSet.getInt("jury_id");
                Date date = resultSet.getDate("date");
                Time heure = resultSet.getTime("heure");
                String locale = resultSet.getString("locale");
                boolean validation = resultSet.getBoolean("validation");
                int note = resultSet.getInt("note");

                // Process retrieved data
                // ...

                System.out.println("ID: " + id);
                System.out.println("Projet ID: " + projet_id);
                System.out.println("Jury ID: " + jury_id);
                System.out.println("Date: " + date);
                System.out.println("Heure: " + heure);
                System.out.println("Locale: " + locale);
                System.out.println("Validation: " + validation);
                System.out.println("Note: " + note);
            }
        }
    }

    // Retrieve data from Jury table
    void retrieveDataJury(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM jurys";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int president_id = resultSet.getInt("president_id");
                String rapporteur = resultSet.getString("rapporteur");
                String examinateur = resultSet.getString("examinateur");
                String encadreur = resultSet.getString("encadreur");

                // Process retrieved data
                // ...

                System.out.println("ID: " + id);
                System.out.println("President ID: " + president_id);
                System.out.println("Rapporteur: " + rapporteur);
                System.out.println("Examinateur: " + examinateur);
                System.out.println("Encadreur: " + encadreur);
            }
        }
    }

    // Retrieve data from Salle table
    void retrieveDataSalle(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM salles";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                int num_salle = resultSet.getInt("num_salle");
                int soutenance_id = resultSet.getInt("soutenance_id");

                // Process retrieved data
                // ...

                System.out.println("Num Salle: " + num_salle);
                System.out.println("Soutenance ID: " + soutenance_id);
            }
        }
    }

    // Retrieve data from Projet table
    void retrieveDataProjet(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM projets";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int etudiant1_id = resultSet.getInt("etudiant1_id");
                int etudiant2_id = resultSet.getInt("etudiant2_id");
                int encadreur_id = resultSet.getInt("encadreur_id");
                String description = resultSet.getString("description");

                // Process retrieved data
                // ...

                System.out.println("ID: " + id);
                System.out.println("Etudiant1 ID: " + etudiant1_id);
                System.out.println("Etudiant2 ID: " + etudiant2_id);
                System.out.println("Encadreur ID: " + encadreur_id);
                System.out.println("Description: " + description);
            }
        }
    }

    // search method
    boolean search(Connection connection, String tableName, String attribName, String value) throws SQLException {
        String selectSQL = "SELECT * FROM " + tableName + " WHERE " + attribName + " = ?";
        boolean res = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                res = true;
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                System.out.println("ID: " + id);
                System.out.println("Nom: " + nom);
                System.out.println("Prenom: " + prenom);
            }
            return res;
        }
    }

    // double search method
    boolean search2(Connection connection, String tableName, String attribName1, String value1, String attribName2,
            String value2) throws SQLException {
        String selectSQL = "SELECT * FROM " + tableName + " WHERE " + attribName1 + " = ? AND " + attribName2 + " = ?";
        boolean res = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, value1);
            preparedStatement.setString(2, value2);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                res = true;
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                System.out.println("ID: " + id);
                System.out.println("Nom: " + nom);
                System.out.println("Prenom: " + prenom);
            }
            return res;
        }
    }

    // triple search method
    boolean search3(Connection connection, String tableName, String attribName1, String value1, String attribName2,
            String value2, String attribName3, String value3) throws SQLException {
        String selectSQL = "SELECT * FROM " + tableName + " WHERE " + attribName1 + " = ? AND " + attribName2
                + " = ? AND "
                + attribName3 + " = ?";
        boolean res = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, value1);
            preparedStatement.setString(2, value2);
            preparedStatement.setString(3, value3);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                res = true;
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                System.out.println("ID: " + id);
                System.out.println("Nom: " + nom);
                System.out.println("Prenom: " + prenom);
            }
            return res;
        }
    }

    // delete method
    void delete(Connection connection, String tableName, String attribName, String value) throws SQLException {
        String deleteSQL = "DELETE FROM " + tableName + " WHERE " + attribName + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
        }
    }

    // update method
    void update(Connection connection, String tableName, String attribName, String value, String changeAttribName,
            String newValue) throws SQLException {
        String updateSQL = "UPDATE " + tableName + " SET " + changeAttribName + " = ? WHERE " + attribName + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, value);
            preparedStatement.executeUpdate();
        }
    }

    // other methods

    void addEnseignant(Connection connection, int id, String nom, String prenom) throws SQLException {
        if (search(connection, "Enseignant", "id", String.valueOf(id))) {
            System.out.println("Enseignant already exists");
        } else {
            insertDataEnseignant(connection, nom, prenom);
        }
    }

    void addEtudiant(Connection connection, int id, String nom, String prenom) throws SQLException {
        if (search(connection, "Etudiant", "id", String.valueOf(id))) {
            System.out.println("Etudiant already exists");
        } else {
            insertDataEtudiant(connection, nom, prenom);
        }
    }

    void addSoutenance(Connection connection, int id, int projet_id, int jury_id, Date date, Time heure, String locale)
            throws SQLException {
        // checking if it exists
        if (search(connection, "Soutenance", "id", String.valueOf(id))) {
            System.out.println("Soutenance already exists");
        } // then checking if the salle if already accupied
        else if (search(connection, "salle", "num_salle", locale)) {
            System.out.println("Salle deja occupee");
        } // checking if the jury is already busy
        else if (search3(connection, "Soutenance", "jury_id", String.valueOf(jury_id), "date", String.valueOf(date),
                "heure", String.valueOf(heure))) {
            System.out.println("Jury deja occupee");
        } else {
            insertDataSoutenance(connection, projet_id, jury_id, date, heure, locale, false, 0);
        }
    }

    void addJury(Connection connection, int id, int president_id, Enseignant rapporteur, Enseignant examinateur,
            Enseignant encadreur) throws SQLException {
        if (search(connection, "Jury", "id", String.valueOf(id))) {
            System.out.println("Jury already exists");
        } else {
            insertDataJury(connection, president_id, rapporteur, examinateur, encadreur);
        }
    }

    void addSalle(Connection connection, int num_salle, int soutenance_id) throws SQLException {
        if (search(connection, "Salle", "num_salle", String.valueOf(num_salle))) {
            System.out.println("Salle already exists");
        } else {
            insertDataSalle(connection, num_salle, soutenance_id);
        }
    }

    void addProjet(Connection connection, int id, int etudiant1_id, int etudiant2_id, int encadreur_id,
            String description)
            throws SQLException {
        if (search(connection, "Projet", "id", String.valueOf(id))) {
            System.out.println("Projet already exists");
        }
        else if (etudiant2_id == 0 && search(connection, "Projet", "etudiant1_id", String.valueOf(etudiant1_id)))
            System.out.println("Etudiant 1 already in a project");
        else if (etudiant2_id != 0 &&
                (search(connection, "Projet", "etudiant1_id", String.valueOf(etudiant1_id))
                        || search(connection, "Projet", "etudiant2_id", String.valueOf(etudiant2_id)))) {
            System.out.println("Etudiant 1 or Etudiant 2 is already in a project");
        }

        else {
            insertDataProjet(connection, etudiant1_id, etudiant2_id, encadreur_id, description);
        }
    }

    void addGroupe(Connection connection,  String nom) throws SQLException {
        if (search(connection, "Groupe", "Nom_groupe", nom)) {
            System.out.println("Groupe already exists");
        } else {
            insertDataGroupe(connection, nom);
        }
    }

    void validationSoutenance(Connection connection, int soutenance_id, boolean validation, int note) throws SQLException {
        update(connection, "Soutenance", "id", String.valueOf(soutenance_id), "validation", String.valueOf(validation));
        update(connection, "Soutenance", "id", String.valueOf(soutenance_id), "note", String.valueOf(note));
    }
}