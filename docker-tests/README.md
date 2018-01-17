# CWDS CASE MANAGEMENT API TESTS

This image can be used to run a set of tests on CASE MANAGEMENT API 


## Configuration
### Application Configuration Parameters
- CASE_MANAGEMENT_API_URL -- URL of the case-management-api instance to test
- TEST_TYPE - test type to start in container. **Possible values:** smoke; integration. **Default value:** smoke.

###Example command to run:

docker run -e "CASE_MANAGEMENT_API_URL=http://tpt3.dev.cwds.io:8089/" -e "TEST_TYPE=smoke" -it cwds/case-management-api-tests

###Result
container returns 0 if tests finished successfully and 1 if tests failed.
