import java.nio.file.*;
import java.sql.*;
import java.util.regex.*;
import java.util.*;

public class DatabaseDriver {

    private static final String URL  = "jdbc:mysql://localhost:3306/MotivationalQuotes13Jul25";
    private static final String USER = "root";
    private static final String PASS = "RootPassword@123";
    private static final String SQL  = "INSERT INTO quotes VALUES (?,?,?)";

    // 					** file --> category **
    private static final LinkedHashMap<String, String> SOURCES = new LinkedHashMap<>() {{
        put("coding_quotes.json",     "coding");
        put("happy_quotes.json",      "happy");
        put("confidence_quotes.json", "confidence");
        put("work_quotes.json",       "work");
    }};

    // -------- regex to capture  "quote": "...." ---------- 
    private static final Pattern QUOTE_PATTERN =
        Pattern.compile("\"quote\"\\s*:\\s*\"([^\"]*)\"", Pattern.MULTILINE);

    public static void main(String[] args) {

        int id = 1;

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement(SQL)) {

            for (var entry : SOURCES.entrySet()) {
                String file     = entry.getKey();
                String category = entry.getValue();

                String json = Files.readString(Path.of(file));

                Matcher m = QUOTE_PATTERN.matcher(json);
                while (m.find()) {
                    String quoteText = m.group(1);

                    pst.setInt(1, id++);
                    pst.setString(2, quoteText);
                    pst.setString(3, category);
                    pst.addBatch();
                }
            }

            pst.executeBatch();
            System.out.println("Inserted " + (id - 1) + " quotes without any external libraries.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
