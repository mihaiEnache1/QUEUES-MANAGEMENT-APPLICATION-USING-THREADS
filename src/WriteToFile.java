import java.io.FileWriter;

public class WriteToFile {
    public void write(String fileName, String text){
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(text);
            fileWriter.close();
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }
}
