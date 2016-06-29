package com.movieapp.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CategoryResourceTest.class, CustomerResourceTest.class, ExtraResourceTest.class,
	MovieResourceTest.class, MovieShowResourceTest.class, ScreenResourceTest.class, 
	SeatResourceTest.class, ShowResourceTest.class, ShowSeatResourceTest.class, 
	TicketResourceTest.class})
public class AllTests {

}
