package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import _07_intro_to_mocking.models.Engine;
import _07_intro_to_mocking.models.GasTank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;

    @BeforeEach
    void setUp() {
deliveryDriver=new DeliveryDriver("john",new Car(new Engine(1.5,1,1,1,1),new GasTank()),new CellPhone());
    }

    @Test
    void itShouldWasteTime() {
        //given

        //when
assertEquals(true,deliveryDriver.wasteTime());
        //then
    }

    @Test
    void itShouldRefuel() {
        //given

        //when
    	assertEquals(true,deliveryDriver.refuel(1));

        //then
    }

    @Test
    void itShouldContactCustomer() {
        //given

        //when
    	assertEquals(true,deliveryDriver.contactCustomer("1234567890"));

        //then
    }

}