package com.marchesani.clair.fridge.com.marchesani.clair.fridge.restlet;

import com.marchesani.clair.fridge.db.FoodDAO;
import com.marchesani.clair.fridge.db.H2MemoryDatabase;
import com.marchesani.clair.fridge.db.HibernateSupport;
import org.hibernate.SessionFactory;
import org.restlet.*;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;

public class RestletServer extends Application {

    private static HibernateSupport hibernateSupport = new HibernateSupport();

    private static ChallengeAuthenticator guard;

    public static void main(String[] args) throws Exception {
        H2MemoryDatabase db = new H2MemoryDatabase(); // Create h2 in-memory database
        SessionFactory sessionFactory = hibernateSupport.getSessionFactory(); // Start hibernate
        FoodDAO foodDAO = new FoodDAO(sessionFactory); // Instantiate FoodDAO with Hibernate

        // Create a new Restlet server and register the components
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8182);
        Router router = new Router(component.getContext().createChildContext());
        router.getContext().getAttributes().put(FoodResource.FOOD_DAO_KEY, foodDAO);
        router.attach("/food", FoodResource.class);
        router.attach("/food/search", FoodSearchResource.class);

        // Set up authentication
        guard = new ChallengeAuthenticator(Context.getCurrent(), ChallengeScheme.HTTP_BASIC, "restlet");
        MapVerifier mapVerifier = new MapVerifier();
        mapVerifier.getLocalSecrets().put("clair", "password".toCharArray());
        guard.setVerifier(mapVerifier);
        guard.setNext(router);

        // Attach everything and start server
        component.getDefaultHost().attach("/restlet", guard);
        component.getDefaultHost().attachDefault(guard);
        component.start();
    }

}
