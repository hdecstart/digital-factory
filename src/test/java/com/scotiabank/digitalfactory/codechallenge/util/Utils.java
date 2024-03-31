package com.scotiabank.digitalfactory.codechallenge.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;

import java.io.IOException;

public class Utils {

  public static <T> T convertToObject(String file, Class<T> cls) {
    T response = null;
    ObjectMapper mapper = new ObjectMapper();
    try {
      response = mapper.readValue(Resources.getResource(file).openStream(), cls);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }
}
