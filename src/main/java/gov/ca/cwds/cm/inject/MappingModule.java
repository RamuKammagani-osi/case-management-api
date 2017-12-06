package gov.ca.cwds.cm.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.cm.service.mapper.AddressMapper;
import gov.ca.cwds.cm.service.mapper.ChildClientMapper;
import gov.ca.cwds.cm.service.mapper.CaseMapper;
import gov.ca.cwds.cm.service.mapper.ClientAddressMapper;
import gov.ca.cwds.cm.service.mapper.ClientMapper;
import gov.ca.cwds.cm.service.mapper.SystemCodeMapper;
import gov.ca.cwds.cm.service.mapper.facade.CaseByStaffMapper;
import org.mapstruct.factory.Mappers;

/**
 * DI (dependency injection) setup for mapping classes.
 *
 * @author CWDS TPT-3 Team
 */
public class MappingModule extends AbstractModule {

  @Override
  protected void configure() {
    bindMapperAsEagerSingleton(AddressMapper.class);
    bindMapperAsEagerSingleton(CaseMapper.class);
    bindMapperAsEagerSingleton(CaseByStaffMapper.class);
    bindMapperAsEagerSingleton(ChildClientMapper.class);
    bindMapperAsEagerSingleton(ClientAddressMapper.class);
    bindMapperAsEagerSingleton(ClientMapper.class);
    bindMapperAsEagerSingleton(SystemCodeMapper.class);
  }

  private void bindMapperAsEagerSingleton(Class<?> clazz) {
    bind(clazz).to(getMapperImpl(clazz)).asEagerSingleton();
  }

  private static Class getMapperImpl(Class<?> mapperClass) {
    return Mappers.getMapper(mapperClass).getClass();
  }
}
