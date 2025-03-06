import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.FileHandler;

public class Captcha {


//    public static void main(String[] args) throws IOException {
//        // Make the API call
//        Response response = RestAssured.get("https://kathir.kerala.gov.in/v3/um/captcha/fetch");
//
//        byte[] binaryData = response.getBody().asByteArray();
//
//        // Encode binary data to Base64 string
//        String base64EncodedString = Base64.getEncoder().encodeToString(binaryData);
//
//            // Decode the Base64 string to get the image bytes
//            byte[] imageBytes = Base64.getDecoder().decode(base64EncodedString);
//
//            // Convert the byte array into an image
//            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
//            Image image = ImageIO.read(bis);
//
//            // Display the image in a JFrame
//            JFrame frame = new JFrame("Decoded Image");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(500, 500);
//            JLabel label = new JLabel(new ImageIcon(image));
//            frame.add(label, BorderLayout.CENTER);
//            frame.setVisible(true);
//            ImageIcon icon = (ImageIcon) label.getIcon(); // Get ImageIcon from JLabel
//            BufferedImage img = (BufferedImage) icon.getImage();
//            ImageIO.write(img, "png", new File("C:\\Users\\dange\\IdeaProjects\\API\\output.jpg"));
//            readImageText(img);
//
//    }
//
//    public static void readImageText(BufferedImage image) {
//        try {
//            // Set up Tesseract (using Tess4J)
//            net.sourceforge.tess4j.Tesseract tesseract = new net.sourceforge.tess4j.Tesseract();
//            tesseract.setLanguage("eng"); // Set the language for OCR (English in this case)
//
//            // Perform OCR on the image
//            String result = tesseract.doOCR(image);
//
//            // Print the extracted text
//            System.out.println("Extracted text: " + result);
//
//        } catch (net.sourceforge.tess4j.TesseractException e) {
//            e.printStackTrace();
//        }
//    }
//}


//    public static void main(String[] args) {
//        // Make the API call
//        Response response = RestAssured.get("https://kathir.kerala.gov.in/v3/um/captcha/fetch");
//
//        // Verify the Content-Type
//        String contentType = response.getHeader("Content-Type");
//        System.out.println("Content-Type: " + contentType);
//
//        // Get binary data from the response
//        byte[] binaryData = response.getBody().asByteArray();
//
//        // Check if the binary data is valid
//        if (binaryData.length > 0) {
//            // Encode binary data to Base64 string
//            String base64EncodedString = Base64.getEncoder().encodeToString(binaryData);
//
//            System.out.println("Base64 Encoded String: " + base64EncodedString);
//        } else {
//            System.out.println("No binary data found in the response!");
//        }
//    }


    public static void main(String[] args) throws TesseractException, IOException {
        // Make API call
        Response response = RestAssured.get("https://kathir.kerala.gov.in/v3/um/captcha/fetch");

        byte[] binaryData = response.getBody().asByteArray();

        // Encode binary data to Base64 string
        String base64EncodedString = Base64.getEncoder().encodeToString(binaryData);

        byte[] imageBytes = Base64.getDecoder().decode(base64EncodedString);

        // Save the binary data as an image file
        String outputPath = "output.jpg";
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            fos.write(imageBytes);
            System.out.println("Image saved as: " + outputPath);
        } catch (IOException e) {
            System.out.println("Error saving the image: " + e.getMessage());
        }

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\dange\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
        tesseract.setLanguage("eng");
        tesseract.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
        String img = tesseract.doOCR(new File("C:\\Users\\dange\\IdeaProjects\\API\\output.jpg"));
        System.out.println(img.replace(" ", ""));

    }

}




