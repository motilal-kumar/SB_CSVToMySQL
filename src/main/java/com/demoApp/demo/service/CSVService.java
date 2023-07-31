package com.demoApp.demo.service;

import com.demoApp.demo.entity.Content;
import com.demoApp.demo.repository.ContentRepository;
import com.demoApp.demo.util.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
  @Autowired
  ContentRepository contentRepository;



  //To save the CSV File data to the database.
  public void save(MultipartFile file) {
    try {
      List<Content> contentsList = CSVHelper.csvToContents(file.getInputStream());
      contentRepository.saveAll(contentsList);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }
}

