package io.github.lawseff.gadgets.web.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GadgetControllerTest extends ApiTest {

  @Test
  void firstPageReturnedByDefault() throws Exception {
    mockMvc.perform(get("/gadgets"))
        .andExpect(status().isOk())
        .andExpect(header().string(HttpHeaders.CACHE_CONTROL, "max-age=3600"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(
            """
            {
              "data": [
                {
                  "id": "%s",
                  "name": "Action Camera",
                  "dimensions": {
                    "unit": "MM",
                    "length": 60,
                    "width": 40,
                    "height": 30
                  },
                  "imageUrl": null
                },
                {
                  "id": "%s",
                  "name": "Bluetooth Speaker",
                  "dimensions": {
                    "unit": "MM",
                    "length": 150,
                    "width": 80,
                    "height": 25
                  },
                  "imageUrl": null
                },
                {
                  "id": "%s",
                  "name": "Clock (Digital)",
                  "dimensions": {
                    "unit": "MM",
                    "length": 120,
                    "width": 40,
                    "height": 80
                  },
                  "imageUrl": null
                },
                {
                  "id": "%s",
                  "name": "Drone",
                  "dimensions": {
                    "unit": "MM",
                    "length": 300,
                    "width": 300,
                    "height": 100
                  },
                  "imageUrl": null
                },
                {
                  "id": "%s",
                  "name": "E-reader",
                  "dimensions": {
                    "unit": "MM",
                    "length": 150,
                    "width": 100,
                    "height": 5
                  },
                  "imageUrl": null
                },
                {
                  "id": "%s",
                  "name": "Fitness Tracker",
                  "dimensions": {
                    "unit": "MM",
                    "length": 50,
                    "width": 20,
                    "height": 10
                  },
                  "imageUrl": null
                },
                {
                  "id": "%s",
                  "name": "Glucose Meter",
                  "dimensions": {
                    "unit": "MM",
                    "length": 80,
                    "width": 60,
                    "height": 20
                  },
                  "imageUrl": null
                },
                {
                  "id": "%s",
                  "name": "Headphones",
                  "dimensions": {
                    "unit": "MM",
                    "length": 180,
                    "width": 150,
                    "height": 50
                  },
                  "imageUrl": null
                }
              ],
              "pagination": {
                "totalCount": 8,
                "totalPages": 1,
                "page": 1,
                "size": 12
              }
            }
            """.formatted(
                testData.getActionCamera().getId(),
                testData.getBluetoothSpeaker().getId(),
                testData.getClock().getId(),
                testData.getDrone().getId(),
                testData.getEReader().getId(),
                testData.getFitnessTracker().getId(),
                testData.getGlucoseMeter().getId(),
                testData.getHeadphones().getId()
            ),
            // Strict assert for array ordering
            true
        ));
  }

  @Test
  void secondPageReturnedWhenRequested() throws Exception {
    mockMvc.perform(get("/gadgets?page=2&size=3"))
        .andExpect(status().isOk())
        .andExpect(header().string(HttpHeaders.CACHE_CONTROL, "max-age=3600"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(
            """
            {
              "data": [
                {
                  "id": "%s",
                  "name": "Drone",
                  "dimensions": {
                    "unit": "MM",
                    "length": 300,
                    "width": 300,
                    "height": 100
                  },
                  "imageUrl": null
                },
                {
                  "id": "%s",
                  "name": "E-reader",
                  "dimensions": {
                    "unit": "MM",
                    "length": 150,
                    "width": 100,
                    "height": 5
                  },
                  "imageUrl": null
                },
                {
                  "id": "%s",
                  "name": "Fitness Tracker",
                  "dimensions": {
                    "unit": "MM",
                    "length": 50,
                    "width": 20,
                    "height": 10
                  },
                  "imageUrl": null
                }
              ],
              "pagination": {
                "totalCount": 8,
                "totalPages": 3,
                "page": 2,
                "size": 3
              }
            }
            """.formatted(
                testData.getDrone().getId(),
                testData.getEReader().getId(),
                testData.getFitnessTracker().getId()
            ),
            // Strict assert for array ordering
            true
        ));
  }

  @ParameterizedTest
  @CsvSource({
      "-3,-3,12", // Negative values
      "PAGE,SIZE,12", // Not a number
      "1,100,50" // Exceeds the max page size. Should fall back to the max value
  })
  void usesDefaultPaginationIfInvalid(String page, String size, String expectedSize) throws Exception {
    mockMvc.perform(get("/gadgets?page=%s&size=%s".formatted(page, size)))
        .andExpect(status().isOk())
        .andExpect(header().string(HttpHeaders.CACHE_CONTROL, "max-age=3600"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.pagination.page").value(1))
        .andExpect(jsonPath("$.pagination.size").value(expectedSize))
        .andExpect(jsonPath("$.pagination.totalPages").value(1))
        .andExpect(jsonPath("$.pagination.totalCount").value(8))
        .andExpect(jsonPath("$.data.length()").value(8));
  }

}