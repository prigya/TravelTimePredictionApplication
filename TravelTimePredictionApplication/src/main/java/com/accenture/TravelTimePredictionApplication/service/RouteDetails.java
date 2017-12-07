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
	        
	        Route route2 = new Route();
	        route2.setId("02");
	        route2.setName("Bayadarahalli to Accenture Office(Pritech Road)");
	        route2.setCity("Bangalore");
 
	        
	        Route route3 = new Route();
	        route3.setId("03");
	        route3.setName("Thane(West) to Accenture Office(Airoli)");
	        route3.setCity("Mumbai");
	        

	        Route route4 = new Route();
	        route4.setId("04");
	        route4.setName("Wakad to Accenure Office Hinjawadi");
	        route4.setCity("Pune");
	        
	        
	               
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
