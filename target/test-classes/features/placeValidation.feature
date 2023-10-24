Feature: Validating addPlace API

  @addPlace @regression
  Scenario Outline: Verify if the place is being added successfully using addPlace API
    Given Add place payload with "<name>" and "<language>"
    When user calls the "addPlace" API using "POST" http method
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify if the place_Id created maps to "<name>" and "getPlace" API

    Examples: 
      | name          | language |
      | Dreams j park | Tamil    |

  #| Mulberry      | Tamil    |
  @deletePlace @regression
  Scenario: Verify if delete place functionality is working
    Given Delete place payload
    When user calls the "deletePlace" API using "POST" http method
    Then the API call got success with status code 200
    And "status" in response body is "OK"
