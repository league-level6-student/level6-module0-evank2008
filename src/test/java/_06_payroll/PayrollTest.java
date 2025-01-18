package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
double hourlyWage = 12.5;
int numHours=40;
        //when
double wage = payroll.calculatePaycheck(hourlyWage, numHours);
        //then
assertEquals(500,wage);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given

        //when

        //then
    	assertEquals(575,payroll.calculateMileageReimbursement(1000));
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given

        //when
assertEquals("Hello john, We are pleased to offer you an hourly wage of 12.5",payroll.createOfferLetter("john", 12.5));
        //then
    }

}