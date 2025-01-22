package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import _08_mocking.models.DeliveryDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

class MyDonutShopTest {
	
    MyDonutShop myDonutShop;
    Order order;
    @Mock
    DeliveryService del;
    @Mock
    PaymentService pay;
    @Mock
    BakeryService bake;
    @Mock
    DeliveryDriver deliveryDriver;
    @BeforeEach
    void setUp() { 
    	MockitoAnnotations.openMocks(this);

    	myDonutShop=new MyDonutShop(pay,del,bake);
    	
    	List<DeliveryDriver> availableDeliveryDrivers = Collections.singletonList(deliveryDriver);
        myDonutShop.openForTheDay();
        
        del.setAvailableDeliveryDrivers(availableDeliveryDrivers);
        order = new Order("CUSTOMER_NAME",
    			"CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
    	
        //given
    	when(bake.getDonutsRemaining()).thenReturn(10);
    	when(pay.charge(order)).thenReturn(true);
        //when
    	myDonutShop.takeOrder(order);
        //then
    	//??? doesnt work???? says its never interacted with it make no sense
    	verify(del,times(1)).scheduleDelivery(any());
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
    	when(bake.getDonutsRemaining()).thenReturn(0);
    	when(pay.charge(order)).thenReturn(true);
        //when
    	try {
			myDonutShop.takeOrder(order);
		} catch (IllegalArgumentException e) {
			assertEquals(true,true);
			e.printStackTrace();
		} catch (Exception e) {
			assertEquals(true,false);
			e.printStackTrace();
		}
        //then
         verify(pay, never()).charge(any());
         
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given

        //when
    	try {
			myDonutShop.takeOrder(order);
		} catch(IllegalStateException e) {
	         verify(pay, never()).charge(any());
		}
    	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //then
    }

}