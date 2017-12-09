package com.accenture.TravelTimePredictionApplication.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



public class RouteDetails {

	 private static final Map<String, Route> ROUTES = new HashMap<>();
	    static {
	    	Route route1 = new Route();
	    	route1.setId("01");
	    	route1.setName("Andheri(East) to Accenture Office(Airoli)");
	    	route1.setCity("Mumbai");
	    	route1.setOrigin("19.106478,72.861596");
	    	route1.setDestination("19.160846,73.001461");
	        
	        Route route2 = new Route();
	        route2.setId("02");
	        route2.setName("Bayadarahalli to Accenture Office(Pritech Road)");
	        route2.setCity("Bangalore");
	        route2.setOrigin("12.998841,77.606842");
	    	route2.setDestination("12.920591,77.681226");
 
	        
	        Route route3 = new Route();
	        route3.setId("03");
	        route3.setName("Thane(West) to Accenture Office(Airoli)");
	        route3.setCity("Mumbai");
	        route3.setOrigin("19.2243282,72.9601282");
	    	route3.setDestination("19.1622059,73.000855");
	        

	        Route route4 = new Route();
	        route4.setId("04");
	        route4.setName("Wakad to Accenure Office Hinjawadi");
	        route4.setCity("Pune");
	        route4.setOrigin("18.595416,73.770809");
	    	route4.setDestination("18.518141,73.920451");
	        
	        
	               
	        ROUTES.put(route1.getId(), route1);
	        ROUTES.put(route2.getId(), route2);
	        ROUTES.put(route3.getId(), route3);
	        ROUTES.put(route4.getId(), route4);
	    }
	    
	    public static Collection<Route> getRoutes() {
	        return ROUTES.values();
	    }
	    
	    public static Route getRoute(String id) {
	        return ROUTES.get(id);
	    }
}
