package com.app;

import com.app.service.*;

import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        XmlService xmlService = new XmlService();
        DocumentService documentService = new DocumentService();

        boolean running = true;

        System.out.println("Welcome to Nutren! - meal plan generator");

        while (running) {

            try {
                System.out.println("\n-> Enter doc template path: ");
                String docxPath = scanner.nextLine().trim();
                System.out.println("\n-> Enter xml file path: ");
                String xmlPath = scanner.nextLine().trim();
                System.out.println("\n-> Enter path to PDF be saved: ");
                String pdfOutputPath = scanner.nextLine().trim();
                System.out.println("\n-> Loading ...");

                Map<String, String> placeholderMap = xmlService.readXmlData(xmlPath);
                System.out.println("\n-> Reading XML and preparing for substitutions");
                documentService.generatePdfFromDocx(docxPath, pdfOutputPath, placeholderMap);
                System.out.println("\n-> Injecting data from XMl to DOC and then converting into PDF");
                System.out.println("\n-> Success! PDF generated");
            } catch (Exception e) {
                System.out.println("\n-> Error during process " + e.getMessage());
            }

            System.out.println("\n-> Do you wigh to perform another process? (Y/N): ");
            String response = scanner.nextLine().trim().toUpperCase();
            if (!response.equals("Y")) {
                running = false;
            }
            System.out.println("\n--------------------------------------------------------------------------------");
        }
        System.out.println("\n Session(s) completed. Retunrning to CMD.");
        scanner.close();
    }
}
