import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;

public class jFileChooser {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec;

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                int line = 0;
                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    line++;
                    out.printf("\nLine %4d %-60s ", line, rec);
                    lineCount++;
                    String[] words = rec.split(" ");
                    wordCount = wordCount + words.length;

                    for(String word : words)
                    {
                        charCount = charCount + word.length();
                    }
                }
                System.out.println("\nLine count: " + lineCount + "\nWord count: " + wordCount +
                        "\nCharacter count: " + charCount);
                reader.close();
                out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}