package com.swisscom.cloud.sb.broker.servicedefinition

import com.swisscom.cloud.sb.broker.model.CFService
import com.swisscom.cloud.sb.broker.model.repository.CFServiceRepository
import com.swisscom.cloud.sb.broker.servicedefinition.dto.ServiceDto
import spock.lang.Specification

class ServiceDefinitionInitializerUnitSpec extends Specification {
    private final String TEST_GUID = "TEST_GUID"

    private ServiceDefinitionConfig serviceDefinitionConfig
    private ServiceDefinitionInitializer serviceDefinitionInitializer
    private CFServiceRepository cfServiceRepository
    private ServiceDefinitionProcessor serviceDefinitionProcessor

    private List<CFService> cfServiceList

    def setup() {
        cfServiceList = [new CFService(name: "test1")]
        cfServiceRepository = Mock(CFServiceRepository)
        serviceDefinitionConfig = new ServiceDefinitionConfig(serviceDefinitions: [new ServiceDto(name: "test1", guid: TEST_GUID)])
        serviceDefinitionProcessor = new ServiceDefinitionProcessor(cfServiceRepository: cfServiceRepository)
        serviceDefinitionInitializer = new ServiceDefinitionInitializer(cfServiceRepository, serviceDefinitionConfig, serviceDefinitionProcessor)
    }

    def "Matching service definitions"() {
        when:
        serviceDefinitionInitializer.checkForMissingServiceDefinitions(cfServiceList)

        then:
        noExceptionThrown()
    }

    def "Adding service definition from config"() {
        given:
        serviceDefinitionConfig.serviceDefinitions << new ServiceDto(name: "test3")

        when:
        serviceDefinitionInitializer.checkForMissingServiceDefinitions(cfServiceList)

        then:
        noExceptionThrown()
    }

    def "Missing service definition from config"() {
        given:
        cfServiceList << new CFService(name: "test3")

        when:
        serviceDefinitionInitializer.checkForMissingServiceDefinitions(cfServiceList)

        then:
        def exception = thrown(Exception)
        exception.message == "Missing service definition configuration exception. Service list - [test1, test2, test3]"
    }

    def "Update service definition"() {
        given:
        CFService cfService = new CFService(guid: TEST_GUID)
        cfServiceRepository.findByGuid(serviceDefinitionConfig.serviceDefinitions[0].guid) >> cfService
        cfServiceRepository.save(cfService) >> cfService

        when:
        serviceDefinitionInitializer.addServiceDefinitions()

        then:
        noExceptionThrown()
    }
}
