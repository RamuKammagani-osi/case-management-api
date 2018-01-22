package gov.ca.cwds.cm.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cm.service.dto.ClientDTO;
import gov.ca.cwds.cm.service.dto.ClientRelationshipDTO;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.data.legacy.cms.entity.ClientRelationship;
import gov.ca.cwds.data.legacy.cms.entity.enums.SameHomeStatus;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ClientRelationshipType;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class ClientRelationshipMapperTest {

  final private static String RELATIONSHIP_ID = "relationshipId";
  final private static String LEFT_SIDE_CLIENT_ID = "leftSideClientId";
  final private static String RIGHT_SIDE_CLIENT_ID = "rightSideClientId";
  final private static String LEFT_SIDE_CLIENT_FIRST_NAME = "John";
  final private static String LEFT_SIDE_CLIENT_LAST_NAME = "Left";
  final private static String LEFT_SIDE_CLIENT_EMAIL = "left@left.com";
  final private static String RIGHT_SIDE_CLIENT_FIRST_NAME = "Tomas";
  final private static String RIGHT_SIDE_CLIENT_LAST_NAME = "Right";
  final private static String RIGHT_SIDE_CLIENT_EMAIL = "right@right.com";
  final private static LocalDate START_DATE = LocalDate.of(2012, 12, 22);
  final private static LocalDate END_DATE = null;
  final private static Short RELATIONSHIP_TYPE_SYSTEMID = (short)285;
  final private static String RELATIONSHIP_TYPE_LONG_DESCRIPTION = "211";
  final private static Boolean ABSENT_PARENT_INDICATOR = Boolean.FALSE;
  final private static SameHomeStatus SAME_HOME_STATUS = SameHomeStatus.UNKNOWN;

  private ClientRelationshipMapperImpl mapper;

  @Before
  public void before(){
    mapper = new ClientRelationshipMapperImpl();
  }

  @Test
  public void testFromRightRelationshipToDto() {
    ClientRelationshipDTO dto = mapper.fromRightRelationshipToDto(clientRelationship());

    assertNotNull(dto);
    assertEquals(RELATIONSHIP_ID, dto.getId());
    assertEquals(LEFT_SIDE_CLIENT_ID, dto.getClientId());
    assertEquals(RIGHT_SIDE_CLIENT_ID, dto.getRelatedClientId());
    assertEquals(RELATIONSHIP_TYPE_SYSTEMID, dto.getTypeCode());
    assertEquals(ABSENT_PARENT_INDICATOR, dto.getAbsentParentIndicator());
    assertEquals(START_DATE, dto.getStartDate());
    assertEquals(END_DATE, dto.getEndDate());
    assertEquals(SAME_HOME_STATUS.toString(), dto.getSameHomeStatus());

    ClientDTO relatedClient = dto.getRelatedClient();
    assertNotNull(relatedClient);
    assertEquals(RIGHT_SIDE_CLIENT_ID, relatedClient.getIdentifier());
    assertEquals(RIGHT_SIDE_CLIENT_FIRST_NAME, relatedClient.getCommonFirstName());
    assertEquals(RIGHT_SIDE_CLIENT_LAST_NAME, relatedClient.getCommonLastName());
    assertEquals(RIGHT_SIDE_CLIENT_EMAIL, relatedClient.getEmailAddr());
  }

  @Test
  public void testFromLeftRelationshipToDto() {
    ClientRelationshipDTO dto = mapper.fromLeftRelationshipToDto(clientRelationship());

    assertNotNull(dto);
    assertEquals(RELATIONSHIP_ID, dto.getId());
    assertEquals(RIGHT_SIDE_CLIENT_ID, dto.getClientId());
    assertEquals(LEFT_SIDE_CLIENT_ID, dto.getRelatedClientId());
    assertEquals(Short.valueOf((short)211), dto.getTypeCode());
    assertEquals(ABSENT_PARENT_INDICATOR, dto.getAbsentParentIndicator());
    assertEquals(START_DATE, dto.getStartDate());
    assertEquals(END_DATE, dto.getEndDate());
    assertEquals(SAME_HOME_STATUS.toString(), dto.getSameHomeStatus());

    ClientDTO relatedClient = dto.getRelatedClient();
    assertNotNull(relatedClient);
    assertEquals(LEFT_SIDE_CLIENT_ID, relatedClient.getIdentifier());
    assertEquals(LEFT_SIDE_CLIENT_FIRST_NAME, relatedClient.getCommonFirstName());
    assertEquals(LEFT_SIDE_CLIENT_LAST_NAME, relatedClient.getCommonLastName());
    assertEquals(LEFT_SIDE_CLIENT_EMAIL, relatedClient.getEmailAddr());
  }

  private static ClientRelationship clientRelationship() {
    ClientRelationship clientRelationship = new ClientRelationship();
    clientRelationship.setIdentifier(RELATIONSHIP_ID);

    Client leftSide = new Client();
    leftSide.setIdentifier(LEFT_SIDE_CLIENT_ID);
    leftSide.setCommonFirstName(LEFT_SIDE_CLIENT_FIRST_NAME);
    leftSide.setCommonLastName(LEFT_SIDE_CLIENT_LAST_NAME);
    leftSide.setEmailAddress(LEFT_SIDE_CLIENT_EMAIL);
    clientRelationship.setLeftSide(leftSide);

    Client rightSide = new Client();
    rightSide.setIdentifier(RIGHT_SIDE_CLIENT_ID);
    rightSide.setCommonFirstName(RIGHT_SIDE_CLIENT_FIRST_NAME);
    rightSide.setCommonLastName(RIGHT_SIDE_CLIENT_LAST_NAME);
    rightSide.setEmailAddress(RIGHT_SIDE_CLIENT_EMAIL);
    clientRelationship.setRightSide(rightSide);

    ClientRelationshipType clientRelationshipType = new ClientRelationshipType();
    clientRelationshipType.setSystemId(RELATIONSHIP_TYPE_SYSTEMID);
    clientRelationshipType.setLongDescription(RELATIONSHIP_TYPE_LONG_DESCRIPTION);
    clientRelationship.setType(clientRelationshipType);

    clientRelationship.setAbsentParentIndicator(false);
    clientRelationship.setStartDate(START_DATE);
    clientRelationship.setEndDate(END_DATE);
    clientRelationship.setSameHomeStatus(SameHomeStatus.UNKNOWN);

    return clientRelationship;
  }
}
