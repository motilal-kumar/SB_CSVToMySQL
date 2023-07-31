package com.demoApp.demo.util;

import com.demoApp.demo.entity.Content;
import org.apache.commons.csv.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id", "Title","Author", "Description","Email", "Type", "State", "Published" };

  static Logger logger = LoggerFactory.getLogger(CSVHelper.class);

  public static List<Content> csvToContents(InputStream is) {

    //To Read the CSV File.
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Content> contentList = new ArrayList<>();


      //It will return the content present in the CSV File in the form of Record.
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
          Content content = new Content(
              Long.parseLong(csvRecord.get("Id")),
              csvRecord.get("Title"),
                 // csvRecord.get("Author"),
              csvRecord.get("Description")
                 /* csvRecord.get("Email"),
                  csvRecord.get("Type"),
                  csvRecord.get("State"),
                  Boolean.parseBoolean(csvRecord.get("Published"))*/
            );

       /* Content id = Content.builder()
                .id(Long.parseLong(csvRecord.get("Id")))
                .build();*/
        contentList.add(content);
      }

      return contentList;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }


  //It is used to check the file format is CSV or not.
  public static boolean hasCSVFormat(MultipartFile file) {
      logger.info("getContentType .. {} .."+file.getContentType());
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }
    return false;
  }
}
