package de.weimarnetz.registrator.services;

import org.assertj.core.util.Maps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import de.weimarnetz.registrator.configuration.NetworkInformation;
import de.weimarnetz.registrator.configuration.NetworksConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetworkVerificationServiceTest {

    @InjectMocks
    private NetworkVerificationService networkVerificationService;
    @Mock
    private NetworksConfiguration networksConfiguration;

    @Before
    public void setup() {
        Map<String, NetworkInformation> networkInformationMap = Maps.newHashMap("testnet", null);
        when(networksConfiguration.getMap()).thenReturn(networkInformationMap);
    }

    @Test
    public void invalidNetworkTest() {
        // when
        boolean networkValid = networkVerificationService.isNetworkValid("not_our_netz");

        // then
        assertThat(networkValid).isFalse();
    }

    @Test
    public void validNetworkTest() {
        // when
        boolean networkValid = networkVerificationService.isNetworkValid("testnet");

        // then
        assertThat(networkValid).isTrue();
    }
}