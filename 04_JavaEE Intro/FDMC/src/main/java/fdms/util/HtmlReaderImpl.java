package fdms.util;

import java.io.*;

public class HtmlReaderImpl implements HtmlReader {
    @Override
    public String readHtmlFile(String htmlFilePath) {
        File file  = new File(htmlFilePath);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line =  reader.readLine()) != null ){
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString().trim();
    }
}
