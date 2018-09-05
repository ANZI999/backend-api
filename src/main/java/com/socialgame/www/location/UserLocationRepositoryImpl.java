package com.socialgame.www.location;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class UserLocationRepositoryImpl implements UserLocationRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<UserLocation> getClosest(UserLocation userLocation) {
		String sql = "SELECT * FROM user_location "
				   + "WHERE user_id <> ?"
				   + "ORDER BY POWER((latitude - ?)/2, 2) + COS(?)*COS(latitude)*POWER((longitude - ?)/2, 2) ASC "
				   + "LIMIT 20";
		Query query = entityManager.createNativeQuery(sql, UserLocation.class);
		query.setParameter(1, userLocation.getUserID());
		query.setParameter(2, userLocation.getLatitude());
		query.setParameter(3, userLocation.getLatitude());
		query.setParameter(4, userLocation.getLongitude());
		return query.getResultList();
	}

}
